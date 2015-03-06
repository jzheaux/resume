package com.joshcummings.resume.config;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebApplicationInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer {

	/*
	 * @Override public void onStartup(ServletContext container) throws
	 * ServletException { AnnotationConfigWebApplicationContext rootContext =
	 * new AnnotationConfigWebApplicationContext();
	 * rootContext.register(PersistenceConfig.class);
	 * 
	 * // Manage the lifecycle of the root application context
	 * container.addListener(new ContextLoaderListener(rootContext));
	 * 
	 * // Create the dispatcher servlet's Spring application context
	 * AnnotationConfigWebApplicationContext dispatcherServlet = new
	 * AnnotationConfigWebApplicationContext();
	 * dispatcherServlet.register(WebConfiguration.class);
	 * 
	 * 
	 * ServletRegistration.Dynamic registration =
	 * container.addServlet("dispatcher", new
	 * DispatcherServlet(dispatcherServlet)); registration.setLoadOnStartup(1);
	 * registration.addMapping("/*"); }
	 */

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
