package com.whut.smart.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 一个角色能访问多个资源，没有去细化角色的权限
 *
 * Created by null on 2017/1/2.
 */
public class ResourceDto implements Serializable {

    private Long id;

    private String url;

    private String description;

    private List<RoleDto> roleDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoleDto> getRoleDtos() {
        return roleDtos;
    }

    public void setRoleDtos(List<RoleDto> roleDtos) {
        this.roleDtos = roleDtos;
    }

    @Override
    public String toString() {
        return "ResourceDto{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", roleDtos=" + roleDtos +
                '}';
    }
}
