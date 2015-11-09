package edu.sjsu.cmpe275.lab2.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInit implements WebApplicationInitializer{

	public void onStartup(ServletContext serCon) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(ApplicationConfig.class);
        ctx.register(ResponseTypeConfig.class);
		ctx.setServletContext(serCon);

		ServletRegistration.Dynamic servlet = serCon.addServlet("DispatcherServlet", new DispatcherServlet(ctx));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

}
