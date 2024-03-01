package kz.wonder.wonderuser.entity;

import jakarta.persistence.*;
import kz.wonder.wonderuser.enums.Role;
import lombok.*;

import java.util.Collection;
import java.util.List;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String keycloakUserId;

    private String keycloakAuthToken;

    private String firstname;

    private String lastname;

    private String phoneNumber;

    private String email;

    private Role role;

}

