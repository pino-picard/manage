package com.dao;

import com.dao.entity.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by caoxi on 2017/5/15.
 */
@Repository("userDao")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class UserDao extends AbstractHibernateDao<UserEntity> {

    public UserDao () {
        super(UserEntity.class);
    }

    public List<UserEntity> getUserList (String userName, String roleName) {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
        if (userName != null && !Objects.equals(userName, "")) {
            criteria.add(Restrictions.like("userName", userName, MatchMode.ANYWHERE));
        }
        if (roleName != null && !Objects.equals(roleName, "")) {
            criteria.add(Restrictions.eq("role_id", roleName));
        }

        return criteria.list();
    }

    public UserEntity findUserByName (String userName) {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("userName", userName));
        List target = criteria.list();
        if (target != null && !target.isEmpty()) {
            return (UserEntity) target.get(0);
        } else {
            return null;
        }
    }

    public UserEntity verifyUser (String userName, String password) {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
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
