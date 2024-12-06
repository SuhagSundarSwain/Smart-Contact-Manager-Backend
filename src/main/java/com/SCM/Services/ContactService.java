package com.SCM.Services;

import com.SCM.Entities.Contact;
import com.SCM.Entities.User;

public interface ContactService {

    Contact addContact(Contact contact, User user);
    Contact deleteContact(int id);

}
