package alura.challenge.forohub.services;

import alura.challenge.forohub.model.UsuarioModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  // para usar las variables de application.properties
  @Value("${api.security.secret}")
  private String apiSecret;

  // Necesario para crear un token
  public String generarToken(UsuarioModel usuarioModel) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      return JWT.create()
        .withIssuer("forohub")
        .withSubject(usuarioModel.getLogin())
        .withClaim("id", usuarioModel.getId())
        .withExpiresAt(generarFechaExpiracion()) // fecha de expiracion
        .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException();
    }
  }

  // necesario para verficar token
  public String getSubject(String token) {
    if (token == null){
      throw new RuntimeException();
    }
    DecodedJWT verifier = null;
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      verifier = JWT.require(algorithm)
        .withIssuer("forohub")
        .build()
        .verify(token);
      verifier.getSubject();

    } catch (JWTVerificationException exception) {
      System.out.println(exception.toString());
    }
    if (verifier.getSubject() == null){
      throw new RuntimeException("Verifier inválido");
    }
    return verifier.getSubject();
  }

  private Instant generarFechaExpiracion(){
    return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-05:00"));
  }
}
