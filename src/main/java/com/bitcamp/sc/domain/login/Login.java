package com.bitcamp.sc.domain.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Login {


    private String email;
    private String pw;
    private boolean reEmail;
    private String redirectUri;
}
