package com.gao.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gao.demo.entity.CompanyEntity;
import com.gao.demo.entity.StockPriceEntity;
import com.gao.demo.model.IPODetailVo;

@Repository
public interface CompanyRepo extends JpaRepository<CompanyEntity,Long>{
    public List<CompanyEntity> findByCompanyNameLike(String name);
    
    public List<CompanyEntity> findBySectorId(Long sectorId);

    @Query("select c from CompanyEntity c, IPODetailEntity i where c.id = i.company.id and i.stockExchange = ?1 ")
    public List<CompanyEntity> getCompanyListByExchange(String stockExchange);
    
}
