package todo.g.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import todo.g.dto.JWTAuthResponse;
import todo.g.dto.LoginDto;
import todo.g.dto.UserDtoRequest;
import todo.g.dto.UserDtoResponse;
import todo.g.repository.RoleRepository;
import todo.g.repository.UserRepository;
import todo.g.security.JwtTokenProvider;
import todo.g.service.IUserService;

import javax.validation.Valid;

//@Api(value = "Auth controller exposes siginin and signup REST APIs")
@RestController
@CrossOrigin
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

    @Autowired
    private IUserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    //    @ApiOperation(value = "REST API to Signin or Login user to Blog app")
    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDtoRequest userDtoRequest) {

        // add check for username exist in db

        if (userRepository.existsByUsername(userDtoRequest.getUsername()))
            return new ResponseEntity<>("Username is already Taken!", HttpStatus.OK);


        // add check for email exists in DB

        if (userRepository.existsByEmail(userDtoRequest.getEmail()))
            return new ResponseEntity<>("Email is already Taken!", HttpStatus.BAD_REQUEST);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");


//        return new ResponseEntity(userService.registerUser(userDtoRequest), headers, HttpStatus.OK);
        UserDtoResponse response = userService.registerUser(userDtoRequest);


        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        userDtoRequest.getEmail(), userDtoRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(userDtoRequest.getEmail());

        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token,response));


    }

    //    @ApiOperation(value = "REST API to Register or Signup user to Blog app")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }


}
