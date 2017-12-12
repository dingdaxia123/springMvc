package com.springapp.mvc.dao;


import com.springapp.mvc.entity.Cost;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class TestDao {
    private String conf="applicationContext.xml";
    ApplicationContext app = new ClassPathXmlApplicationContext(conf);
    ICostDao dao = (ICostDao)app.getBean("costDaoImpl");
    
    @Test
    public void test1(){
       
        List<Cost> list = dao.findAll();
        for(Cost c:list){
            System.out.println(c.getName());
        }
        System.out.println(list);
    }
    
    @Test
    public void test2(){
        Cost cost=dao.findById(1);
        System.out.println(cost.getStatus());
    }
    
    @Test
    public void test3(){
        Cost cost = new Cost();
        cost.setName("1234");
        cost.setBaseCost(1.1);
        cost.setBaseDuration(1);
        cost.setCostType("1");
        dao.save(cost);
    }
    
    @Test
    public void test4(){
       Cost cost=dao.findById(2);
       cost.setName("dhyy");
       dao.update(cost);
    }
    
    @Test
    public void test5(){
        dao.delect(128,"12314");
    }
}
