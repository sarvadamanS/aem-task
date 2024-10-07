"use strict";
console.log("loaded");
document.addEventListener("DOMContentLoaded", function () {
  let blogPosts = []; // Array to store fetched posts
  const postList = document.getElementById("postList");

  // Fetch posts from the servlet
  function fetchPosts() {
    fetch("/bin/simple", { method: "GET" })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok " + response.statusText);
        }
        return response.json(); // Parse the JSON response
      })
      .then((data) => {
        blogPosts = data; // Store fetched posts
        renderPosts(blogPosts); // Render posts after fetching
      })
      .catch((error) => console.error("Error fetching blog posts:", error));
  }

  // Function to render posts on the page
  function renderPosts(posts) {
    postList.innerHTML = ""; // Clear existing posts

    posts.forEach((post) => {
      const li = document.createElement("li");
      li.className = "blog-post";
      li.innerHTML = `
        <h3>${post.title || "No title available"}</h3>
        <p>${post.content || "No content available"}</p>
        <p><strong>Author:</strong> ${post.author || "Unknown author"}</p>
        <p><strong>Category:</strong> ${post.category || "Uncategorized"}</p>
      `;
      postList.appendChild(li);
    });
  }

  // Filter posts based on search input
  function filterPosts() {
    const query = document.getElementById("search").value.toLowerCase();
    const filteredPosts = blogPosts.filter((post) => {
      const title = post.title.toLowerCase();
      const content = post.content.toLowerCase();
      return title.includes(query) || content.includes(query);
    });
    renderPosts(filteredPosts); // Render filtered posts
  }

  // Attach event listener to the search input
  document.getElementById("search").addEventListener("input", filterPosts);

  // Fetch and display posts when the page loads
  fetchPosts();
});
