package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class)
public class AdvancedBlogListModel {

    @ValueMapValue
    private String someProperty; // Example of using properties from the dialog

    private List<BlogPost> blogPosts; // Store the list of blog posts

    @PostConstruct
    protected void init() {
        // Initialize blog posts when the model is constructed
        blogPosts = getBlogPosts();
    }

    // Getter for blog posts
    public List<BlogPost> getBlogPosts() {
        List<BlogPost> blogPosts = new ArrayList<>();

        // Add blog posts (You can replace this with dynamic content from an external API)
        blogPosts.add(new BlogPost("Title 1", "Author 1", "Content 1", "Category 1"));
        blogPosts.add(new BlogPost("Title 2", "Author 2", "Content 2", "Category 2"));

        return blogPosts;
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPosts; // This method can be used to get blog posts in the HTL file
    }
}
