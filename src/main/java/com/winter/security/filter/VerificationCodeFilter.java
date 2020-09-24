package com.winter.security.filter;

import com.winter.security.exception.VerificationCodeException;
import com.winter.security.handler.MyAuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName : VerificationCodeFilter
 * @Description : 用于校验验证码的过滤器
 * @Author : Winter
 * @Date: 2020-09-23 17:36
 */
public class VerificationCodeFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler = new MyAuthenticationFailureHandler();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 非登录请求不校验验证码
        if (!"/auth/form".equals(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            try {
                verificationCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (VerificationCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }

        }


    }

    public void verificationCode(HttpServletRequest httpServletRequest) throws VerificationCodeException {
        String requestCode = httpServletRequest.getParameter("captcha");
        HttpSession session = httpServletRequest.getSession();
        String saveCode = (String) session.getAttribute("captcha");
        if (!StringUtils.isEmpty(saveCode)) {
            //随手清除验证码，无论失败成功。客户端应在登陆失败时刷新验证码
            session.removeAttribute("captcha");
        }
        //校验不通过，抛出异常
        if (StringUtils.isEmpty(requestCode) || StringUtils.isEmpty(saveCode) || !requestCode.equals(saveCode)) {
            throw new VerificationCodeException();
        }
    }

}

