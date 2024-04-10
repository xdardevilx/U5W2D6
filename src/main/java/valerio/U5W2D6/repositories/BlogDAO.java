package valerio.U5W2D6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.U5W2D6.entities.Blogpost;

public interface BlogDAO extends JpaRepository<Blogpost, Integer> {
}
