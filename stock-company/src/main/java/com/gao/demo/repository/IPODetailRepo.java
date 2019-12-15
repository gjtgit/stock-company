package com.gao.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gao.demo.entity.IPODetailEntity;

@Repository
public interface IPODetailRepo extends JpaRepository<IPODetailEntity,Long>{
    List<IPODetailEntity> findAllByOrderByOpenDateTimeDesc();
}
