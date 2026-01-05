package com.petruboulescu.web;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BankingServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().print( """
                            <html>
                            <body>
                            <h1>Hello World</h1>
                            <p>This is an embedded Tomcat page</p>
                            </body>
                            </html>""");
    }


}
