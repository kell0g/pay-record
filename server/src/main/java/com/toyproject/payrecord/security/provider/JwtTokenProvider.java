package com.toyproject.payrecord.security.provider;

import java.security.Key;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.toyproject.payrecord.domain.employee.Role;
import com.toyproject.payrecord.exception.CustomException;
import com.toyproject.payrecord.service.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

//	@Value("${security.jwt.token.secret-key:secret-key}")
//	private String secretKey;

	// Keys are generally obtained through key generators, certificates, or various
	// Identity classes used to manage keys. Keys may also be obtained from key
	// specifications (transparent representations of the underlying key material)
	// through the use of a key factory (see KeyFactory).
	// Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	Key secretKey;
	
	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds = 3600000; // 1h

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@PostConstruct
	protected void init() {
		secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		//secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String username, List<Role> roles) {

		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority()))
				.filter(Objects::nonNull).collect(Collectors.toList()));

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(secretKey).compact();

		// signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
		log.info(userDetails.getUsername());
		// log.info(userDetails.getAuthorities().forEach(System.out::println));

		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			log.info("validationToken successed!!");
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			log.info("validationToken failed!!");
			throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
