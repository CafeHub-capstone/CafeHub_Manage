package com.CafeHub.Manage.Admin.controller;


import com.CafeHub.Manage.Admin.request.SignupRequest;
import com.CafeHub.Manage.Admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;


    @GetMapping("/signup")
    public String signupPage() {

        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest request) {

        if(!request.getCode().equals("aaa")) {
            System.out.println("인증 코드가 다름");
            return "/login";
        }


        adminService.signup(request);

        return "/login";
    }

    @GetMapping("/login")
    public String loginPage(){

        return "login";
    }

}
