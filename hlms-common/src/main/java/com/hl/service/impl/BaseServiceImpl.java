package com.hl.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.hl.mapper.BaseMapper;
import com.hl.service.BaseService;

public class BaseServiceImpl implements BaseService{

	@Inject
    private BaseMapper baseMapper;
    
    public <T> List<T> findByPage(T formMap) {
        // TODO Auto-generated method stub
        return baseMapper.findByPage(formMap);
    }

    public <T> List<T> findByWhere(T formMap) {
        // TODO Auto-generated method stub
        return baseMapper.findByWhere(formMap);
    }

    public void editEntity(Object formMap) throws Exception {
        // TODO Auto-generated method stub
        baseMapper.editEntity(formMap);
    }

    public <T> List<T> findByNames(T formMap) {
        // TODO Auto-generated method stub
        return baseMapper.findByNames(formMap);
    }

    public <T> List<T> findByAttribute(String key, String value, Class<T> clazz) {
        // TODO Auto-generated method stub
        return baseMapper.findByAttribute(key,value,clazz);
    }

    public void deleteByAttribute(String key, String value, @SuppressWarnings("rawtypes") Class clazz)
            throws Exception {
        // TODO Auto-generated method stub
        baseMapper.deleteByAttribute(key,value,clazz);
    }

    public void addEntity(Object formMap) throws Exception {
        // TODO Auto-generated method stub
        baseMapper.addEntity(formMap);
    }

    public void batchSave(@SuppressWarnings("rawtypes") List formMap)  {
        // TODO Auto-generated method stub
        baseMapper.batchSave(formMap);
    }

    public void deleteByNames(Object formMap) throws Exception {
        // TODO Auto-generated method stub
        baseMapper.deleteByNames(formMap);
    }

    public <T> T findbyFrist(String key, String value, Class<T> clazz) {
        // TODO Auto-generated method stub
        return baseMapper.findbyFrist(key,value,clazz);
    }
}
