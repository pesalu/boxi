package hello.Service;

import hello.Dao.AccountRepository;
import hello.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        System.out.println("XXXXY " + account);

        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        //authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority(account.getPermission()));

        return new User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                authorities

        );
        //Arrays.asList(new SimpleGrantedAuthority("ADMIN"))
        //Arrays.asList(new SimpleGrantedAuthority(account.getPermission()))
    }

}
