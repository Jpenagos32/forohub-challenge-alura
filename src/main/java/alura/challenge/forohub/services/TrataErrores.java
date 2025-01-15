package alura.challenge.forohub.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Esta clase es utilizada para poder tratar los errores
// Con la anotación RestControllerAdvice no es necesario llamar ninguno de estos métodos en otras clases
// spring se encarga de hacerlo automáticamente
@RestControllerAdvice
public class TrataErrores {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity error404(){
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity errorDatosDuplicados(DataIntegrityViolationException e){
    return ResponseEntity.badRequest().body("ERROR: Ya existe un post con el mismo titulo");
  }
}
