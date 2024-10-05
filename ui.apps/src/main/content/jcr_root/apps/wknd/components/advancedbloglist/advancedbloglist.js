(function (document, $) {
  $(document).ready(function () {
    const cachedData = localStorage.getItem("blogData");
    if (cachedData) {
      renderBlogPosts(JSON.parse(cachedData));
    } else {
      $.get("/bin/wknd/blogdata", function (data) {
        localStorage.setItem("blogData", JSON.stringify(data));
        renderBlogPosts(data);
      });
    }
  });

  function renderBlogPosts(posts) {
    const filteredPosts = applyFilters(posts);
    const blogPostsContainer = $(".blog-posts ul");
    blogPostsContainer.empty(); // Clear existing posts

    filteredPosts.forEach((post) => {
      const postHtml = `
        <li>
          <h3>${post.title}</h3>
          <p>Author: ${post.author}</p>
          <p>Category: ${post.category}</p>
          <p>${post.content}</p>
        </li>
      `;
      blogPostsContainer.append(postHtml);
    });
  }

  function applyFilters(posts) {
    const searchText = $("#search").val().toLowerCase();
    return posts.filter((post) =>
      post.title.toLowerCase().includes(searchText)
    );
  }

  // Event listener for the search input
  $("#search").on("input", function () {
    const cachedData = JSON.parse(localStorage.getItem("blogData"));
    renderBlogPosts(cachedData);
  });
})(document, jQuery);
