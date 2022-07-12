package mutlu.movies.entity;

import com.fasterxml.jackson.annotation.*;
import jdk.jfr.Enabled;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "commentId")
public class Comment {
    @Id
    @Column(name = "comment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long commentId;
    private String text;
    @ManyToOne
    @JoinColumn()
    @JsonIdentityReference(alwaysAsId = true)
    private User user;
    @ManyToOne
    @JoinColumn()
//    @JsonBackReference
    private Movie movie;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //setter for user field to be used when deserializing from JSON.
    @JsonProperty("user")
    public void setUser(Long userId) {
        var user = new User();
        user.setUserId(userId);
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
