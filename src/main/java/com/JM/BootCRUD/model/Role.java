package com.JM.BootCRUD.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Repository
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String role;

    @Override
    public String toString() {
        return this.role.substring(5);
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return id.equals(role1.id) && role.equals(role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}