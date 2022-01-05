package com.example.epidemicprevention.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author ZZY
 * @Date 2021/10/27
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * token存活时间,24小时
     */
    private Long ACCESS_TOKEN_EXPIRATION = 3600L * 1000 * 24;
    /**
     * refreshToken存活时间,15天
     */
    private Long REFRESH_TOKEN_EXPIRATION = 15 * 24 * 3600L * 1000;
    /**
     * jwt所有者
     */
    private String subject;
    /**
     * jwt签发者
     */
    private String jwt_iss;
    /**
     * jwt密钥
     */
    private String secret;

    /**
     * 生成token
     *
     * @param userId
     * @param role
     * @return
     */
    public String getAccessToken(String userId, String role) {
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        return Jwts.builder().setClaims(claims)
                .setHeader(header)
                .setIssuer(jwt_iss)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

    /**
     * 生成refreshToken
     *
     * @param userId
     * @param role
     * @return
     */
    public String getRefreshToken(String userId, String role) {
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        return Jwts.builder().setClaims(claims)
                .setHeader(header)
                .setIssuer(jwt_iss)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

    /**
     * 管理员
     * 解密Token，得到role
     *
     * @param token：token
     * @return UserDetails
     */
    public String getRole(String token) throws ExpiredJwtException {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return (String) claims.get("role");
    }

    /**
     * 解密Token，得到id
     *
     * @param token：token
     * @return UserDetails
     */
    public String getUserId(String token) throws ExpiredJwtException {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return (String) claims.get("userId");
    }

    /**
     * 解密Token，得到UserDetails
     *
     * @param token：token
     * @param request：httpServletRequest,存储用户信息
     * @return UserDetails
     */
    public UserDetails getUserDetails(String token, HttpServletRequest request) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String userId = (String) claims.get("userId");
        try {
            String role = (String) claims.get("role");
            return User.withUsername(userId).password("123456").roles(role).build();
        } catch (NullPointerException e) {
            return User.withUsername(userId).password("123456").roles("DEFAULT").build();
        }
    }
}
