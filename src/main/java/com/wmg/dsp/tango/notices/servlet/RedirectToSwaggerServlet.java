package com.wmg.dsp.tango.notices.servlet;


import com.wmg.dsp.tango.notices.entrypoint.ServiceApplication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns="") // mapped to /
public class RedirectToSwaggerServlet extends HttpServlet {

    public static final String SWAGGER_PATH_SUFFIX = "/api";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(ServiceApplication.PATH + SWAGGER_PATH_SUFFIX);
    }
}