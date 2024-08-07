package koff.expense.service.implement;

import koff.expense.exception.ResourceNotFoundException;
import koff.expense.model.dto.user.UserDTO;
import koff.expense.model.entity.User;
import koff.expense.model.form.user.UserForm;
import koff.expense.repository.IUserRepository;
import koff.expense.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User insertUser(UserForm userForm) {
        User user = convertFormToEntity(userForm);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.insert(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User not found with username: " + username)
        );
        return convertEntityToDTO(user);
    }

    private User convertFormToEntity(UserForm userForm) {
        return modelMapper.map(userForm, User.class);
    }

    private UserDTO convertEntityToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
