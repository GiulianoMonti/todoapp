package todo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import todo.g.dto.SignUpDto;
import todo.g.dto.UserDtoResponse;
import todo.g.model.Role;
import todo.g.model.User;
import todo.g.repository.RoleRepository;
import todo.g.repository.UserRepository;
import todo.g.security.JwtTokenProvider;
import todo.g.service.IUserService;

import java.util.Collections;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @Override
    public UserDtoResponse registerUser(SignUpDto dto) {
        User userToSave = SignUpDto.dtoToUser(dto);

        userToSave.setName(dto.getName());
        userToSave.setUsername(dto.getUsername());
        userToSave.setEmail(dto.getEmail());
        userToSave.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        userToSave.setRoles(Collections.singleton(roles));

        User user = userRepository.save(userToSave);

        return UserDtoResponse.UserToDto(user);

    }


}
