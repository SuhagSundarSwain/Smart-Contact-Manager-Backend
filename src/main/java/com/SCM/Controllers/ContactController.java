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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

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

}
