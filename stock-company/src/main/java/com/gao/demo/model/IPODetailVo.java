package com.gao.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class IPODetailVo {
    private Long id;
    private Long companyId;
    private double pricePerShare;
    private int totalNumberOfShares;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openDateTime;
    private String companyName;
    private String stockExchange;
    
    public IPODetailVo() {
    }
    
    public IPODetailVo(Long id, Long companyId, String stockExchange, double pricePerShare, int totalNumberOfShares,
            Date openDateTime, String companyName) {
        super();
        this.id = id;
        this.companyId = companyId;
        this.stockExchange = stockExchange;
        this.pricePerShare = pricePerShare;
        this.totalNumberOfShares = totalNumberOfShares;
        this.openDateTime = openDateTime;
        this.companyName = companyName;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public String getStockExchange() {
        return stockExchange;
    }
    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }
    public double getPricePerShare() {
        return pricePerShare;
    }
    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }
    public int getTotalNumberOfShares() {
        return totalNumberOfShares;
    }
    public void setTotalNumberOfShares(int totalNumberOfShares) {
        this.totalNumberOfShares = totalNumberOfShares;
    }
    public Date getOpenDateTime() {
        return openDateTime;
    }
    public void setOpenDateTime(Date openDateTime) {
        this.openDateTime = openDateTime;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
