package valerio.U5W2D6.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import valerio.U5W2D6.entities.Author;
import valerio.U5W2D6.payloads.AuthorDTO;
import valerio.U5W2D6.services.AuthorsService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    AuthorsService authorsService;

    // 1. - POST http://localhost:3001/authors (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public AuthorDTO saveAuthor(@RequestBody @Validated AuthorDTO authorDTO) throws Exception {
        System.out.println(authorDTO);
        return authorsService.save(authorDTO);
    }

    // 2. - GET http://localhost:3001/authors
    @GetMapping("")
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) throws Exception {
        return authorsService.getAuthors(page, size, sort);
    }

    // 3. - GET http://localhost:3001/authors/{id}
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable int authorId) throws Exception {
        return authorsService.findById(authorId);
    }

    // 4. - PUT http://localhost:3001/authors/{id} (+ req.body)
    @PutMapping("/{authorId}")
    public AuthorDTO findAndUpdate(@PathVariable int authorId, @RequestBody AuthorDTO body) throws Exception {
        return authorsService.findByIdAndUpdate(authorId, body);
    }

    // 5. - DELETE http://localhost:3001/authors/{id}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int authorId) {
        authorsService.findByIdAndDelete(authorId);
    }

    // 6. - POST http://localhost:3001/authors/uploadImg/authorId (+ req.body)

    @PostMapping("/uploadImg/{authorId}")
    public Author uploadImg(@RequestParam("avatar")MultipartFile avatar, @PathVariable int authorId) throws Exception {
        return authorsService.findByIdAndUploadImg(authorId, avatar );

    }
}
