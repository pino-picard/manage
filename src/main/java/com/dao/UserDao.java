package com.dao;

import com.dao.entity.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caoxi on 2017/5/15.
 */
@Repository("userDao")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class UserDao extends AbstractHibernateDao<UserEntity> {

    public UserDao () {
        super(UserEntity.class);
    }

    public UserEntity findUserByName (String userName) {
        Criteria criteria = openSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userName", userName));
        List target = criteria.list();
        if (target != null && !target.isEmpty()) {
            return (UserEntity) target.get(0);
        } else {
            return null;
        }
    }

    public UserEntity verifyUser (String userName, String password) {
        Criteria criteria = openSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userName", userName))
                .add(Restrictions.eq("password", password));
        List target = criteria.list();
        if (target != null && !target.isEmpty()) {
            return (UserEntity) target.get(0);
        } else {
            return null;
        }
    }
}
