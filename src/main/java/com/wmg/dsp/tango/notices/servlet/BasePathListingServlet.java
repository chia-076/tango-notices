package com.wmg.dsp.tango.notices.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmg.dsp.platform.swagger.resources.ApiListingResource;
import com.wordnik.swagger.jaxrs.config.BeanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@WebServlet(urlPatterns="/apiVersions", loadOnStartup=1)
public class BasePathListingServlet extends HttpServlet {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private BeanConfig beanConfig;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> apiBaseUrls = Arrays.asList(beanConfig.getBasePath() + ApiListingResource.LISTING_NAME);
        response.setContentType(MediaType.APPLICATION_JSON);
        response.getWriter().print(mapper.writeValueAsString(apiBaseUrls));
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
    }
}
