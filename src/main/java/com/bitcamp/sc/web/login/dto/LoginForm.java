package com.bitcamp.sc.web.login.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class LoginForm {

    @NotBlank
    @Email(message = "이메일 형식에 맞게 입력해주세요.",regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")
    private String email;
    @NotBlank
    @Pattern(message = "영어, 숫자, 특수기호 모두를 포함해서 8~30자리 입력해 주세요.", regexp = "^(?=.*[a-zA-Z])((?=.*\\d)(?=.*\\W)).{8,20}$")
    private String pw;
    private boolean reEmail;

}
