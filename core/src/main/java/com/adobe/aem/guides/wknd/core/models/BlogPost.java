package com.adobe.aem.guides.wknd.core.models;

public class BlogPost {
    private String title;
    private String author;
    private String content;
    private String category;

    public BlogPost(String title, String author, String content, String category) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }
}
