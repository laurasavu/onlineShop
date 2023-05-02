package com.practice.onlineShop.entities;

import com.practice.onlineShop.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Set;


@Setter
@Getter
@Table(name="users")
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name="usersequence",allocationSize = 1)
    @GeneratedValue(generator = "usersequence",strategy = GenerationType.SEQUENCE)
    private Integer id;
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

}

