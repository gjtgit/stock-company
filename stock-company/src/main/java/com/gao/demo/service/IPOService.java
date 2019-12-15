package com.gao.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gao.demo.entity.IPODetailEntity;
import com.gao.demo.model.IPODetailVo;
import com.gao.demo.repository.IPODetailRepo;

@Service
public class IPOService {
    @Autowired
    private IPODetailRepo ipoDetailRepo;
    
    public IPODetailEntity findById(Long id) throws Exception{
        IPODetailEntity ipo = null;
        try {
            Optional<IPODetailEntity> u = ipoDetailRepo.findById(id);
            if(u.isPresent()) {
                ipo = u.get();
            }
            return ipo;
        }catch(Exception e) {
            throw new Exception(e);
        }
    }
    
    public void save(IPODetailEntity u) throws Exception{
        try{
            ipoDetailRepo.saveAndFlush(u);
        }catch(Exception e) {
            throw new Exception(e);
        }
    }
    
    public IPODetailVo getIpoDetailVoById(Long id) throws Exception{
        IPODetailEntity eo = this.findById(id);
        IPODetailVo vo = new IPODetailVo();
        if(eo!=null) {
            vo = new IPODetailVo(eo.getId(),
            eo.getCompany().getId(),
            eo.getStockExchange(),
            eo.getPricePerShare(),
            eo.getTotalNumberOfShares(),
            eo.getOpenDateTime(),
            eo.getCompany().getCompanyName()
            );
        }
        return vo;
    }
    
    public List<IPODetailVo> getIpoDetailVoList() throws Exception{
        List<IPODetailVo> voList = new ArrayList<IPODetailVo>();
        List<IPODetailEntity> eoList = ipoDetailRepo.findAllByOrderByOpenDateTimeDesc();
        IPODetailVo vo = null;
        for(int i=0;i<eoList.size();i++) {
            IPODetailEntity eo = eoList.get(i);
            vo = new IPODetailVo(eo.getId(),
                    eo.getCompany().getId(),
                    eo.getStockExchange(),
                    eo.getPricePerShare(),
                    eo.getTotalNumberOfShares(),
                    eo.getOpenDateTime(),
                    eo.getCompany().getCompanyName()
                    );
            
            voList.add(vo);
        }
        return voList;
    }
        
}
