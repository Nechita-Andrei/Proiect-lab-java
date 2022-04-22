package proiect.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import proiect.domain.Security.Authority;
import proiect.domain.Security.User;
import proiect.repository.security.AuthorityRepository;
import proiect.repository.security.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private void loadUserData() {
        if (userRepository.count() == 0){
            Authority adminRole =
                    authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build())
                    ;
            Authority guestRole =
                    authorityRepository.save(Authority.builder().role("ROLE_GUEST").build())
                    ;
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("test1"))
                    .authority(adminRole)
                    .build();
            User guest = User.builder()
                    .username("guest")
                    .password(passwordEncoder.encode("test1"))
                    .authority(guestRole)
                    .build();
            userRepository.save(admin);
            userRepository.save(guest);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
}
