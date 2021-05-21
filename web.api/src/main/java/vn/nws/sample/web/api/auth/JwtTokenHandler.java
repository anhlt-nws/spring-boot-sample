package vn.nws.sample.web.api.auth;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import vn.nws.sample.service.util.DateUtils;

/**
 * The Class JwtTokenHandler.
 */
@Component
public final class JwtTokenHandler {

	/** The private key. */
	@Value("${lineschool.private-key}")
	private String privateKeyStr;

	/** The public key. */
	@Value("${lineschool.public-key}")
	private String publicKeyStr;

	/** The application user service. */
	@Autowired
	private AppUserDetailsService applicationUserService;

	/**
	 * Parses the user from token.
	 *
	 * @param token the token
	 * @return the optional
	 */
	Optional<UserDetails> parseUserFromToken(String token) throws Exception {
		// TODO: Need to check black list token

		PublicKey publicKey = this.getPublicKeyFromString(publicKeyStr);
		
		Claims claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
		String username = claims.get("email", String.class);

		return Optional.ofNullable(applicationUserService.loadUserByUsername(username));
	}

	/**
	 * Creates the token for user.
	 *
	 * @param user the user
	 * @return the string
	 */
	public String createTokenForUser(SoUserDetails user) throws Exception {
		LocalDateTime afterOneWeek = LocalDateTime.now().plusDays(1);
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
		PrivateKey privateKey = this.getPrivateKeyFromString(privateKeyStr);
		return Jwts.builder()
				.claim("email", user.getUsername())
				.claim("fullname", user.getFullname())
				.signWith(privateKey, signatureAlgorithm)
				.setExpiration(DateUtils.asDate(afterOneWeek)).compact();
	}

	/**
	 * Gets the private key from string.
	 *
	 * @param privateKeyStr the private key str
	 * @return the private key from string
	 * @throws Exception the exception
	 */
	private PrivateKey getPrivateKeyFromString(String privateKeyStr) throws Exception {
		byte[] privateKeyBytes = Base64.decodeBase64(privateKeyStr);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	/**
	 * Gets the public key from string.
	 *
	 * @param publicKeyStr the public key str
	 * @return the public key from string
	 * @throws Exception the exception
	 */
	private PublicKey getPublicKeyFromString(String publicKeyStr) throws Exception {
		byte[] publicKeyBytes = Base64.decodeBase64(publicKeyStr);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}
}