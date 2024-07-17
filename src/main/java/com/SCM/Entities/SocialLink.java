package com.SCM.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class SocialLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String link;
    private String title;

    @ManyToOne
    private Contact contact;

}
