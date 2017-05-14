package com.dao;

import com.dao.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by caoxiao on 2017/4/16.
 */
@Repository("employeeDao")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class EmployeeDao extends AbstractHibernateDao<EmployeeEntity> {

    public EmployeeDao () {
        super(EmployeeEntity.class);
    }
}
