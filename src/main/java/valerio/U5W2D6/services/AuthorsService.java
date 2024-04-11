package valerio.U5W2D6.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import valerio.U5W2D6.entities.Author;
import valerio.U5W2D6.exceptions.BadRequestException;
import valerio.U5W2D6.exceptions.NotFoundException;
import valerio.U5W2D6.payloads.AuthorDTO;
import valerio.U5W2D6.repositories.AuthorDAO;

import java.io.IOException;

@Service
public class AuthorsService {
    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private Cloudinary upImg;

//    private final List<Author> authors = new ArrayList<>();

    public AuthorDTO save(AuthorDTO newAuthor) {
        this.authorDAO.findByEmail(newAuthor.email()).ifPresent(
                author -> {
                    throw new BadRequestException("L'email " + newAuthor.email() + " è già in uso!");
                }
        );

        Author author = new Author(newAuthor.name(), newAuthor.surname(), newAuthor.email(), newAuthor.birthDate(), newAuthor.avatar());
        author.setAvatar("https://ui-avatars.com/api/?name="+ newAuthor.name() + "+" + newAuthor.surname());
        this.authorDAO.save(author);
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

    public AuthorDTO findByIdAndUpdate(int id, AuthorDTO modifiedAuthor) {
        Author found = this.findById(id);
        found.setName(modifiedAuthor.name());
        found.setSurname(modifiedAuthor.surname());
        found.setEmail(modifiedAuthor.email());
        found.setDateOfBirth(modifiedAuthor.birthDate());
        found.setAvatar("https://ui-avatars.com/api/?name="+ modifiedAuthor.name() + "+" + modifiedAuthor.surname());
       this.authorDAO.save(found);
        return modifiedAuthor;

    }

    public Author findByIdAndUploadImg(int authorId, MultipartFile avatar) throws IOException {
        Author found= this.findById(authorId);
        String url = (String) upImg.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(url);
        this.authorDAO.save(found);
        return found;
    }
}
