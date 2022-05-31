package com.pv41.rentalproperty.service;

import com.pv41.rentalproperty.exceptions.LoginUnavailableException;
import com.pv41.rentalproperty.model.Role;
import com.pv41.rentalproperty.model.Status;
import com.pv41.rentalproperty.model.User;
import com.pv41.rentalproperty.repository.RoleRepository;
import com.pv41.rentalproperty.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(User user) throws AddressException, LoginUnavailableException {

        validateMail(user.getLogin());
        checkLoginAvailability(user.getLogin());

        Role roleUser = roleRepository.findByName("ROLE_BASE");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        log.info("User {} successfully registered", registeredUser);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("{} users found", result.size());
        return result;
    }

    @Override
    public User findByLogin(String login) {
        User result = userRepository.findByLogin(login);
        log.info("User {} found by login {}", result, login);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("No user found by id {}", id);
            return null;
        }
        log.info("User {} found by id {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("User with id {} successfully deleted", id);
    }

    private void validateMail(String mail) throws AddressException {
        InternetAddress emailAddress = new InternetAddress(mail);
        emailAddress.validate();
    }

    private void checkLoginAvailability(String login) throws LoginUnavailableException {
        if (userRepository.findByLogin(login) != null) {
            throw new LoginUnavailableException();
        }
    }
}
