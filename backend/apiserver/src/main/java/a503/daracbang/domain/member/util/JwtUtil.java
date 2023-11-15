package a503.daracbang.domain.member.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import a503.daracbang.domain.member.exception.MemberErrorCode;
import a503.daracbang.global.exception.CustomException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	private static final long EXPIRATION_TIME = 60 * 60 * 1000L;
	private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
	private final String secretKey;

	public JwtUtil(@Value("${jwt.secret}") final String secretKey) {
		this.secretKey = secretKey;
	}

	public String generateJwt(Long id) {
		Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", id); // id 외 추가 가능

		return Jwts.builder().setClaims(claims)
			.setIssuedAt(new Date()).setExpiration(expirationDate).signWith(SIGNATURE_ALGORITHM, secretKey).compact();
	}

	public String getJwtFromHeader(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String jwt) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
			return true;
		} catch (Exception e) {
			throw new CustomException(MemberErrorCode.INVALID_JWT);
		}

		// catch (MalformedJwtException | SecurityException e) {
		// 	log.error("Invalid JWT signature");
		// } catch (ExpiredJwtException e) {
		// 	log.error("Expired JWT token");
		// } catch (UnsupportedJwtException e) {
		// 	log.error("Unsupported JWT token");
		// } catch (IllegalArgumentException e) {
		// 	log.error("Empty JWT token");
		// }
		// return false;
	}

	public Long getIdFromJwt(String jwt) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody().get("id", Long.class);
	}


}
