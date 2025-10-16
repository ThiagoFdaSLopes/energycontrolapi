package br.com.energycontrol.EnergyControl.service;

import br.com.energycontrol.EnergyControl.dto.UserDTO;
import br.com.energycontrol.EnergyControl.dto.UserRegisterDTO;
import br.com.energycontrol.EnergyControl.model.User;
import br.com.energycontrol.EnergyControl.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO saveUser(UserRegisterDTO user){

        String passwordCripto = new BCryptPasswordEncoder().encode(user.user_password());

        User newUser = new User();
        BeanUtils.copyProperties(user,newUser);
        newUser.setUser_password(passwordCripto);
        User userSaved = userRepository.save(newUser);
        return new UserDTO(userSaved);
    }

    public UserDTO searchById(Long id){
        Optional<User> userOptional =
                userRepository.findById(id);

        if (userOptional.isPresent()){
            return new UserDTO(userOptional.get());
        } else {
            throw new RuntimeException("Usuário não existe!");
        }
    }

    public List<UserDTO> listAllUsers(){
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    public void delete(Long id){
        Optional<User> userOptional =
                userRepository.findById(id);

        if (userOptional.isPresent()){
            userRepository.delete(userOptional.get());
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public User update(User user){
        Optional<User> userOptional =
                userRepository.findById(user.getUser_id());

        if (userOptional.isPresent()){
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

}