package com.joshcummings.resume.config;

import java.util.Arrays;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.joshcummings.resume.model.ResumeNotFoundException;
import com.joshcummings.resume.service.ResumeNotFoundExceptionMapper;
import com.joshcummings.resume.service.ResumeRestService;
import com.joshcummings.resume.service.ResumeRestServiceServer;
 
@Configuration
public class AppConfig {
 
    @ApplicationPath("/")
    public class JaxRsApiApplication extends Application { }
 
    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }
 
    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer(ApplicationContext appContext) {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(jaxRsApiApplication(), JAXRSServerFactoryBean.class);
        factory.setServiceBeans(Arrays.<Object>asList(resumeService()));
        factory.setAddress("/");
        factory.setProviders(Arrays.asList(jsonProvider(), exceptionMapper()));
        return factory.create();
    }
 
    @Bean
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }
 
    @Bean
    public JacksonJsonProvider jsonProvider() {
        JacksonJsonProvider p =  new JacksonJaxbJsonProvider();
        return p;
    }
    
    @Bean
    public ExceptionMapper<ResumeNotFoundException> exceptionMapper() {
    	ExceptionMapper<ResumeNotFoundException> em = new ResumeNotFoundExceptionMapper();
    	return em;
    }
 
    @Bean
    public ResumeRestService resumeService() {
        return new ResumeRestServiceServer();
    }
}