package com.lxisoft.web.rest;

import com.lxisoft.RedDonorApp;

import com.lxisoft.domain.BloodGroup;
import com.lxisoft.repository.BloodGroupRepository;
import com.lxisoft.service.BloodGroupService;
import com.lxisoft.service.dto.BloodGroupDTO;
import com.lxisoft.service.mapper.BloodGroupMapper;
import com.lxisoft.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.lxisoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BloodGroupResource REST controller.
 *
 * @see BloodGroupResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedDonorApp.class)
public class BloodGroupResourceIntTest {

    private static final String DEFAULT_BLOOD_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_BLOOD_GROUP = "BBBBBBBBBB";

    @Autowired
    private BloodGroupRepository bloodGroupRepository;

    @Autowired
    private BloodGroupMapper bloodGroupMapper;

    @Autowired
    private BloodGroupService bloodGroupService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBloodGroupMockMvc;

    private BloodGroup bloodGroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BloodGroupResource bloodGroupResource = new BloodGroupResource(bloodGroupService);
        this.restBloodGroupMockMvc = MockMvcBuilders.standaloneSetup(bloodGroupResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BloodGroup createEntity(EntityManager em) {
        BloodGroup bloodGroup = new BloodGroup()
            .bloodGroup(DEFAULT_BLOOD_GROUP);
        return bloodGroup;
    }

    @Before
    public void initTest() {
        bloodGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createBloodGroup() throws Exception {
        int databaseSizeBeforeCreate = bloodGroupRepository.findAll().size();

        // Create the BloodGroup
        BloodGroupDTO bloodGroupDTO = bloodGroupMapper.toDto(bloodGroup);
        restBloodGroupMockMvc.perform(post("/api/blood-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bloodGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the BloodGroup in the database
        List<BloodGroup> bloodGroupList = bloodGroupRepository.findAll();
        assertThat(bloodGroupList).hasSize(databaseSizeBeforeCreate + 1);
        BloodGroup testBloodGroup = bloodGroupList.get(bloodGroupList.size() - 1);
        assertThat(testBloodGroup.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
    }

    @Test
    @Transactional
    public void createBloodGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bloodGroupRepository.findAll().size();

        // Create the BloodGroup with an existing ID
        bloodGroup.setId(1L);
        BloodGroupDTO bloodGroupDTO = bloodGroupMapper.toDto(bloodGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBloodGroupMockMvc.perform(post("/api/blood-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bloodGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BloodGroup in the database
        List<BloodGroup> bloodGroupList = bloodGroupRepository.findAll();
        assertThat(bloodGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBloodGroups() throws Exception {
        // Initialize the database
        bloodGroupRepository.saveAndFlush(bloodGroup);

        // Get all the bloodGroupList
        restBloodGroupMockMvc.perform(get("/api/blood-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bloodGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP.toString())));
    }
    
    @Test
    @Transactional
    public void getBloodGroup() throws Exception {
        // Initialize the database
        bloodGroupRepository.saveAndFlush(bloodGroup);

        // Get the bloodGroup
        restBloodGroupMockMvc.perform(get("/api/blood-groups/{id}", bloodGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bloodGroup.getId().intValue()))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBloodGroup() throws Exception {
        // Get the bloodGroup
        restBloodGroupMockMvc.perform(get("/api/blood-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBloodGroup() throws Exception {
        // Initialize the database
        bloodGroupRepository.saveAndFlush(bloodGroup);

        int databaseSizeBeforeUpdate = bloodGroupRepository.findAll().size();

        // Update the bloodGroup
        BloodGroup updatedBloodGroup = bloodGroupRepository.findById(bloodGroup.getId()).get();
        // Disconnect from session so that the updates on updatedBloodGroup are not directly saved in db
        em.detach(updatedBloodGroup);
        updatedBloodGroup
            .bloodGroup(UPDATED_BLOOD_GROUP);
        BloodGroupDTO bloodGroupDTO = bloodGroupMapper.toDto(updatedBloodGroup);

        restBloodGroupMockMvc.perform(put("/api/blood-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bloodGroupDTO)))
            .andExpect(status().isOk());

        // Validate the BloodGroup in the database
        List<BloodGroup> bloodGroupList = bloodGroupRepository.findAll();
        assertThat(bloodGroupList).hasSize(databaseSizeBeforeUpdate);
        BloodGroup testBloodGroup = bloodGroupList.get(bloodGroupList.size() - 1);
        assertThat(testBloodGroup.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
    }

    @Test
    @Transactional
    public void updateNonExistingBloodGroup() throws Exception {
        int databaseSizeBeforeUpdate = bloodGroupRepository.findAll().size();

        // Create the BloodGroup
        BloodGroupDTO bloodGroupDTO = bloodGroupMapper.toDto(bloodGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBloodGroupMockMvc.perform(put("/api/blood-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bloodGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BloodGroup in the database
        List<BloodGroup> bloodGroupList = bloodGroupRepository.findAll();
        assertThat(bloodGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBloodGroup() throws Exception {
        // Initialize the database
        bloodGroupRepository.saveAndFlush(bloodGroup);

        int databaseSizeBeforeDelete = bloodGroupRepository.findAll().size();

        // Get the bloodGroup
        restBloodGroupMockMvc.perform(delete("/api/blood-groups/{id}", bloodGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BloodGroup> bloodGroupList = bloodGroupRepository.findAll();
        assertThat(bloodGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BloodGroup.class);
        BloodGroup bloodGroup1 = new BloodGroup();
        bloodGroup1.setId(1L);
        BloodGroup bloodGroup2 = new BloodGroup();
        bloodGroup2.setId(bloodGroup1.getId());
        assertThat(bloodGroup1).isEqualTo(bloodGroup2);
        bloodGroup2.setId(2L);
        assertThat(bloodGroup1).isNotEqualTo(bloodGroup2);
        bloodGroup1.setId(null);
        assertThat(bloodGroup1).isNotEqualTo(bloodGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BloodGroupDTO.class);
        BloodGroupDTO bloodGroupDTO1 = new BloodGroupDTO();
        bloodGroupDTO1.setId(1L);
        BloodGroupDTO bloodGroupDTO2 = new BloodGroupDTO();
        assertThat(bloodGroupDTO1).isNotEqualTo(bloodGroupDTO2);
        bloodGroupDTO2.setId(bloodGroupDTO1.getId());
        assertThat(bloodGroupDTO1).isEqualTo(bloodGroupDTO2);
        bloodGroupDTO2.setId(2L);
        assertThat(bloodGroupDTO1).isNotEqualTo(bloodGroupDTO2);
        bloodGroupDTO1.setId(null);
        assertThat(bloodGroupDTO1).isNotEqualTo(bloodGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bloodGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bloodGroupMapper.fromId(null)).isNull();
    }
}
