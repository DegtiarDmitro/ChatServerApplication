package com.chatsystem.server.service;


import com.chatsystem.server.entity.BaseEntity;

import java.util.Collection;


/**
 *
 * @param <T>
 */

public interface DataService<T extends BaseEntity> {

    T add(T item);

    Collection<T> addAll(Collection<T> collection);

    T update(T item);

    Collection<T> updateAll(Collection<T> collection);

    T get(int id);

    Collection<T> getAll();

    void remove(int id);

    void remove(T item);

    void removeAll();


}
