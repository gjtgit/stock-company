package com.gao.demo.model;

public class CompanyVo {
    private Long id;
    private String companyName;
    private float turnover;
    private String ceo;
    private String boardMembers;
    private String listedExchanges;
    private Long sectorId;
    private String brief;
    private String stockCodeInExchange;
    
    public CompanyVo() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public float getTurnover() {
        return turnover;
    }
    public void setTurnover(float turnover) {
        this.turnover = turnover;
    }
    public String getCeo() {
        return ceo;
    }
    public void setCeo(String ceo) {
        this.ceo = ceo;
    }
    public String getBoardMembers() {
        return boardMembers;
    }
    public void setBoardMembers(String boardMembers) {
        this.boardMembers = boardMembers;
    }
    public String getListedExchanges() {
        return listedExchanges;
    }
    public void setListedExchanges(String listedExchanges) {
        this.listedExchanges = listedExchanges;
    }
    public Long getSectorId() {
        return sectorId;
    }
    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }
    public String getBrief() {
        return brief;
    }
    public void setBrief(String brief) {
        this.brief = brief;
    }
    public String getStockCodeInExchange() {
        return stockCodeInExchange;
    }
    public void setStockCodeInExchange(String stockCodeInExchange) {
        this.stockCodeInExchange = stockCodeInExchange;
    }

}
