package game.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class PlayerEntity {


    @Id
    private String name;
    private String password;
    private String email;
    private String avatarUrl;
    private LocalDateTime registered= LocalDateTime.now();

    public PlayerEntity() {

    }

    public PlayerEntity(String name, String password, String email, String avatarUrl) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }

    public PlayerEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }


    @Override
    public String toString() {
        return "PlayerEntity{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", registered=" + registered +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }
}
