package vn.hoidanit.jobhunter.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;



@Service
public class SecurityUtil {
    public static final MacAlgorithm JWT_ALGORITHM =MacAlgorithm.HS512; //Khai báo thuật toán mã hoá
    @Value("${hoidanit.jwt.base64-secret}")//anotation này lấy giá trị từ cấu hình trong file yml
    private String jwtKey;
    @Value("${hoidanit.jwt.token-validity-in-seconds}")
    private long jwtKeyExpiration;

    private final JwtEncoder jwtEncoder;

    public SecurityUtil(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }


    public String createToken(Authentication authentication){
        Instant now = Instant.now();// lấy ra thời gian hiện tại
        Instant validity = now.plus(this.jwtKeyExpiration, ChronoUnit.SECONDS);//cộng với thời gian hết hạn token là ra hạn hết
        // @formatter:off
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuedAt(now)
            .expiresAt(validity)
            .subject(authentication.getName())
            .claim("minhnv1", authentication)
            .build();
        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader,claims)).getTokenValue();
    }
}
