package readyInterview.springclaudeproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import readyInterview.springclaudeproject.dto.UserDto;
import readyInterview.springclaudeproject.entity.Role;
import readyInterview.springclaudeproject.entity.User;
import readyInterview.springclaudeproject.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService  jwtService;

    public void save(UserDto userDto) {

        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public String findUser(UserDto userDto) {
        String username = userDto.getUserName();

        User user= userRepository.findByUserName(username)
                .orElseThrow(() ->new AccessDeniedException("Invalid userName and password"));

     if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return jwtService.generateToken(username);
        }
        else {
         throw new AccessDeniedException("UserName and Password Incorrect");
     }

    }
}
