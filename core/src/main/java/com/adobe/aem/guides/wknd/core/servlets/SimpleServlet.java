package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(
        service = {Servlet.class},
        property = {
                "sling.servlet.paths=/bin/simple",  // The endpoint URL
                "sling.servlet.methods=GET"          // HTTP method
        }
)
public class SimpleServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setStatus(SlingHttpServletResponse.SC_OK);

        try (PrintWriter out = response.getWriter()) {
            out.println("{\"message\": \"Hello, World!\"}");
        }
    }
}
