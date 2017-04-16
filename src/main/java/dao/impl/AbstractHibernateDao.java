package dao.impl;

import org.hibernate.*;
import javax.annotation.Resource;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoxiao on 2017/4/16.
 */
@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends Serializable> {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    protected Class<T> clazz;

    public AbstractHibernateDao() {
    }

    protected  void setClazz(final Class<T> clazzToSet) {
        if(clazzToSet == null) {
            throw new NullPointerException();
        } else {
            this.clazz = clazzToSet;
        }
    }

    public AbstractHibernateDao(Class<T> clazz) {
        if(clazz == null) {
            throw new NullPointerException();
        } else {
            this.clazz = clazz;
        }
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session openSession() {
        return sessionFactory.openSession();
    }
    
    public T get(final String id) {
        Session session = openSession();
        T entity = null;
        try {
            session.beginTransaction();
            entity = (T) session.get(clazz, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        } finally {
            session.close();
        }
        return entity;
    }

    public  T get(final long id) {
        Session session = openSession();
        T entity = null;
        try {
            session.beginTransaction();
            entity = (T) session.get(clazz, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        } finally {
            session.close();
        }
        return entity;
    }
    
    public List<T> get() {
        Session session = openSession();
        List<T> tmpList = new ArrayList<T>();
        try {
            session.beginTransaction();
            tmpList = session.createQuery("from " + clazz.getName()).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        }finally {
            session.close();
        }

        return tmpList;
    }
    
    public  T update(final T entity) {
        Session session = openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        } finally {
            session.close();
        }

        return entity;
    }

    public  T updateWithoutTransaction(Session session, final T entity) {
        try {
            session.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e ;
        }

        return entity;
    }

    
    
    public  void create(final T entity) {
        Session session = openSession();
        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        } finally {
            session.close();
        }
    }

    /**
     * 该方法主要是为了解决重试写数据库时，持久化类会存在id的值，当id的策略设为自动增长时会抛异常。
     * @param entity
     */
    public void createEntity(final T entity) {
        String methodName = "createEntity";
        Session session = openSession();
        resetGeneratedValue(entity);
        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        } finally {
            session.close();
        }
    }

    private void resetGeneratedValue(T entity) {
        Field[] field = entity.getClass().getDeclaredFields();
        if(field == null) {
            return;
        }

        for(Field fie : field){
            if(!fie.isAccessible()){
                fie.setAccessible(true);
            }
            GeneratedValue annon = fie.getAnnotation(GeneratedValue.class);
            if (null != annon) {
                try {
                    fie.set(entity, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    
    public void create(List<T> entities) {
        Session session = sessionFactory.openSession();
        try {
            Transaction tx = session.beginTransaction();
            for (T entity : entities) {
                session.save(entity);
            }

            tx.commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        } finally {
            session.close();
        }
    }

    
    
    public  T merge(final T entity) {
        Session session = openSession();
        try {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        } finally {
            session.close();
        }
        return entity;
    }

    
    
    public void merge(List<T> entities) {
        Session session = sessionFactory.openSession();
        try {
            Transaction tx = session.beginTransaction();
            for (T entity : entities) {
                session.merge(entity);
            }

            tx.commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        } finally {
            session.close();
        }
    }

    
    
    public  void delete(final T entity) {
        Session session = openSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        } finally {
            session.close();
        }
    }

    
    
    public  void deleteById(final long entityId) {
        try {
            final T entity = get(entityId);
            delete(entity);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        }
    }


    
    
    public void deleteAll() {
        String hql = "delete from " + clazz.getName();
        Session session = openSession();
        try {

            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e ;
        } finally {
            session.close();
        }
    }
}
