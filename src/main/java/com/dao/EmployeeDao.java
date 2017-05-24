package com.dao;

import com.dao.entity.CompanyEntity;
import com.dao.entity.EmployeeEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caoxiao on 2017/4/16.
 */
@Repository("employeeDao")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class EmployeeDao extends AbstractHibernateDao<EmployeeEntity> {

    public EmployeeDao () {
        super(EmployeeEntity.class);
    }

    public List<EmployeeEntity> findEmployeeByName (String name) {
        Criteria criteria = openSession().createCriteria(CompanyEntity.class);
        criteria.add(Restrictions.eq("companyName", name));
        return criteria.list();
    }

    public List<EmployeeEntity> describeEmployee (String employeeName, String companyName, String departmentName, String telNum, String recruitId) {
        Criteria criteria = openSession().createCriteria(EmployeeEntity.class);
        if (employeeName != null) {
            criteria.add(Restrictions.like("employeeName", employeeName, MatchMode.ANYWHERE));
        }
        if (companyName != null) {
            criteria.add(Restrictions.like("companyName", companyName, MatchMode.ANYWHERE));
        }
        if (departmentName != null) {
            criteria.add(Restrictions.like("departmentName", departmentName, MatchMode.ANYWHERE));
        }
        if (telNum != null) {
            criteria.add(Restrictions.like("employeeTel", telNum, MatchMode.ANYWHERE));
        }
        if (recruitId != null) {
            criteria.add(Restrictions.eq("recruitId", recruitId));
        }

        return criteria.list();
    }

}
