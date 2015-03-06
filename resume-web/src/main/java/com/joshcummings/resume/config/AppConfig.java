package com.joshcummings.resume.config;

import java.util.Arrays;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.joshcummings.resume.service.ResumeRestService;
 
@Configuration
public class AppConfig { 
    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }
 
    
    @Bean
    @DependsOn("cxf")
    public ResumeRestService jaxRsClient(ApplicationContext appContext) {
    	ResumeRestService store = JAXRSClientFactory.create("http://localhost:8080/resume-ws", ResumeRestService.class, Arrays.asList(jsonProvider()));
        return store;
    }

    
    @Bean
    public JacksonJsonProvider jsonProvider() {
        JacksonJsonProvider p =  new JacksonJaxbJsonProvider();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JSR310Module());
        p.setMapper(om);
        return p;
    }
}