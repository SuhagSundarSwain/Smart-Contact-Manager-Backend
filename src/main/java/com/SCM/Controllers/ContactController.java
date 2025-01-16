package com.SCM.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SCM.Entities.Contact;
import com.SCM.Entities.User;
import com.SCM.ErrorResponse.ErrorResponse;
import com.SCM.Forms.ContactForm;
import com.SCM.Helper.AppConstants;
import com.SCM.Services.ContactService;
import com.SCM.Services.Impls.SecurityCustomUserDetailService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    SecurityCustomUserDetailService securityCustomUserDetailService;

    @Autowired
    ContactService contactServices;

    @PostMapping("/add")
    public ResponseEntity<Object> addContact(@Valid @RequestBody ContactForm contactForm, BindingResult rBindingResult,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (rBindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(AppConstants.VALIDATION_ERROR, rBindingResult));
        }
        try {
            String userEmail = userDetails.getUsername();
            User user = (User) securityCustomUserDetailService.loadUserByUsername(userEmail);

            Contact contact = new Contact();

            contact.setName(contactForm.getName());
            contact.setEmail(contactForm.getEmail());
            contact.setAddress(contactForm.getAddress());
            contact.setDescription(contactForm.getDescription());
            contact.setFavorite(contactForm.isFavourite());
            contact.setPhone(contactForm.getPhone());
            contact.setUser(user);

            Contact savedContact = contactServices.addContact(contact, user);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains(AppConstants.DUPLICATE_PHONE_NUMBER_ADDED_ERROR_CODE)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ErrorResponse(AppConstants.DUPLICATE_ERROR, AppConstants.DUPLICATE_ERROR_FIELD_PHONE));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ErrorResponse(AppConstants.DUPLICATE_ERROR, AppConstants.DUPLICATE_ERROR_FIELD_SOMETHING));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }

    }

    @GetMapping("/all-contacts")
    public ResponseEntity<Object> getAllContacts(@AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = (User) securityCustomUserDetailService.loadUserByUsername(userEmail);
        return ResponseEntity.status(HttpStatus.OK).body(user.getContacts());
    }

    @GetMapping("/get-contacts-page")
    public ResponseEntity<Object> getMethodName(@AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        try {
            Page<Contact> pageContact = this.contactServices.getContactByPage(
                    userDetails,
                    pageNumber,
                    pageSize,
                    sortBy,
                    ascending);
            return ResponseEntity.status(HttpStatus.OK).body(pageContact.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable int id) {
        try {
            Contact contact = contactServices.deleteContact(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(contact);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(AppConstants.NOT_FOUND, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

}
