package com.SCM.Services.Impls;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SCM.Entities.User;
import com.SCM.Forms.LoginForm;
import com.SCM.Helper.AppConstants;
import com.SCM.Helper.ResourceNotFoundExeption;
import com.SCM.Repositories.UserRepo;
import com.SCM.Services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User getLogin(LoginForm loginForm) {

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUserName(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User loggedinUser = (User) authentication.getPrincipal();
        loggedinUser.setPassword(null);
        return loggedinUser;

    }

    @Override
    public User saveUser(User user) {

        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        logger.info(user.getProvider().toString());
        User result = this.userRepo.save(user);
        result.setPassword(null);
        return result;

    }

    @Override
    public Optional<User> getUserById(String id) {
        Optional<User> user = this.userRepo.findById(id);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User existUser = this.userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundExeption("User Not Found!!"));
        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        existUser.setAbout(user.getAbout());
        existUser.setEnabled(user.isEnabled());
        existUser.setEmailVerified(user.isEmailVerified());
        existUser.setPassword(user.getPassword());
        existUser.setPhoneNumber(user.getPhoneNumber());
        existUser.setPhoneVerified(user.isPhoneVerified());
        existUser.setProfilePic(user.getProfilePic());
        existUser.setProvider(user.getProvider());
        existUser.setProviderUserId(user.getProviderUserId());
        this.userRepo.save(existUser);
        return existUser;

    }

    @Override
    public void deleteUser(String id) {
        User existUser = this.userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("User Not Found!!"));
        this.userRepo.delete(existUser);
    }

    @Override
    public boolean isUserExist(String id) {
        Optional<User> existUser = this.userRepo.findById(id);
        return existUser != null;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        Optional<User> existUser = this.userRepo.findByEmail(email);
        return existUser != null;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    public boolean deleteAllUsers() {
        try {
            this.userRepo.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
