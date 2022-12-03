package ru.practicum.explorewithme.comment.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.comment.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByEventId(long eventId, Pageable page);
    List<Comment> findAllByUserId(long userId, Pageable page);

}
