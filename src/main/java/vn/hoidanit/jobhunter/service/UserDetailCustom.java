package vn.hoidanit.jobhunter.service;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//lý do dòng dưới bị comment là do đối tượng user trả về cuối cùng lại không phải là entity user
// mà là kiểu user của UserDeteil. nó chỉ lấy 2 thông tin là email và password của entity user thôi
import org.springframework.security.core.userdetails.User;
// import vn.hoidanit.jobhunter.domain.User;

@Component("userDetailsService")
public class UserDetailCustom implements UserDetailsService{
    private final UserService userService;

    public UserDetailCustom(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        vn.hoidanit.jobhunter.domain.User user = this.userService.getUserByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with email : " + username);
        }
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
