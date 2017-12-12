package com.springapp.mvc.dao;


import com.springapp.mvc.entity.Cost;

import java.util.List;

public interface ICostDao {
    List<Cost> findAll();
    
    Cost findById(int id);
    
    void save(Cost cost);
    
    void update(Cost cost);
    
    void delect(int id, String dhyy);
}
