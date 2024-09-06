package tmdtdemo.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tmdtdemo.tmdt.entity.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment , Long> {
    Comment getCommentById(Long id);
}
