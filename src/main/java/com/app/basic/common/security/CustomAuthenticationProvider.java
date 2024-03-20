package com.app.basic.common.security;

import com.app.basic.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.CredentialExpiredException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDto user = (UserDto) customUserDetailsService.loadUserByUsername(username);

        try {
            //패스워드 체크
            if (!passwordEncoder.matches(password, user.getUserPassword())) {
                throw new BadCredentialsException("Password is invalid");
            }

            if (!user.isAccountNonExpired()) {
                //계정 만료 여부
                throw new CredentialExpiredException("Account is expired");
            } else if (!user.isAccountNonLocked()) {
                //계정 잠금 여부
                throw new AccountLockedException("Account is locked");
            } else if (!user.isEnabled()) {
                //계정 사용 여부
                throw new LockedException("Can't use account");
            } else if (!user.isCredentialsNonExpired()) {
                //계정 비밀번호 만료 여부
                throw new CredentialExpiredException("Credentials is expired");
            }
        } catch (CredentialExpiredException e) {
            e.printStackTrace();
        } catch (AccountLockedException e) {
            e.printStackTrace();
        }

        //인증이 완료 후 객체 리턴
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}