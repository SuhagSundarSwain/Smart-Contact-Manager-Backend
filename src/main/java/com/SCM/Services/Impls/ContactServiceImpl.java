package com.SCM.Services.Impls;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Override
    public Page<Contact> getContactByPage(UserDetails userDetails, int pageNumber, int pageSize, String sortBy,
            boolean ascending) {
        Direction direction = ascending ? Direction.ASC : Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        pageNumber = pageNumber > 0 ? pageNumber - 1 : 0; // -1 as page take from index 0
        pageSize = pageSize > 0 ? pageSize : 5; // as pageSize can't be <= 0
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return this.contactRepo.findByUser(userDetails, pageable);
    }

}
