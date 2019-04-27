package com.lxisoft.domain;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * BloodGroup entity.
 * @Author Sarangi Balu
 */
@ApiModel(description = "BloodGroup entity. @Author Sarangi Balu")
@Entity
@Table(name = "blood_group")
public class BloodGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blood_group")
    private String bloodGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public BloodGroup bloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BloodGroup bloodGroup = (BloodGroup) o;
        if (bloodGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bloodGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BloodGroup{" +
            "id=" + getId() +
            ", bloodGroup='" + getBloodGroup() + "'" +
            "}";
    }
}
