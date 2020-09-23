package com.winter.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName : VerificationCodeException
 * @Description : 验证码校验失败异常
 * @Author : Winter
 * @Date: 2020-09-23 17:34
 */
public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException() {
        super("图形验证码校验失败！");
    }

}
