package vn.hoidanit.jobhunter.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.RestResponse;
import vn.hoidanit.jobhunter.domain.dto.LoginDTO;
import vn.hoidanit.jobhunter.util.SecurityUtil;


@RestController
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtil securityUtil) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
    }



    // @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping("/login")
    public ResponseEntity<RestResponse> login(@Valid @RequestBody LoginDTO loginDTO){
        // tạo token bằng dữ liệu request đăng nhập
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        //xác thực người dùng (hàm này chưa chạy, mới trả về đối tượng dto thôi)
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String access_token=this.securityUtil.createToken(authentication); // tạo access token
        //tạo 1 cái contexHolder để lưu trữ thông tin người dùng, lưu giá trị token, tiện cho các lần rq sau
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //tạo response trả về kq
        RestResponse res = new RestResponse();
        res.setStatus(200);
        res.setMessage("Login success");
        res.setData(access_token);
        return ResponseEntity.ok().body(res);
    }
}
