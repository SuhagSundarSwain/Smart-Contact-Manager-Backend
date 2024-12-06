package com.SCM.Services.Impls;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SCM.Entities.Contact;
import com.SCM.Entities.User;
import com.SCM.Repositories.ContactRepo;
import com.SCM.Services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepo contactRepo;

    @Override
    public Contact addContact(Contact contact, User user) {
        contact.setUser(user);
        Contact savedContact = contactRepo.save(contact);

        return savedContact;
    }

    @Override
    public Contact deleteContact(int id) {
        Optional<Contact> optionalContact = contactRepo.findById(id);
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            contactRepo.deleteById(id);
            return contact;
        } else {
            throw new RuntimeException("Contact not found.");
        }

    }

}
