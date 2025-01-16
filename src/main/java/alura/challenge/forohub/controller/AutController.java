package alura.challenge.forohub.controller;

import alura.challenge.forohub.model.UsuarioModel;
import alura.challenge.forohub.records.DatosAutenticacionUsuario;
import alura.challenge.forohub.records.DatosJwtToken;
import alura.challenge.forohub.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutController {

  // utilizado para el proceso de autenticacion
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
    Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
    var usuarioAutenticado = authenticationManager.authenticate(authToken);
    var jwtToken = tokenService.generarToken((UsuarioModel) usuarioAutenticado.getPrincipal());

    return ResponseEntity.ok(new DatosJwtToken(jwtToken));
  }

}
