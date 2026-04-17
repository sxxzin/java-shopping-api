package com.devhunter.shop.service;

import com.devhunter.shop.model.User;
import com.devhunter.shop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepo;

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /** 회원가입 */
    public User register(String username, String password, String email) {
        if (userRepo.existsByUsername(username)) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // 평문 저장 (개선 필요)
        user.setEmail(email);
        return userRepo.save(user);
    }

    /** 로그인 — 비밀번호 평문 비교 */
    public User login(String username, String password) {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}
