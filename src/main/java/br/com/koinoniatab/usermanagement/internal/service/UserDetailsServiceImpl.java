package br.com.koinoniatab.usermanagement.internal.service;

import br.com.koinoniatab.usermanagement.api.service.UserService;
import br.com.koinoniatab.usermanagement.internal.dto.UserAuthDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthDTO user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + username));

        Collection<? extends GrantedAuthority> authorities = getAuthorities(user.roleNames());

        return new User(user.email(),
                user.password(),
                user.active(),
                true,
                true,
                true,
                authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
