package com.practice.onlineShop.entities;

import com.practice.onlineShop.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;


@Setter
@Getter
@Table(name="users")
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
private String name;
private String password;
@Embedded
private Adress adress;
private String email;
@ElementCollection
@CollectionTable(name="user_roles",joinColumns = @JoinColumn(name="User_id"))
@Column(name="roles")
private Collection<Roles>roles;

    public User(String name, String password, Adress adress, String email, Collection<Roles> roles) {
        this.name = name;
        this.password = password;
        this.adress = adress;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +

                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", adress=" + adress +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
