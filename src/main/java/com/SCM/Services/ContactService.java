package com.SCM.Services;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import com.SCM.Entities.Contact;
import com.SCM.Entities.User;

public interface ContactService {

    Contact addContact(Contact contact, User user);

    Contact deleteContact(int id);

    Page<Contact> getContactByPage(UserDetails userDetails, int pageNumber, int pageSize, String sortBy,
            boolean ascending);

}
