package br.com.energycontrol.EnergyControl.controller;

import br.com.energycontrol.EnergyControl.dto.UserDTO;
import br.com.energycontrol.EnergyControl.dto.UserRegisterDTO;
import br.com.energycontrol.EnergyControl.model.User;
import br.com.energycontrol.EnergyControl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody UserRegisterDTO user){
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> listAll(){
        return userService.listAllUsers();
    }

    @GetMapping("/users/{userId}")
    public UserDTO searchById(@PathVariable Long userId){
        return userService.searchById(userId);
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId){
        userService.delete(userId);
    }

    @PutMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody User user){
        return userService.update(user);
    }

}
