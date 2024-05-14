package com.CafeHub.Manage.Admin.service;


import com.CafeHub.Manage.Admin.entity.Admin;
import com.CafeHub.Manage.Admin.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepositroy adminRepositroy;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void signup(SignupRequest request) {

        Admin admin = Admin.builder()
                .username(request.getUsername())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .role("USER_ADMIN")
                .name(request.getName())
                .build();

        adminRepositroy.save(admin);
    }
}
