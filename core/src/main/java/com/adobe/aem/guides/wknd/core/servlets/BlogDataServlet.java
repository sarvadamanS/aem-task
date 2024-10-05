package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/wknd/blogdata",
                "sling.servlet.methods=GET"
        }
)
public class BlogDataServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        // Fetch external REST API data
        String blogDataJson = fetchBlogData();
        response.setContentType("application/json");
        response.getWriter().write(blogDataJson);
    }

    private String fetchBlogData() {
        // Code to call external API and return the JSON response
        return "{\"posts\": []}";
    }
}
