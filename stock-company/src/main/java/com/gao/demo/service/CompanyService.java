package com.gao.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gao.demo.entity.CompanyEntity;
import com.gao.demo.entity.IPODetailEntity;
import com.gao.demo.entity.StockPriceEntity;
import com.gao.demo.model.CompanyVo;
import com.gao.demo.repository.CompanyRepo;

@Service
public class CompanyService {
    Logger logger = LoggerFactory.getLogger(CompanyService.class);
    
    @Autowired
    private CompanyRepo companyRepo;
    
    @Autowired
    private StockPriceClient stockPriceClient;
    
    public CompanyEntity findById(Long id) throws Exception{
        CompanyEntity companyEntity = null;
        try {
            Optional<CompanyEntity> u = companyRepo.findById(id);
            if(u.isPresent()) {
                companyEntity = u.get();
            }
            return companyEntity;
        }catch(Exception e) {
            throw new Exception(e);
        }
    }
    
    public List<CompanyEntity> findByCompanyNameLike(String name) throws Exception{
        try{
            return companyRepo.findByCompanyNameLike("%"+name+"%");
        }catch(Exception e) {
            throw new Exception(e);
        }
    }

    public List<CompanyEntity> getCompanyList() throws Exception{
        return companyRepo.findAll();
    }
    
    public List<CompanyVo> getCompanyVoList() throws Exception{
        List<CompanyVo> voList = new ArrayList<CompanyVo>();
        List<CompanyEntity> eoList = companyRepo.findAll();
        
        CompanyVo vo = null; 
        for(int i=0;i<eoList.size();i++) {
            CompanyEntity eo = eoList.get(i);
            StringBuffer sb = new StringBuffer();
            List<IPODetailEntity> ipoList = eo.getIpoList();
            for(IPODetailEntity ipo:ipoList) {
                sb.append(ipo.getStockExchange()+",");
            }
            vo = new CompanyVo();
            vo.setId(eo.getId());
            vo.setCompanyName(eo.getCompanyName());
            vo.setTurnover(eo.getTurnover());
            vo.setCeo(eo.getCeo());
            vo.setBoardMembers(eo.getBoardMembers());
            if(sb.length()>0 && sb.lastIndexOf(",")>0) {
                vo.setListedExchanges(sb.substring(0,sb.lastIndexOf(",")));
            }
            vo.setSectorId(eo.getSectorId());
            vo.setBrief(eo.getBrief());
            vo.setStockCodeInExchange(eo.getStockCodeInExchange());
            voList.add(vo);
        }
        return voList;
    }   
    
    public List<CompanyEntity> getCompanyListBySectorId(Long sectorId) throws Exception{
        try{
            return companyRepo.findBySectorId(sectorId);
        }catch(Exception e) {
            throw new Exception(e);
        }
    }
    
    public List<CompanyEntity> getCompanyListByExchange(String stockExchange) throws Exception{
        List<CompanyEntity> l = new ArrayList<CompanyEntity>();
        l = this.companyRepo.getCompanyListByExchange(stockExchange);
        return l;
    }
    
    public void save(CompanyEntity u) throws Exception{
        try{
            companyRepo.saveAndFlush(u);
        }catch(Exception e) {
            throw new Exception(e);
        }
    }

    public List<StockPriceEntity> getStockPriceByComIdAndExAndPeriod(Long companyId, String stockExchange, String fromDate, String toDate) throws Exception{
        return stockPriceClient.getStockPriceByComIdAndExAndPeriod(companyId, stockExchange, fromDate, toDate);
    }

}
