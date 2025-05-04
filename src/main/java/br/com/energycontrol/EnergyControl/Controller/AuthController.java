package br.com.energycontrol.EnergyControl.Controller;

import br.com.energycontrol.EnergyControl.Config.security.TokenService;
import br.com.energycontrol.EnergyControl.Dto.LoginDTO;
import br.com.energycontrol.EnergyControl.Dto.TokenDTO;
import br.com.energycontrol.EnergyControl.Dto.UserDTO;
import br.com.energycontrol.EnergyControl.Dto.UserRegisterDTO;
import br.com.energycontrol.EnergyControl.Model.User;
import br.com.energycontrol.EnergyControl.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody
            @Valid
            LoginDTO userRegisterDTO
            ) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(userRegisterDTO.email(), userRegisterDTO.password());

        Authentication authentication = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {

        UserDTO userSaved = null;
        userSaved = userService.saveUser(userRegisterDTO);

        return ResponseEntity.ok(userSaved);

    }
}
