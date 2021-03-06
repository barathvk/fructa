package com.xing.coding.challenge.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import static java.util.Collections.emptyList;

@SuppressWarnings("unchecked")
class AuthenticationService {
  private static final long EXPIRATIONTIME = 864_000_000;
  private static final String SECRET = "ThisIsASecret";
  private static final String TOKEN_PREFIX = "Bearer";
  private static final String HEADER_STRING = "Authorization";
  static void addAuthentication(HttpServletResponse res, String username) throws IOException {
    String JWT = Jwts.builder()
      .setSubject(username)
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
      .signWith(SignatureAlgorithm.HS512, SECRET)
      .compact();
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    res.getWriter().write(TOKEN_PREFIX + " " + JWT);
    res.getWriter().flush();
    res.getWriter().close();
  }
  static Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      String user = Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
        .getBody()
        .getSubject();
      return user != null ?

        new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
        null;
    }
    return null;
  }
}
