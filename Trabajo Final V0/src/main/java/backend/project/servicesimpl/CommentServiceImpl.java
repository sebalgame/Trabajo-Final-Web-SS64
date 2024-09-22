package backend.project.servicesimpl;

import backend.project.entities.Comment;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.CommentRepository;
import backend.project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment insertComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment insertComment(String commentType, String description, Long clientId) {
        Comment comment = new Comment();
        comment.setCommentType(commentType);
        comment.setDescription(description);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment not found with ID: " + id);
        }
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteComment(Long id, boolean forced) {
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment not found with ID: " + id);
        }

        if (forced) {
            commentRepository.deleteById(id);
        } else {
            Comment comment = commentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Comment not found with ID: " + id));
            commentRepository.delete(comment);
        }
    }

    @Override
    public List<Comment> listAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with ID: " + id));
    }

    @Override
    public List<Comment> findByCommentType(String type) {
        return commentRepository.findByCommentType(type);
    }
}
