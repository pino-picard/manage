package com.dao;


import com.dao.entity.ProgressEntity;
import com.dao.entity.RecruitEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caoxiao on 2017/5/31.
 */
@Repository("recruitDao")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class RecruitDao extends AbstractHibernateDao<RecruitEntity>{
    public RecruitDao () {
        super(RecruitEntity.class);

    }

    public List<RecruitEntity> getRecruitList (String title, String applyPerson, String status) {
        Criteria criteria = getSession().createCriteria(RecruitEntity.class);
        if (title != null && !"".equals(title)) {
            criteria.add(Restrictions.like("recruitTitle", title, MatchMode.ANYWHERE));
        }
        if (applyPerson != null && !"".equals(applyPerson)) {
            criteria.add(Restrictions.like("applyPerson", applyPerson, MatchMode.ANYWHERE));
        }
        if (status != null && !"".equals(status)) {
            String[] statusList = status.split(",");
            criteria.add(Restrictions.in("state",statusList));
        }

        return criteria.list();
    }
}
