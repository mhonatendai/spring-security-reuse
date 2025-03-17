package com.tenstech.spring_security_reuse.auth;

import lombok.Getter;
import lombok.Setter;

public class AuthDTO {

    @Setter
    @Getter
    public static class UserRequest {
        private String username;
        private String password;
        private String email;
    }

    @Setter
    @Getter
    public static class Response {
        private String message;
        private String token;

        public Response() {
        }

        public Response(String message, String token) {
            this.message = message;
            this.token = token;
        }
    }
}