package valerio.U5W2D6.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.U5W2D6.entities.Blogpost;
import valerio.U5W2D6.exceptions.NotFoundException;
import valerio.U5W2D6.payloads.BlogPostDTO;
import valerio.U5W2D6.repositories.BlogDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@Service
public class BlogsService {

    @Autowired
    private BlogDAO blogPostDAO;

    public Blogpost save(Blogpost blogpost) {

        blogpost.setCover("https://picsum.photos/200/300");
        return this.blogPostDAO.save(blogpost);

    }

    public Page<Blogpost> getPosts(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogPostDAO.findAll(pageable);
    }

    public Blogpost findById(int postId) {
        return this.blogPostDAO.findById(postId).orElseThrow(() -> new NotFoundException(postId));
    }

    public void findByIdAndDelete(int id) {
        Blogpost found = this.findById(id);
        this.blogPostDAO.delete(found);
    }

    public BlogPostDTO findByIdAndUpdate(int id, BlogPostDTO body) {
        Blogpost found = this.findById(id);
        found.setCover(body.cover());
        found.setTitle(body.title());
        found.setCategory(body.category());
        found.setContent(body.content());
        found.setReadingTime(body.readingTime());
        this.blogPostDAO.save(found);
        return body;

    }
}