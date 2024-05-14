package com.CafeHub.Manage.Admin.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    public class SignupRequest {

        private String username;

        private String password;

        private String name;

        private String code;
    }
