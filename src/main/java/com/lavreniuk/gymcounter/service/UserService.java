package com.lavreniuk.gymcounter.service;

import com.lavreniuk.gymcounter.domain.User;
import com.lavreniuk.gymcounter.exceptions.SuchEmailAlreadyExistsException;
import com.lavreniuk.gymcounter.exceptions.SuchUsernameAlreadyExistsException;
import com.lavreniuk.gymcounter.repository.UserRepo;
import com.lavreniuk.gymcounter.security.utils.AuthenticationUtils;
import com.lavreniuk.gymcounter.utils.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author taras
 * @date 15.04.18.
 */
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserService() {
        System.out.println("UserService constructor.");
    }

    public User getByUsername(String username) {
        return userRepo.getByUsername(username);
    }

    @Transactional
    public User getById(Long id) {
        return userRepo.getOneById(id);
    }

    public Long createUser(final String username, final String password, final String email, final String phone)
            throws SuchUsernameAlreadyExistsException, SuchEmailAlreadyExistsException {
        checkIfUserExists(username, email);
        final String hashedPassword = PasswordHashing.hashPassword(password);
        return userRepo.createUser(username, hashedPassword, email, phone);

    }

    private void checkIfUserExists(String username, String email) throws SuchUsernameAlreadyExistsException, SuchEmailAlreadyExistsException {
        List<User> users = userRepo.getByUsernameOrEmail(username, email);
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                throw new SuchUsernameAlreadyExistsException();
            }
            if (user.getEmail().equals(email)) {
                throw new SuchEmailAlreadyExistsException();
            }
        }
    }

    @Transactional
    public User updateUser(User user) {
        User userToUpdate = userRepo.getOneById(AuthenticationUtils.getCurrentUserId());
        if (user.getFirstName() != null) {
            userToUpdate.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userToUpdate.setLastName(user.getLastName());
        }
        if (user.getPassword() != null) {
            userToUpdate.setPassword(PasswordHashing.hashPassword(user.getPassword()));
        }
        if (user.getSex() != null) {
            userToUpdate.setSex(user.getSex());
        }
        if (user.getWeight() != null) {
            userToUpdate.setWeight(user.getWeight());
        }
        if (user.getHeight() != null) {
            userToUpdate.setHeight(user.getHeight());
        }
        userRepo.save(userToUpdate);
        return userToUpdate;
    }
}