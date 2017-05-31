package com.dao;

import com.dao.entity.CompanyEntity;
import com.dao.entity.PrivilegeMapEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caoxi on 2017/5/15.
 */
@Repository("privilegeMapDao")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class PrivilegeMapDao extends AbstractHibernateDao<PrivilegeMapEntity> {

    public PrivilegeMapDao () {
        super(PrivilegeMapEntity.class);
    }

    public PrivilegeMapEntity findMap (String privilege) {
        List<PrivilegeMapEntity> target;
        Criteria criteria = getSession().createCriteria(PrivilegeMapEntity.class);
        criteria.add(Restrictions.eq("pageNum", privilege));
        target = criteria.list();
        if (target != null && !target.isEmpty()) {
            return target.get(0);
        } else {
            return null;
        }
    }
}
