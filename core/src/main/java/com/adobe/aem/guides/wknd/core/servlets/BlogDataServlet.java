package com.adobe.aem.guides.wknd.core.servlets;

import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet; // Ensure this import is present
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/blogdata")
public class BlogDataServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Map<String, String>> blogPosts = new ArrayList<>();

        // Dummy blog data
        blogPosts.add(createPost("Understanding AEM", "Content about AEM.", "Author 1", "Category 1"));
        blogPosts.add(createPost("Getting Started with AEM", "Beginner's guide to AEM.", "Author 2", "Category 1"));

        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(blogPosts));
    }

    private Map<String, String> createPost(String title, String content, String author, String category) {
        Map<String, String> post = new HashMap<>();
        post.put("title", title);
        post.put("content", content);
        post.put("author", author);
        post.put("category", category);
        return post;
    }
}
