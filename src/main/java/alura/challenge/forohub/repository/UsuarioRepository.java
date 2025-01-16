package alura.challenge.forohub.repository;

import alura.challenge.forohub.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {


  UserDetails findByLogin(String username);
}
