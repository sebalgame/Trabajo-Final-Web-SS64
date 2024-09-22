package backend.project.repositories;

import backend.project.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.client.id = :clientId")
    List<Comment> findByClient(Long clientId);

    @Query("SELECT c FROM Comment c WHERE c.commentType = :type")
    List<Comment> findByCommentType(String type);


}
