package com.school.test.service;

import com.school.test.dto.AccountResponseDto;
import com.school.test.entity.User;
import com.school.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<AccountResponseDto> getMyAccountDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        Optional<User> user = this.userRepository.findByUsername(userName);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User foundUser = user.get();
        String[] names = foundUser.getUsername().split(" ");
        StringBuilder initials = new StringBuilder();
        for (String n : names) {
            if (!n.isEmpty())
                initials.append(n.charAt(0));
        }
        AccountResponseDto accountResponseDto = new AccountResponseDto(foundUser.getId(), foundUser.getUsername(), foundUser.getEmail(),initials.toString().toUpperCase());
        return ResponseEntity.ok(accountResponseDto);
    }
}
