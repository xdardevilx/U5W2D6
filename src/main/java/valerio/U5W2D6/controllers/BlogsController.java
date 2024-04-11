package valerio.U5W2D6.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.U5W2D6.entities.Blogpost;
import valerio.U5W2D6.payloads.BlogPostDTO;
import valerio.U5W2D6.services.AuthorsService;
import valerio.U5W2D6.services.BlogsService;

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
    public BlogPostDTO saveBlog(@RequestBody  @Validated BlogPostDTO payload) {
        Blogpost newBlogPost = new Blogpost();
        newBlogPost.setCategory(payload.category());
        newBlogPost.setTitle(payload.title());
        newBlogPost.setCover(payload.cover());
        newBlogPost.setContent(payload.content());
        newBlogPost.setReadingTime(payload.readingTime());
        newBlogPost.setAuthor(authorsService.findById(payload.idAuthor()));
        blogsService.save(newBlogPost);
        return payload;

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
    public BlogPostDTO findAndUpdate(@PathVariable int blogId, @RequestBody BlogPostDTO body) {
        return blogsService.findByIdAndUpdate(blogId, body);
    }

    // 5. - DELETE http://localhost:3001/blogs/{id
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int blogId) {
        blogsService.findByIdAndDelete(blogId);
    }
}
