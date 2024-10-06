"use strict";

use(function () {
  var data = {};
  data.pageTitle = currentPage.properties["jcr:title"];
  data.title = granite.resource.properties["title"];
  data.description = granite.resource.properties["description"];
  console.log("yea");
  // Sample blog data, replace this with your dynamic fetching logic
  data.blogPosts = [
    {
      title: "Understanding AEM",
      content: "Content about AEM.",
      author: "Author 1",
      category: "Category 1",
    },
    {
      title: "Getting Started with AEM",
      content: "Beginner's guide to AEM.",
      author: "Author 2",
      category: "Category 1",
    },
    {
      title: "AEM Best Practices",
      content: "Best practices for AEM.",
      author: "Author 3",
      category: "Category 2",
    },
    {
      title: "Advanced AEM Techniques",
      content: "Advanced techniques for AEM.",
      author: "Author 4",
      category: "Category 2",
    },
    {
      title: "Troubleshooting AEM",
      content: "Common issues and solutions.",
      author: "Author 5",
      category: "Category 3",
    },
    {
      title: "AEM Security Practices",
      content: "How to secure your AEM instance.",
      author: "Author 6",
      category: "Category 3",
    },
  ];

  return data;
});
