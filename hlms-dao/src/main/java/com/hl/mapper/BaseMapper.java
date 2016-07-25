package com.hl.mapper;

import java.util.List;

public interface BaseMapper {

	public <T> List<T> findByPage(T formMap);
    
    public <T> List<T> findByWhere(T formMap);
    
    public void editEntity(Object formMap);
    
    public <T> List<T> findByNames(T formMap);
    
    public <T> List<T> findByAttribute(String key, String value, Class<T> clazz);
    
    public void deleteByAttribute(String key, String value, @SuppressWarnings("rawtypes") Class clazz);
    
    public void addEntity(Object formMap);
    
    public void batchSave(@SuppressWarnings("rawtypes") List formMap);
    
    public void deleteByNames(Object formMap) throws Exception;
    
    public <T> T findbyFrist(String key,String value,Class<T> clazz);
}
