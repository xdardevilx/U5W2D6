package valerio.U5W2D6.services;

import org.springframework.stereotype.Service;
import valerio.U5W2D6.entities.Author;
import valerio.U5W2D6.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@Service
public class AuthorsService {

    private final List<Author> authors = new ArrayList<>();

    public Author save(Author author) {
        Random rndm = new Random();
        author.setId(rndm.nextInt());
        author.setAvatar("https://ui-avatars.com/api/?name="+ author.getName() + "+" + author.getSurname());
        this.authors.add(author);
        return author;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public Author findById(int id) {
        Author found = null;

        for (Author author : authors) {
            if (author.getId() == id)
                found = author;
        }
        if (found == null)
            throw new NotFoundException(id);
        return found;
    }

    public void findByIdAndDelete(int id) {
        ListIterator<Author> iterator = this.authors.listIterator();

        while (iterator.hasNext()) {
            Author currentAuthor = iterator.next();
            if (currentAuthor.getId() == id) {
                iterator.remove();
            }
        }
    }

    public Author findByIdAndUpdate(int id, Author author) {
        Author found = null;

        for (Author currentAuthor : authors) {
            if (currentAuthor.getId() == id) {
                found = currentAuthor;
                found.setName(author.getName());
                found.setSurname(author.getSurname());
                found.setId(id);
            }
        }
        if (found == null)
            throw new NotFoundException(id);
        return found;

    }
}
