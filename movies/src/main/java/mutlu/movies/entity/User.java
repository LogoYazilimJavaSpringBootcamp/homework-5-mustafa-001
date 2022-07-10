package mutlu.movies.entity;

import com.fasterxml.jackson.annotation.*;
import mutlu.movies.entity.Movie;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
public class User {

    @Id
    @Column(nullable = false)
    private String username;
    private String email;
    private String passwordHash;
    private LocalDateTime premiumUntil;
    @OneToMany(mappedBy = "user")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Movie> movies;


    @JsonIgnore
    @Transient
    public boolean isPremium() {
        if (premiumUntil == null) {
            return false;
        }
        return this.premiumUntil.isAfter(LocalDateTime.now());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public LocalDateTime getPremiumUntil() {
        return premiumUntil;
    }

    public void setPremiumUntil(LocalDateTime premiumUntil) {
        this.premiumUntil = premiumUntil;
    }
}
