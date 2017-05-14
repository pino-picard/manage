package com.dao.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by caoxi on 2017/4/28.
 */
@Entity(name = "role")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @Cascade(value = {CascadeType.ALL})
    private ArrayList<PrivilegeMapEntity> privileges;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public ArrayList<PrivilegeMapEntity> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(ArrayList<PrivilegeMapEntity> privileges) {
        this.privileges = privileges;
    }
}
