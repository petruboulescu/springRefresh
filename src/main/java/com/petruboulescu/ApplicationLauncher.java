package com.petruboulescu;

import com.petruboulescu.context.ApplicationConfiguration;
import jakarta.servlet.ServletContext;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationLauncher {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        int port = System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 8080;
        tomcat.setPort(port);
        tomcat.getConnector();
        Context context = tomcat.addContext("", null);
        WebApplicationContext webApplicationContext = createApplicationContext(context.getServletContext());
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        Wrapper servlet = Tomcat.addServlet(context, "dispatcher", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
    }

    public static WebApplicationContext createApplicationContext(ServletContext servletContext) {
        //Create web context
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        //Register config class
        ctx.register(ApplicationConfiguration.class);
        //Set the context
        ctx.setServletContext(servletContext);
        ctx.refresh();
        // Not really needed, here for tutorialing shutdown methods on beans
        ctx.registerShutdownHook();
        return ctx;
    }

}
