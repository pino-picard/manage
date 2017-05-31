package com.dao;

import com.dao.entity.ProgressEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by caoxiao on 2017/5/24.
 */
@Repository("progressDao")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class ProgressDao extends AbstractHibernateDao<ProgressEntity> {


    public ProgressDao () {
        super(ProgressEntity.class);

    }
}
