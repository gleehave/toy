package toproject.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toproject.toy.entity.Post;

public class PostRepository extends JpaRepository<Post, Long> {

}
