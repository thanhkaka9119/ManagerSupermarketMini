package com.vnpt.security.jwt;

import com.vnpt.security.userpincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final String JWT_SECRET = "ahihi";

    private static final Logger LOGGER = LogManager.getLogger(JwtProvider.class);

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 400000L;

    public String createToken(Authentication authentication) {

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }
        return false;
    }

}
