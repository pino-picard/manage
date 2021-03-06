package com.dao;

import com.dao.entity.CompanyEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caoxiao on 2017/5/14.
 */
@Repository("companyDao")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class CompanyDao extends AbstractHibernateDao<CompanyEntity> {


    public CompanyDao () {
        super(CompanyEntity.class);
    }

    public CompanyEntity findCompanyByName (String name) {
        List<CompanyEntity> target;
        Criteria criteria = getSession().createCriteria(CompanyEntity.class);
        criteria.add(Restrictions.eq("companyName", name));
        target = criteria.list();
        if (target != null && !target.isEmpty()) {
            return target.get(0);
        } else {
            return null;
        }
    }
}
