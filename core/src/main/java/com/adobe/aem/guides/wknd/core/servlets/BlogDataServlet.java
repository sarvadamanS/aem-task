package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/blogposts",
                "sling.servlet.methods=GET"
        }
)
public class BlogDataServlet extends SlingAllMethodsServlet {

    // Sample blog data
    private final List<BlogPost> blogPosts = List.of(
            new BlogPost("Understanding AEM", "Content about AEM.", "Author 1", "Category 1"),
            new BlogPost("Getting Started with AEM", "Beginner's guide to AEM.", "Author 2", "Category 1"),
            new BlogPost("AEM Best Practices", "Best practices for AEM.", "Author 3", "Category 2"),
            new BlogPost("Advanced AEM Techniques", "Advanced techniques for AEM.", "Author 4", "Category 2"),
            new BlogPost("Troubleshooting AEM", "Common issues and solutions.", "Author 5", "Category 3"),
            new BlogPost("AEM Security Practices", "How to secure your AEM instance.", "Author 6", "Category 3")
    );

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String searchQuery = request.getParameter("search");
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = 2; // Number of posts per page

        // Filter blog posts based on the search query
        List<BlogPost> filteredPosts = blogPosts;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            filteredPosts = blogPosts.stream()
                    .filter(post -> post.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                            post.getContent().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Paginate the results
        int totalPosts = filteredPosts.size();
        int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
        List<BlogPost> paginatedPosts = filteredPosts.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        // Build JSON response
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write("{\"posts\": " + toJson(paginatedPosts) + ", \"page\": " + page + ", \"totalPages\": " + totalPages + "}");
    }

    private String toJson(List<BlogPost> posts) {
        StringBuilder jsonBuilder = new StringBuilder("[");
        for (int i = 0; i < posts.size(); i++) {
            BlogPost post = posts.get(i);
            jsonBuilder.append("{\"title\":\"").append(post.getTitle()).append("\",")
                    .append("\"content\":\"").append(post.getContent()).append("\",")
                    .append("\"author\":\"").append(post.getAuthor()).append("\",")
                    .append("\"category\":\"").append(post.getCategory()).append("\"}");
            if (i < posts.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    // Dummy BlogPost class to hold blog data
    private static class BlogPost {
        private final String title;
        private final String content;
        private final String author;
        private final String category;

        public BlogPost(String title, String content, String author, String category) {
            this.title = title;
            this.content = content;
            this.author = author;
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getAuthor() {
            return author;
        }

        public String getCategory() {
            return category;
        }
    }
}
