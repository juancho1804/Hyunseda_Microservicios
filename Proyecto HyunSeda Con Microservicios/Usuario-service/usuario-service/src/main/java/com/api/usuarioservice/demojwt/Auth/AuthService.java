package com.api.usuarioservice.demojwt.Auth;

import com.api.usuarioservice.demojwt.Jwt.JwtService;
import com.api.usuarioservice.exceptions.EnumErrorCodes;
import com.api.usuarioservice.exceptions.UserDomainException;
import com.api.usuarioservice.exceptions.UserError;
import com.api.usuarioservice.models.RoleModel;
import com.api.usuarioservice.models.UserModel;
import com.api.usuarioservice.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String username = user.getUsername();
        String role = user.getAuthorities().toString();
        System.out.println(username);
        System.out.println(role);

        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .role(role)
                .username(username)
                .build();

    }

    public AuthResponse register(RegisterRequest request) throws UserDomainException {
        UserModel user = UserModel.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                //.firstName(request.getFirstName())
                //.lastName(request.getLastName())
                .email(request.getEmail())
                .roleModel(RoleModel.USER)
                .build();
        List<UserError>errors=validateDomain(user);
        if(!errors.isEmpty()){
            throw new UserDomainException(errors);
        }

        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .role(user.getRoleModel().toString())
                .username(user.getUsername())
                .build();

    }

    private List<UserError> validateDomain(UserModel user) {
        List<UserError> errors = new ArrayList<>();
        if(this.userRepository.findByUsername(user.getUsername()).isPresent()){
            errors.add(new UserError(EnumErrorCodes.EXISTENT_FIELD, "name", "El username ya existe"));
        }
        return errors;
    }

}
