package backend.project.controllers;

import backend.project.entities.Comment;
import backend.project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.insertComment(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createCommentByDetails(@RequestParam String commentType, @RequestParam String description, @RequestParam Long clientId) {
        Comment savedComment = commentService.insertComment(commentType, description, clientId);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/force/{id}")
    public ResponseEntity<Void> deleteCommentForced(@PathVariable Long id, @RequestParam boolean forced) {
        commentService.deleteComment(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Comment>> listAllComments() {
        List<Comment> comments = commentService.listAllComments();
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.findById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<List<Comment>> getCommentsByType(@RequestParam String type) {
        List<Comment> comments = commentService.findByCommentType(type);
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
