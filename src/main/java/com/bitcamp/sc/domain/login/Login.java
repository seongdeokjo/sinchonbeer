package com.bitcamp.sc.domain.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class Login {

    @NotEmpty
    private String email;
    @NotEmpty
    private String pw;
    private boolean reEmail;
    private String redirectUri;
}
