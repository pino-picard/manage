package com.dao;

import com.dao.entity.CompanyEntity;
import com.dao.entity.DepartmentEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caoxiao on 2017/5/14.
 */
@Repository("departmentDao")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class DepartmentDao extends AbstractHibernateDao<DepartmentEntity> {

    public DepartmentDao () {
        super(DepartmentEntity.class);
    }

    public DepartmentEntity findDepartmentByName (String name) {
        List<DepartmentEntity> target;
        Criteria criteria = openSession().createCriteria(DepartmentEntity.class);
        criteria.add(Restrictions.eq("departmentName", name));
        target = criteria.list();
        if (target != null && !target.isEmpty()) {
            return target.get(0);
        } else {
            return null;
        }
    }
}
