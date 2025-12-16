package com.auth.service.securit;

import com.auth.service.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Slf4j
public class JwtUtil {

    private static final String KEY = "Aldier_envio_email";
    private static final long  EXPIRATION_TIME = 1000 * 60 * 60;

    public static Map<String, Object> generateToken( User usr){

        String token = JWT.create()
                .withSubject(usr.getEmail()) // define o assunto, o que o token se refere.
                .withClaim("e-mail", usr.getEmail())// adiciona uma clain(reivindicação) personalizada ao payload do jwt.
                .withClaim("Role", usr.getRole())
                .withIssuedAt(new Date()) // define o momento em que o token foi emitido.
                .withExpiresAt(new Date(System.currentTimeMillis()+ EXPIRATION_TIME)) // difine quando vai expirar
                .sign(Algorithm.HMAC256(KEY)); // finaliza o processo, calcua a assinatura do token e retorna a stirng jwt.
        return generateAuthResponse(token);
    }

    public static boolean validateToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("token invalid: ", e);
            return false;
        }
    }

    public static String getTokenHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }


    public static String getUserNameFromToken(String token){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(KEY)).build().verify(token);
        return decodedJWT.getSubject();
    }

    private static Map<String, Object> generateAuthResponse(String token) {
        Map<String, Object> response = new HashMap<>();

        response.put("token", token);
        response.put("type", "Bearer");
        response.put("expires_in", 3600);

        return response;
    }
}
