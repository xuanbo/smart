package com.whut.smart.dto;

import java.io.Serializable;

/**
 * 角色
 *
 * Created by null on 2017/1/1.
 */
public class RoleDto implements Serializable {

    private Long id;
    private String name; //ROLE_XX
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
