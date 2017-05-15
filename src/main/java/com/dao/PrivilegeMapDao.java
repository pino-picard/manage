package com.dao;

import com.dao.entity.PrivilegeMapEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by caoxi on 2017/5/15.
 */
@Repository("privilegeMapDao")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class PrivilegeMapDao extends AbstractHibernateDao<PrivilegeMapEntity> {

    public PrivilegeMapDao () {
        super(PrivilegeMapEntity.class);
    }
}
