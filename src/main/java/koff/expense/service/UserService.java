package koff.expense.service;

import koff.expense.model.dto.user.UserDTO;
import koff.expense.model.entity.User;
import koff.expense.model.form.user.UserForm;

public interface UserService {
    User insertUser(UserForm user);

    UserDTO getUserByUsername(String username);
}
