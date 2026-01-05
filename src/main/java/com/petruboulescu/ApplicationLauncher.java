package com.petruboulescu;

import com.petruboulescu.web.BankingServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class ApplicationLauncher {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        int port = System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 8080;
        tomcat.setPort(port);
        tomcat.getConnector();
        Context context = tomcat.addContext("", null);
        Wrapper servlet = Tomcat.addServlet(context, "basicServlet", new BankingServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
    }

}
