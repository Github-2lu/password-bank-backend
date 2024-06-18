package com.tulu.passwordms.password.impl;

import com.tulu.passwordms.password.PasswordRepository;
import com.tulu.passwordms.password.PasswordService;
import com.tulu.passwordms.password.clients.UserClient;
import com.tulu.passwordms.password.mapper.PasswordMapper;
import com.tulu.passwordms.password.models.Password;
import com.tulu.passwordms.password.models.dto.PasswordWithUserDTO;
import com.tulu.passwordms.password.models.externals.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PasswordServiceImpl implements PasswordService {

    final private PasswordRepository passwordRepository;

    @Autowired
    RestTemplate restTemplate;

    final private UserClient userClient;

    public PasswordServiceImpl(PasswordRepository passwordRepository, UserClient userClient) {
        this.passwordRepository = passwordRepository;
        this.userClient = userClient;
    }

    private PasswordWithUserDTO convertToDTO(Password password){
        User user = userClient.getUser(password.getUserId());
//        User user = restTemplate.getForObject("http://USERMS:8082/users/"+password.getUserId(), User.class);
        return PasswordMapper.mapToPasswordWithUserDTO(password, user);
    }

    @Override
    public List<Password> findAll(Long userId) {
        return passwordRepository.findByUserId(userId);
    }

    @Override
    @CircuitBreaker(name = "userBreaker", fallbackMethod = "userBreakerFallback")
    public PasswordWithUserDTO findById(Long id){
        Password password = passwordRepository.findById(id).orElse(null);
        if(password == null){
            return null;
        }
        return convertToDTO(password);
    }
    // this is used to show specific error message when userms is down.
    // Here the name should be exactly same as fallbackMethod defined in above annotation.
    // And the return type have to be same as above function where circuitbraker annotation is used
    private PasswordWithUserDTO userBreakerFallback(Throwable t){
        System.out.println("Dummy");
        return null;
    }

    @Override
    public boolean addPassword(Long userId, Password password) {
        if(userId != null && password != null) {
            password.setUserId(userId);
            passwordRepository.save(password);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deletePasswordById(Long id) {
        Optional<Password> optionalPassword = passwordRepository.findById(id);
        if(optionalPassword.isPresent()){
            passwordRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePasswordById(Long id, Password newPassword) {
        Optional<Password> optionalPassword = passwordRepository.findById(id);
        if(optionalPassword.isPresent()){
            Password oldPassword = optionalPassword.get();
            oldPassword.setName(newPassword.getName());
            oldPassword.setCode(newPassword.getCode());
            oldPassword.setDescription(newPassword.getDescription());
            passwordRepository.save(oldPassword);
            return true;
        }
        return false;
    }
}
