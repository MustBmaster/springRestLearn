package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User setNewUser(User newUser) {
        return this.userRepository.save(newUser);
    }

    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        // làm như dươi đây sẽ báo lỗi, vì hàm findById này trả về kiểu dữ liệu optional
        // tức là nó có thể trả về dữ liệu hoặc không
        // return this.userRepository.findById(id);
        Optional<User> user = this.userRepository.findById(id);
        // kiểm tra có tồn tại kết quả không
        if (user.isPresent()) {
            return user.get();// có thì user.get sẽ ép kiểu optional về user
        }
        // không thì trả null
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    public User updateUser(User user) {
        // làm như bên dưới sẽ có lỗ hổng, vì thằng save là insert or update, nên nếu truyền id chưa có, nó sẽ thêm mới
        // return this.userRepository.save(user);
        // logic đúng phải là: tìm xem có thg user với id đó không rồi mới đi sửa
        // và nên dùng luôn thg tìm user bên trên thay vì chọc lại vào  repo, vì chọc vào repo thì kiểu dữ liệu là optional
        User userInDb = this.getUserById(user.getId());
        if (userInDb!= null) {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userInDb.setEmail(user.getEmail());
            return this.userRepository.save(userInDb);
        }
        return null;
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
