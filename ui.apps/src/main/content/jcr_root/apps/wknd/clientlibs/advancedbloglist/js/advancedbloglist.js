"use strict";
document.addEventListener("DOMContentLoaded", function () {
  let blogPosts = []; // Array to store fetched posts
  let cachedPosts = []; // Array for filtered and sorted posts
  const postList = document.getElementById("postList");
  const postsPerPage = 5; // Number of posts to display per page
  let currentPage = 1; // Track the current page
  const loadingMessage = document.querySelector(".blogs-message");

  // Fetch posts from the servlet
  function fetchPosts() {
    showLoading(true); // Show loading message

    fetch("/bin/simple", { method: "GET" })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok " + response.statusText);
        }
        return response.json(); // Parse the JSON response
      })
      .then((data) => {
        blogPosts = data; // Store fetched posts
        cachedPosts = blogPosts; // Cache posts
        renderPosts(cachedPosts); // Render posts
        updatePagination(cachedPosts); // Update pagination info
        showLoading(false); // Hide loading message
      })
      .catch((error) => {
        console.error("Error fetching blog posts:", error);
        showLoading(false); // Hide loading message in case of error
      });
  }

  // Render posts on the page
  function renderPosts(posts) {
    postList.innerHTML = ""; // Clear existing posts

    // Calculate the start and end index for pagination
    const start = (currentPage - 1) * postsPerPage;
    const end = start + postsPerPage;
    const paginatedPosts = posts.slice(start, end); // Get posts for the current page

    paginatedPosts.forEach((post) => {
      const li = document.createElement("li");
      li.className = "blog-post";
      li.innerHTML = `
        <h3>${post.title || "No title available"}</h3>
        <p><strong>Author:</strong> ${post.author || "Unknown author"}</p>
        <p><strong>Category:</strong> ${post.category || "Uncategorized"}</p>
      `;
      postList.appendChild(li);
    });
  }

  // Update pagination controls
  function updatePagination(posts) {
    const totalPages = Math.ceil(posts.length / postsPerPage);
    document.getElementById(
      "pageInfo"
    ).innerText = `Page ${currentPage} of ${totalPages}`;

    // Disable buttons if on the first or last page
    document.getElementById("prevPage").disabled = currentPage === 1;
    document.getElementById("nextPage").disabled = currentPage === totalPages;
  }

  // Filter posts based on search input
  function filterPosts() {
    const query = document.getElementById("search").value.toLowerCase();
    const filteredPosts = blogPosts.filter((post) => {
      const title = post.title.toLowerCase();
      const author = post.author.toLowerCase();
      const category = post.category.toLowerCase();
      return (
        title.includes(query) ||
        author.includes(query) ||
        category.includes(query)
      );
    });

    cachedPosts = filteredPosts; // Cache filtered posts
    currentPage = 1; // Reset to the first page after filtering
    renderPosts(cachedPosts); // Render filtered posts
    updatePagination(cachedPosts); // Update pagination info
  }

  // Sort posts based on the selected field and order
  function sortPosts() {
    const sortField = document.getElementById("sortOptions").value;
    const sortOrder = document.getElementById("sortOrder").value;

    const sortedPosts = [...cachedPosts].sort((a, b) => {
      const fieldA = a[sortField].toLowerCase();
      const fieldB = b[sortField].toLowerCase();
      if (fieldA < fieldB) return sortOrder === "asc" ? -1 : 1;
      if (fieldA > fieldB) return sortOrder === "asc" ? 1 : -1;
      return 0;
    });

    cachedPosts = sortedPosts; // Cache sorted posts
    currentPage = 1; // Reset to the first page after sorting
    renderPosts(cachedPosts); // Render sorted posts
    updatePagination(cachedPosts); // Update pagination info
  }

  // Show or hide the loading message
  function showLoading(isLoading) {
    loadingMessage.style.display = isLoading ? "block" : "none";
  }

  // Event listener for search input
  document.getElementById("search").addEventListener("input", filterPosts);

  // Event listener for sorting options
  document.getElementById("sortOptions").addEventListener("change", sortPosts);
  document.getElementById("sortOrder").addEventListener("change", sortPosts);

  // Pagination controls
  document.getElementById("prevPage").addEventListener("click", () => {
    if (currentPage > 1) {
      currentPage--;
      renderPosts(cachedPosts);
      updatePagination(cachedPosts);
    }
  });

  document.getElementById("nextPage").addEventListener("click", () => {
    const totalPages = Math.ceil(cachedPosts.length / postsPerPage);
    if (currentPage < totalPages) {
      currentPage++;
      renderPosts(cachedPosts);
      updatePagination(cachedPosts);
    }
  });

  // Fetch and display posts when the page loads
  fetchPosts();
});
