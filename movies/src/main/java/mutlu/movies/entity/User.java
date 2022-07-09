package mutlu.movies.entity;

import mutlu.movies.entity.Movie;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "users")
public class User {
    private String email;

    @Id
    @Column(nullable = false)
    private String username;
    private String passwordHash;
    @OneToMany(mappedBy = "user")
    private Set<Movie> movieSet;
    private LocalDateTime premiumUntil;
    @Transient
    public boolean isPremium() {
        if (premiumUntil == null){
            return false;
        }
        return this.premiumUntil.isAfter(LocalDateTime.now());
    }

    public String getUserId() {
        return email;
    }

    public void setUserId(String userId) {
        this.email = userId;
    }

    public User() {
        this.movieSet = Set.of();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<Movie> getMovieSet() {
        return movieSet;
    }

    public void setMovieSet(Set<Movie> movieSet) {
        this.movieSet = movieSet;
    }

    public LocalDateTime getPremiumUntil() {
        return premiumUntil;
    }

    public void setPremiumUntil(LocalDateTime premiumUntil) {
        this.premiumUntil = premiumUntil;
    }
}
