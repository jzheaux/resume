package com.joshcummings.resume.config;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		servletContext.addListener(new ContextLoaderListener(createWebAppContext()));
        
        CXFServlet cxfServlet = new CXFServlet();
        
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("CXFServlet", cxfServlet);
        appServlet.setLoadOnStartup(1);
 
        Set<String> mappingConflicts = appServlet.addMapping("/*");
	}

    private WebApplicationContext createWebAppContext() {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppConfig.class);
        appContext.register(PersistenceConfig.class);
        return appContext;
    }
}
