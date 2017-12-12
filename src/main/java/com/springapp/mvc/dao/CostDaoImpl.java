package com.springapp.mvc.dao;



import javax.annotation.Resource;

import com.springapp.mvc.entity.Cost;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CostDaoImpl extends HibernateDaoSupport implements ICostDao{
    
    @Resource
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Cost> findAll() {
        String hql="from Cost";
        return getHibernateTemplate().find(hql);
    }

    @Override
    public Cost findById(int id) {
        Cost cost=getHibernateTemplate().get(Cost.class, id);
        return cost;
    }

    @Override
    public void save(Cost cost) {
        getHibernateTemplate().save(cost);
        
    }

    @Override
    public void update(Cost cost) {
        getHibernateTemplate().update(cost);
        
    }

    @Override
    public void delect(int id,String name) {
        Cost cost=new Cost();
        cost.setId(id);
        cost.setName(name);
        getHibernateTemplate().delete(cost);
    }

}
