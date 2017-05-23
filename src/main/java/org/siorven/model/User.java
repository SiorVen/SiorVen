package org.siorven.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The class that defines a user of the web page
 */
@Entity
@Table(name="USERS")
public class User implements UserDetails, CredentialsContainer {

    public final static String ROLE_ADMIN = "ROLE_ADMIN";
    public final static String ROLE_REPONEDOR = "ROLE_REP";
    private final static String EMAIL_REGEX = "^\\S+@\\S+$";
    private final static String PERMISO_REGEX = "^(" + ROLE_REPONEDOR + "|" + ROLE_ADMIN + ")$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    @NotEmpty(groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{NotEmpty.user.username}")
    @Size(min = 3, max = 15, groups = {SpringFormGroup.class, SpringFormEditGroup.class}, message = "{Size.user.username}")
    private String username;

    @NotEmpty(groups = {PersistenceGroup.class, SpringFormGroup.class}, message = "{NotEmpty.user.password}")
    @Size(min = 3, max = 15, groups = {SpringFormGroup.class}, message = "{Size.user.password}")
    private String password;

    @Column(unique=true)
    @NotEmpty(groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{NotEmpty.user.email}")
    @Pattern(regexp = EMAIL_REGEX, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{Pattern.user.email}")
    private String email;

    @NotEmpty(groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{NotEmpty.user.permission}")
    @Pattern(regexp = PERMISO_REGEX, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class},message = "{Pattern.user.permission}")
    private String permission;

    public User(String username, String password, String email, String permission) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.permission = permission;
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(permission));
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public void eraseCredentials() {
        permission = null;
    }
}
