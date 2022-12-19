package toproject.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toproject.toy.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
