package toproject.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toproject.toy.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
