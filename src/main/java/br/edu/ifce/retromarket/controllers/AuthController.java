package br.edu.ifce.retromarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifce.retromarket.dtos.AuthDTO;
import br.edu.ifce.retromarket.dtos.LoginResponseDTO;
import br.edu.ifce.retromarket.entities.Usuario;
import br.edu.ifce.retromarket.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping(value = "/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO authDTO) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getSenha());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((Usuario) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDTO(token));
  }

}
