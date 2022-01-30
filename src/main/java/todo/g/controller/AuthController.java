package todo.g.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import todo.g.dto.JWTAuthResponse;
import todo.g.dto.LoginDto;
import todo.g.dto.UserDtoRequest;
import todo.g.model.Role;
import todo.g.model.User;
import todo.g.repository.RoleRepository;
import todo.g.repository.UserRepository;
import todo.g.security.JwtTokenProvider;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Locale;

//@Api(value = "Auth controller exposes siginin and signup REST APIs")
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    //    @ApiOperation(value = "REST API to Signin or Login user to Blog app")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDtoRequest request) {

        // add check for username exist in db

        if (userRepository.existsByUsername(request.getUsername())) {
            return new ResponseEntity<>("Username is already Taken!", HttpStatus.OK);
        }

        // add check for email exists in DB

        if (userRepository.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>("Email is already Taken!", HttpStatus.BAD_REQUEST);
        }
        // create user object

        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        String token = tokenProvider.generateToken(authentication);

        User returnId =userRepository.findByUsername
                (request.getUsername()).orElseThrow();
        log.info(token);



        return ResponseEntity.ok(new JWTAuthResponse(token, user, "User Created!", returnId.getId()));

    }

    //    @ApiOperation(value = "REST API to Register or Signup user to Blog app")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);


        User user =userRepository.findByUsername
                (loginDto.getUsernameOrEmail()).orElseThrow();


//        System.out.println("dsadasdasddsadasd"+ user.getId());

        return ResponseEntity.ok(new JWTAuthResponse(token, loginDto,user.getId()));
    }


}
