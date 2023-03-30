package com.meetingroom.book.util;

import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.meetingroom.book.constants.AuthenticationConfigurationConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	public String Sign(String userId) {

		return Jwts.builder().setSubject(userId).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + AuthenticationConfigurationConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(AuthenticationConfigurationConstants.SECRET.getBytes())).compact();

	}

	public String Verify(String token) {

		String userId;

		try {

			userId = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(AuthenticationConfigurationConstants.SECRET.getBytes())).parseClaimsJwt(token).getBody().getSubject();

		} catch (Exception e) {

			userId = null;

		}

		return userId;

	}

}