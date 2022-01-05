package todo.g.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import todo.g.dto.UserDtoRequest;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public UserDtoResponse registerUser(UserDtoRequest dto) {
        User userToSave = new User();

        userToSave.setName(dto.getName());
        userToSave.setUsername(dto.getUsername());
        userToSave.setEmail(dto.getEmail());
        userToSave.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        userToSave.setRoles(Collections.singleton(roles));

        User user = userRepository.save(userToSave);

        return UserDtoResponse.UserToDto(user);

    }

//    private String loginUserRegister(UserDtoRequest userDtoRequest) {
//        Authentication authentication =
//                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDtoRequest.getEmail(), userDtoRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        final UserDetails userDetails =
//                userDetailsService.loadUserByUsername(userDtoRequest.getEmail());
//
//        return jwtUtil.generateToken(userDetails);
//    }


}
