package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter; // Import statement for PrintWriter
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component(
    service = {Servlet.class},
    property = {
        "sling.servlet.paths=/bin/simple",  // The endpoint URL
        "sling.servlet.methods=GET"         // HTTP method
    }
)
public class SimpleServlet extends SlingAllMethodsServlet {

    private static final String EXTERNAL_API_URL = "https://6703fc2bab8a8f89273284d4.mockapi.io/blogapi/blogs";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setStatus(SlingHttpServletResponse.SC_OK);

        // Fetch data from the external API
        String apiResponse = fetchBlogPostsFromAPI();
        
        try (PrintWriter out = response.getWriter()) { // Using PrintWriter to send response
            // Send the API response back to the client
            out.println(apiResponse);
        }
    }

    /**
     * Fetches blog post data from the external API.
     *
     * @return JSON response from the external API as a String
     * @throws IOException if an I/O error occurs
     */
    private String fetchBlogPostsFromAPI() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(EXTERNAL_API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        try {
            // Set up the connection to use GET method and the required headers
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            
            // Read the API response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } finally {
            connection.disconnect(); // Close the connection
        }
        
        return result.toString();
    }
}
