package valerio.U5W2D6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.U5W2D6.entities.Author;

import java.util.Optional;

public interface AuthorDAO extends JpaRepository {

    boolean existsByEmail (String email);
    Optional <Author> findByEmail(String email);

}
