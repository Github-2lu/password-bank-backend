package com.tulu.passwordms.password;

import com.tulu.passwordms.password.models.Password;
import com.tulu.passwordms.password.models.dto.PasswordWithUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passwords")
public class PasswordController {
    final private PasswordService passwordService;

    public PasswordController(PasswordService passwordService){
        this.passwordService = passwordService;
    }

    @GetMapping
    public ResponseEntity<List<Password>> findAll(@RequestParam Long userId){
        return new ResponseEntity<>(passwordService.findAll(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestParam Long userId, @RequestBody Password password){
        if(passwordService.addPassword(userId, password)){
            return new ResponseEntity<>("password added", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("password not created", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{passwordId}")
    public ResponseEntity<PasswordWithUserDTO> findPasswordById(@PathVariable Long passwordId){
        PasswordWithUserDTO password = passwordService.findById(passwordId);
        if(password != null){
            return new ResponseEntity<>(password, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{passwordId}")
    public ResponseEntity<String> deletePasswordById(@PathVariable Long passwordId){
        if(passwordService.deletePasswordById(passwordId)){
            return new ResponseEntity<>("password deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("{passwordId}")
    public ResponseEntity<String> updateUserById(@PathVariable Long passwordId, @RequestBody Password password){
        if(passwordService.updatePasswordById(passwordId, password)){
            return new ResponseEntity<>("password updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("password not found", HttpStatus.NOT_FOUND);
    }
}
