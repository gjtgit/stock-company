package com.gao.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gao.demo.entity.SectorEntity;
import com.gao.demo.entity.StockPriceEntity;

@Component
public class StockPriceClientHystric implements StockPriceClient {
    
    Logger logger = LoggerFactory.getLogger(StockPriceClientHystric.class);
 
    @Override
    public List<StockPriceEntity>  getStockPriceByComIdAndExAndPeriod(Long sectorId, String stockExchange, String fromDate, String toDate) {
        logger.info("[error]:getStockPriceByComIdAndExAndPeriod --- Call service-upload failed.");
        return null;
    }

}
