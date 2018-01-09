package com.chatsystem.server.dao;


import com.chatsystem.server.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository<T extends BaseEntity> extends JpaRepository<T, Integer>{
}
