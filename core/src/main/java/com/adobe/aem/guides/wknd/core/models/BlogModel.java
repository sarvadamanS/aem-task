// package com.wknd.models;

// import org.apache.sling.models.annotations.Model;
// import org.apache.sling.models.annotations.DefaultInjectionStrategy;
// import org.apache.sling.models.annotations.injectors.Value;
// import org.apache.sling.models.annotations.injectors.Self;
// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.api.servlets.SlingHttpServletRequest;

// import java.util.ArrayList;
// import java.util.List;

// @Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
// public class BlogModel {

//     @Self
//     private SlingHttpServletRequest request;

//     @Value("${posts}")
//     private List<Post> posts;

//     // A method to load posts, assuming posts are stored in a specific resource path
//     public List<Post> getPosts() {
//         List<Post> postList = new ArrayList<>();
//         ResourceResolver resolver = request.getResourceResolver();

//         // Replace this with the actual path where your blog posts are stored
//         Resource blogResource = resolver.getResource("/content/blog");

//         if (blogResource != null) {
//             for (Resource postResource : blogResource.getChildren()) {
//                 Post post = new Post();
//                 post.setTitle(postResource.getValueMap().get("title", String.class));
//                 post.setContent(postResource.getValueMap().get("content", String.class));
//                 post.setAuthor(postResource.getValueMap().get("author", String.class));
//                 post.setCategory(postResource.getValueMap().get("category", String.class));
//                 postList.add(post);
//             }
//         }

//         return postList;
//     }

//     // A nested static class for individual blog posts
//     public static class Post {
//         private String title;
//         private String content;
//         private String author;
//         private String category;

//         // Getters and setters for the fields
//         public String getTitle() {
//             return title;
//         }

//         public void setTitle(String title) {
//             this.title = title;
//         }

//         public String getContent() {
//             return content;
//         }

//         public void setContent(String content) {
//             this.content = content;
//         }

//         public String getAuthor() {
//             return author;
//         }

//         public void setAuthor(String author) {
//             this.author = author;
//         }

//         public String getCategory() {
//             return category;
//         }

//         public void setCategory(String category) {
//             this.category = category;
//         }
//     }
// }
