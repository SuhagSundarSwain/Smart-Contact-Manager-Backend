package com.SCM.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class User {
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
    @Column(name = "phone", length = 10)
    private String phoneNumber;

    // information
    @Column(name = "account_active")
    private boolean enabled = false;
    @Column(name = "email_verified")
    private boolean emailVerified = false;
    @Column(name = "phone_verified")
    private boolean phoneVerified = false;

    // Self,google,github,facebook
    @Column(name = "Provider")
    private Providers provider = Providers.SELF;
    @Column(name = "Provider_user_id")
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();

}
