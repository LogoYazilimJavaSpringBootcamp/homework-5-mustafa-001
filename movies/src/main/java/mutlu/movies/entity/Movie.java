package mutlu.movies.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    private String name;

    @ManyToOne
    @JoinColumn()
    private User user;

    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private Set<Comment> commentSet;

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
