package com.chatsystem.server.service.impl;


import com.chatsystem.server.dao.DataRepository;
import com.chatsystem.server.service.DataService;
import com.chatsystem.server.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class DataServiceImpl<T extends BaseEntity> implements DataService<T> {

    private final DataRepository<T> dataRepository;

    @Autowired
    public DataServiceImpl(DataRepository<T> dataRepository) {
        this.dataRepository = dataRepository;
    }



    @Override
    @Transactional
    public T add(T item) {
        return dataRepository.save(item);
    }

    @Override
    @Transactional
    public Collection<T> addAll(Collection<T> collection) {
        return null;
    }

    @Override
    public T update(T item) {
        return add(item);
    }

    @Override
    public Collection<T> updateAll(Collection<T> collection) {
        return null;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public T get(int id) {
        return dataRepository.findOne(id);
    }

    @Override
    public Collection<T> getAll() {
        return dataRepository.findAll();
    }

    @Override
    public void remove(int id) {
        dataRepository.delete(id);
    }

    @Override
    public void remove(T item) {
        dataRepository.delete(item);
    }


    @Override
    public void removeAll() {
        dataRepository.deleteAll();
    }

}
