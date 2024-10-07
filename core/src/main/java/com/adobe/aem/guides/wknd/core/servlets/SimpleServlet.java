package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Component(
    service = {Servlet.class},
    property = {
        "sling.servlet.paths=/bin/simple",  // The endpoint URL
        "sling.servlet.methods=GET"         // HTTP method
    }
)
public class SimpleServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setStatus(SlingHttpServletResponse.SC_OK);

        // Sample blog post data
        List<String> blogPosts = Arrays.asList(
            "{\"title\": \"Post 1\", \"content\": \"Content of post 1\", \"author\": \"Author 1\", \"category\": \"Category 1\"}",
            "{\"title\": \"Post 2\", \"content\": \"Content of post 2\", \"author\": \"Author 2\", \"category\": \"Category 2\"}",
            "{\"title\": \"Post 3\", \"content\": \"Content of post 3\", \"author\": \"Author 3\", \"category\": \"Category 3\"}"
        );

        try (PrintWriter out = response.getWriter()) {
            out.println(blogPosts.toString());
        }
    }
}
