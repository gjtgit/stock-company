package com.gao.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gao.demo.entity.CompanyEntity;
import com.gao.demo.entity.IPODetailEntity;
import com.gao.demo.entity.SectorEntity;
import com.gao.demo.entity.StockPriceEntity;
import com.gao.demo.model.CompanyVo;
import com.gao.demo.model.IPODetailVo;
import com.gao.demo.service.CompanyService;
import com.gao.demo.service.IPOService;

@RestController
public class CompanyController {
    
    Logger logger = LoggerFactory.getLogger(CompanyController.class);
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private IPOService ipoService;
    
    //Company ID, From Period, To Period
    @RequestMapping(path="/companyprice/{companyId}",method=RequestMethod.GET)
    public List<StockPriceEntity> getStockPriceByComIdAndExAndPeriod(@PathVariable Long companyId, 
            @RequestParam String stockExchange, @RequestParam String fromDate, @RequestParam String toDate) throws Exception{
        Date from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
        Date to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
        return this.companyService.getStockPriceByComIdAndExAndPeriod(companyId, stockExchange, fromDate, toDate);
    }
    
    @RequestMapping(path="/sectorcompanies",method=RequestMethod.GET)
    public List<CompanyEntity> getCompanyListBySectorId(@RequestParam Long sectorId) throws Exception{
        return this.companyService.getCompanyListBySectorId(sectorId);
    }
    
    @RequestMapping(path="/exchangecompanies",method=RequestMethod.GET)
    public List<CompanyEntity> getCompanyListByExchange(@RequestParam String stockExchange) throws Exception{
        return this.companyService.getCompanyListByExchange(stockExchange);
    }
    
    //used to retrieve list of Companies based on pattern matching of Company Name
    @RequestMapping(path="/matchcompanies/{name}",method=RequestMethod.GET)
    public List<CompanyEntity> findByCompanyNameLike(@PathVariable String name )throws Exception{
        return this.companyService.findByCompanyNameLike(name);
    }
    
    @RequestMapping(path="/companies",method=RequestMethod.GET)
    public List<CompanyEntity> getCompanyList()throws Exception{
        return this.companyService.getCompanyList();
    }
    
    @RequestMapping(path="/companyvoes",method=RequestMethod.GET)
    public List<CompanyVo> getCompanyVoList()throws Exception{
        return this.companyService.getCompanyVoList();
    } 
    
    //getCompanyDetails – Basic information about company
    @RequestMapping(path="/companies/{id}",method=RequestMethod.GET)
    public CompanyEntity getCompanyDetails(@PathVariable Long id) throws Exception{
        return this.companyService.findById(id);
    }
    
    @RequestMapping(path="/companies",method=RequestMethod.POST)
    public CompanyEntity addCompany(@RequestBody CompanyVo vo, BindingResult bindingResult,Model model, Map<String,Object> map, HttpSession session) 
            throws Exception{
        logger.info("info: addCompany --- start");
        CompanyEntity com = new CompanyEntity();
        com.setCompanyName(vo.getCompanyName());
        com.setTurnover(vo.getTurnover());
        com.setCeo(vo.getCeo());
        com.setBoardMembers(vo.getBoardMembers());
        com.setListedExchanges(vo.getListedExchanges());
        com.setStockCodeInExchange(vo.getStockCodeInExchange());
        com.setSectorId(vo.getSectorId());
        com.setBrief(vo.getBrief());
        companyService.save(com);
        logger.info("info: addCompany --- end");
        return com;
    }
    
    @RequestMapping(path="/companies",method=RequestMethod.PUT)
    public CompanyEntity updateCompany(@RequestBody CompanyVo vo, BindingResult bindingResult,Model model, Map<String,Object> map, HttpSession session) 
            throws Exception{
        logger.info("info: updateCompany --- start");
        CompanyEntity com = companyService.findById(vo.getId());
        if(com!=null) {
            com.setCompanyName(vo.getCompanyName());
            com.setTurnover(vo.getTurnover());
            com.setCeo(vo.getCeo());
            com.setBoardMembers(vo.getBoardMembers());
            com.setListedExchanges(vo.getListedExchanges());
            com.setStockCodeInExchange(vo.getStockCodeInExchange());
            com.setSectorId(vo.getSectorId());
            com.setBrief(vo.getBrief());
            companyService.save(com);
        }
        logger.info("info: updateCompany --- end");
        return com;
    }
    

//    @RequestMapping(path="/ipovoes",method=RequestMethod.GET)
//    public List<IPODetailVo> getIpoDetailVoList() throws Exception{
//        return companyService.getIpoDetailVoList();
//    }
    
    @RequestMapping(path="/ipovoes",method=RequestMethod.GET)
    public List<IPODetailVo> getIpoDetailVoList() throws Exception{
        return ipoService.getIpoDetailVoList();
    }
    
    //get IPODetailVo by ipo id
    @RequestMapping(path="/ipovoes/{id}",method=RequestMethod.GET)
    public IPODetailVo getCompanyIPODetailVo(@PathVariable Long id) throws Exception{
        return ipoService.getIpoDetailVoById(id);
    }
    
    @RequestMapping(path="/ipoes",method=RequestMethod.POST)
    public IPODetailEntity addIpo(@RequestBody IPODetailVo vo, BindingResult bindingResult,Model model, Map<String,Object> map, HttpSession session) 
            throws Exception{
        logger.info("info: addIpo --- start");
        IPODetailEntity ipo = new IPODetailEntity();
        //ipo.setCompanyId(vo.getCompanyId());
        CompanyEntity com = companyService.findById(vo.getCompanyId());
        ipo.setCompany(com);
        ipo.setStockExchange(vo.getStockExchange());
        ipo.setPricePerShare(vo.getPricePerShare());
        ipo.setTotalNumberOfShares(vo.getTotalNumberOfShares());
        ipo.setOpenDateTime(vo.getOpenDateTime());
        ipoService.save(ipo);
        logger.info("info: addIpo --- end");
        return ipo;
    }
    
    @RequestMapping(path="/ipoes",method=RequestMethod.PUT)
    public IPODetailEntity updateIpo(@RequestBody IPODetailVo vo, BindingResult bindingResult,Model model, Map<String,Object> map, HttpSession session) 
            throws Exception{
        logger.info("info: updateIpo --- start");
        IPODetailEntity ipo = ipoService.findById(vo.getId());
        if(ipo!=null) {
//            ipo.setCompanyId(vo.getCompanyId());
            ipo.setStockExchange(vo.getStockExchange());
            ipo.setPricePerShare(vo.getPricePerShare());
            ipo.setTotalNumberOfShares(vo.getTotalNumberOfShares());
            ipo.setOpenDateTime(vo.getOpenDateTime());
            ipoService.save(ipo);
        }
        logger.info("info: updateIpo --- end");
        return ipo;
    }
    
    @PostMapping("/test")
    public String test(){
        return "{\"str\":\"from stock-company post\"}";
    }
}
