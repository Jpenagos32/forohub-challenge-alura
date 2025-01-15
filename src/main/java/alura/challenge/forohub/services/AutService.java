//package alura.challenge.forohub.services;
//
//import alura.challenge.forohub.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
///*
//* Esta clase es usada para la configuracion de spring security
//* */
//
//@Service
//public class AutService implements UserDetailsService {
//
//  @Autowired
//  UsuarioRepository usuarioRepository;
//
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    return usuarioRepository.findByUsername(username);
//  }
//}
