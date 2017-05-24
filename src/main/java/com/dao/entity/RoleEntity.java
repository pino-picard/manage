package com.dao.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

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

    @Column(name = "map_id")
    private String privilege_id;

    @Column(name = "create_time")
    private Date createTime;

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

    public String getPrivilege_id() {
        return privilege_id;
    }

    public void setPrivilege_id(String privilege_id) {
        this.privilege_id = privilege_id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
