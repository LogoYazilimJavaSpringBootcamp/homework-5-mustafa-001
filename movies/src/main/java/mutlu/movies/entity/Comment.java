package mutlu.movies.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.jfr.Enabled;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @Column(name = "comment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long commentId;
    @ManyToOne
    @JoinColumn()
    private User user;
    private String text;
    @ManyToOne
    @JoinColumn()
    @JsonBackReference
    private Movie movie;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
