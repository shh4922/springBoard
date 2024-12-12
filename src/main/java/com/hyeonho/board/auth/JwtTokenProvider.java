package com.hyeonho.board.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private static final long JWT_TOKEN_VALID = 1000*60*60;

    @Value("${jwt.secret}") // 뭐임? -> yml || properties 파일에 설정된 비밀키 가저옴
    private String secret;

    private SecretKey key;

    // 일반적인 init()의 경우, Bean등록 전에 실행됨.
    // PostConstruct 는 Bean 등록후에 호출되는 init()임.
    @PostConstruct
    public void init() {
        String encode = Base64.getEncoder().encodeToString(secret.getBytes());
        key = Keys.hmacShaKeyFor(encode.getBytes());
    }


    public <T> T getClaimFromToken(final String token, final Function<Claims,T> claimsTFunction) {
        if(Boolean.FALSE.equals(validateToken(token)))
            return null;

        final Claims claims = getAllClaimsFromToken(token);

        return claimsTFunction.apply(claims);
    }

    /**
     * token Username 조회
     *
     * @param token JWT
     * @return token Username
     */
    public String getUsernameFromToken(final String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    private Claims getAllClaimsFromToken(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 토큰 만료 일자 조회
     *
     * @param token JWT
     * @return 만료 일자
     */
    public Date getExpirationDateFromToken(final String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * access token 생성
     *
     * @param email token 생성 id
     * @return access token
     */
    public String generateAccessToken(final String email) {
        return generateAccessToken(email, new HashMap<>());
    }


    /**
     * access token 생성
     *
     * @param id token 생성 id
     * @return access token
     */
    public String generateAccessToken(final long id) {
        return generateAccessToken(String.valueOf(id), new HashMap<>());
    }

    public String generateAccessToken(final String id, final Map<String, Object> claims) {
        return doGenerateAccessToken(id, claims);
    }

    private String doGenerateAccessToken(final String id, final Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALID)) // 30분
                .signWith(key)
                .compact();
    }

    /**
     * refresh token 생성
     *
     * @param email token 생성 id
     * @return refresh token
     */
    public String generateRefreshToken(final String email) {
        return doGenerateRefreshToken(email);
    }

    /**
     * refresh token 생성
     *
     * @param id token 생성 id
     * @return refresh token
     */
    public String generateRefreshToken(final long id) {
        return doGenerateRefreshToken(String.valueOf(id));
    }

    /**
     * refresh token 생성
     *
     * @param id token 생성 id
     * @return refresh token
     */
    private String doGenerateRefreshToken(final String id) {
        return Jwts.builder()
                .setId(id)
                .setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALID * 2) * 24)) // 24시간
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(key)
                .compact();
    }


    /**
     * token 검증
     *
     * @param token JWT
     * @return token 검증 결과
     */
    public Boolean validateToken(final String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            log.warn("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
