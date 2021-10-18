package pl.coderslab.carshop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(userRepository.findByUsername(s).isPresent()){
            return userRepository.findByUsername(s).get();
        } else {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika");
        }

    }
}