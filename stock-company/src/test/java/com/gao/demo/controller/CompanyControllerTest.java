package com.gao.demo.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gao.demo.entity.CompanyEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration 
public class CompanyControllerTest {
    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        this.mockMvc = webAppContextSetup(wac).build();
    }
    
    @Test
    public void getCompanyList() throws Exception{
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/companies")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk()) 
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();  
        String content=mvcResult.getResponse().getContentAsString();  
        Assert.assertEquals(200,status);
    }
    
    @Test
    public void getCompanyDetails() throws Exception{
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/companies/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk()) 
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();  
        String content=mvcResult.getResponse().getContentAsString();  
        Assert.assertEquals(200,status);
        ObjectMapper om = new ObjectMapper();
        CompanyEntity c = om.readValue(content, CompanyEntity.class);
        Assert.assertNotNull(c); 
        Long x = 1L;
        Assert.assertEquals(x, c.getId());

    }
}
