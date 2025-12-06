package com.dp.algolab_java_server.infrastructure.security;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.dp.algolab_java_server.domain.entities.User;
import com.dp.algolab_java_server.config.AppProperties;
import com.dp.algolab_java_server.domain.security.TokenPayload;
import com.dp.algolab_java_server.domain.security.TokenProvider;

@Component
@RequiredArgsConstructor
public class JwtProvider implements TokenProvider {
  private final ObjectMapper objectMapper;
  private final AppProperties appProperties;

  private SecretKey getSigningKey() {
    byte[] keyBytes = appProperties.getSecurity().getSecret().getBytes();
    return Keys.hmacShaKeyFor(keyBytes);
  }

  @Override
  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("user", user);

    long expirationTime = appProperties.getSecurity().getJwtExpirationMs();

    return Jwts.builder()
        .claims(claims)
        .subject(user.getId().toString())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expirationTime))
        .signWith(getSigningKey(), Jwts.SIG.HS256)
        .compact();
  }

  @Override
  public TokenPayload verifyToken(String token) {
    try {
      Jws<Claims> jws = Jwts.parser()
          .verifyWith(getSigningKey())
          .build()
          .parseSignedClaims(token);

      Claims claims = jws.getPayload();

      Object userClaim = claims.get("user");
      User user = objectMapper.convertValue(userClaim, User.class);

      return new TokenPayload(user, claims.getExpiration());
    } catch (Exception e) {
      return null;
    }
  }
}
