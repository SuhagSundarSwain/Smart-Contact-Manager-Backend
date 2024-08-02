package com.SCM.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SCM.Entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {

}
