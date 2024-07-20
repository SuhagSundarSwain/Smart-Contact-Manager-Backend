package com.SCM.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User implements UserDetails {

    @Id
    private String userId;

    @Column(name = "user_name", length = 40)
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "about", length = 1000)
    private String about;
    @Column(name = "profilePic")
    private String profilePic;
    @Column(name = "phone", unique = true)
    private String phoneNumber;

    // Information
    @Column(name = "account_active")
    private boolean enabled = true;//TODO change this enabled value to false
    @Column(name = "email_verified")
    private boolean emailVerified = false;
    @Column(name = "phone_verified")
    private boolean phoneVerified = false;

    // Provider information: Self, Google, GitHub, Facebook
    @Column(name = "Provider")
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    @Column(name = "Provider_user_id")
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection
    private List<String> roleList = new ArrayList<>();

    /**
     * Returns the authorities granted to the user.
     * 
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
