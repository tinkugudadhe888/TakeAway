package com.elevate.app.takeaway.service;

import com.elevate.app.takeaway.dto.user.User;
import com.elevate.app.takeaway.dto.user.UserAddress;
import com.elevate.app.takeaway.model.UserAddressModel;
import com.elevate.app.takeaway.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    long createUser(UserModel userModel);
    long createUserAddress(UserAddressModel userAddressModel);
    long updateUser(UserModel userModel, long userId);
    long updateUserAddress(UserAddressModel userAddressModel, long userId);
    List<User> getAllUsers();
    List<User> getUsersByCity(String city);
    User getUsersById(long userId);
    List<UserAddress> getUserAddressById(long userId);

    Optional<User> SignIn(String name, String pass);


}
