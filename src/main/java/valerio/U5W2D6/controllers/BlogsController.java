package valerio.U5W2D6.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import valerio.U5W2D6.entities.Author;
import valerio.U5W2D6.entities.BlogPostPayload;
import valerio.U5W2D6.entities.Blogpost;
import valerio.U5W2D6.services.AuthorsService;
import valerio.U5W2D6.services.BlogsService;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    @Autowired
    BlogsService blogsService;
    @Autowired
    AuthorsService authorsService;

    // 1. - POST http://localhost:3001/blogs (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Blogpost saveBlog(@RequestBody BlogPostPayload payload) {
        Blogpost newBlogPost = new Blogpost();
        newBlogPost.setCategory(payload.getCategory());
        newBlogPost.setTitle(payload.getTitle());
        newBlogPost.setCover(payload.getCover());
        newBlogPost.setContent(payload.getContent());
        newBlogPost.setReadingTime(payload.getReadingTime());
        newBlogPost.setAuthor(authorsService.findById(payload.getIdAuthor()));
        return blogsService.save(newBlogPost);
    }

    // 2. - GET http://localhost:3001/blogs
    @GetMapping("")
    public Page<Blogpost> getBlogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        return blogsService.getPosts(page, size, sort);
    }

    // 3. - GET http://localhost:3001/blogs/{id}
    @GetMapping("/{blogId}")
    public Blogpost findById(@PathVariable int blogId) {
        return blogsService.findById(blogId);
    }

    // 4. - PUT http://localhost:3001/blogs/{id} (+ req.body)
    @PutMapping("/{blogId}")
    public Blogpost findAndUpdate(@PathVariable int blogId, @RequestBody Blogpost body) {
        return blogsService.findByIdAndUpdate(blogId, body);
    }

    // 5. - DELETE http://localhost:3001/blogs/{id
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int blogId) {
        blogsService.findByIdAndDelete(blogId);
    }
}
