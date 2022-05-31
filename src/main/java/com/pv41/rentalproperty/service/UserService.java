package com.pv41.rentalproperty.service;

import com.pv41.rentalproperty.exceptions.LoginUnavailableException;
import com.pv41.rentalproperty.model.User;

import javax.mail.internet.AddressException;
import java.util.List;

public interface UserService {

    void register(User user) throws AddressException, LoginUnavailableException;

    List<User> getAll();

    User findByLogin(String login);

    User findById(Long id);

    void delete(Long id);
}
