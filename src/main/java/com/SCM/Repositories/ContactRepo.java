package com.SCM.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import com.SCM.Entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {

    Page<Contact> findByUser(UserDetails userDetails, Pageable pageable);

}
