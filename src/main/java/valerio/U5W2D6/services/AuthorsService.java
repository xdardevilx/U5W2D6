package valerio.U5W2D6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.U5W2D6.entities.Author;
import valerio.U5W2D6.exceptions.BadRequestExcepition;
import valerio.U5W2D6.exceptions.NotFoundException;
import valerio.U5W2D6.repositories.AuthorDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@Service
public class AuthorsService {
    @Autowired
    private AuthorDAO authorDAO;

//    private final List<Author> authors = new ArrayList<>();

    public Author save(Author newAuthor) {
        this.authorDAO.findByEmail(newAuthor.getEmail()).ifPresent(
                author -> {
                    throw new BadRequestExcepition("L'email " + newAuthor.getEmail() + " è già in uso!");
                }
        );
        newAuthor.setAvatar("https://ui-avatars.com/api/?name="+ newAuthor.getName() + "+" + newAuthor.getSurname());
        this.authorDAO.save(newAuthor);
        return newAuthor;
    }

    public Page<Author> getAuthors(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorDAO.findAll(pageable);
    }


    public Author findById(int authorId) {
        return this.authorDAO.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public void findByIdAndDelete(int id) {
        Author found = this.findById(id);
        this.authorDAO.delete(found);
    }

    public Author findByIdAndUpdate(int id, Author modifiedAuthor) {
        Author found = this.findById(id);
        found.setName(modifiedAuthor.getName());
        found.setSurname(modifiedAuthor.getSurname());
        found.setEmail(modifiedAuthor.getEmail());
        found.setDateOfBirth(modifiedAuthor.getDateOfBirth());
        found.setAvatar("https://ui-avatars.com/api/?name="+ modifiedAuthor.getName() + "+" + modifiedAuthor.getSurname());
        return this.authorDAO.save(found);

    }
}
