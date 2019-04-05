package com.lxisoft.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the BloodGroup entity.
 */
public class BloodGroupDTO implements Serializable {

    private Long id;

    private String bloodGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BloodGroupDTO bloodGroupDTO = (BloodGroupDTO) o;
        if (bloodGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bloodGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BloodGroupDTO{" +
            "id=" + getId() +
            ", bloodGroup='" + getBloodGroup() + "'" +
            "}";
    }
}
