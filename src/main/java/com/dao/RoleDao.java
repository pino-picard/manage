package com.dao;

import com.dao.entity.RoleEntity;
import com.dao.entity.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caoxiao on 2017/5/24.
 */
@Repository("roleDao")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class RoleDao extends AbstractHibernateDao<RoleEntity> {

    public RoleDao () {
        super(RoleEntity.class);

    }

    public String getRoleNameById (String roleId) {
        RoleEntity roleEntity = get(Long.valueOf(roleId));
        return roleEntity.getRoleName();
    }

    public RoleEntity getRoleEntityByName (String roleName) {
        Criteria criteria = getSession().createCriteria(RoleEntity.class);
        criteria.add(Restrictions.eq("roleName", roleName));
        List target = criteria.list();
        if (target != null && !target.isEmpty()) {
            return (RoleEntity) target.get(0);
        } else {
            return null;
        }
    }
}
