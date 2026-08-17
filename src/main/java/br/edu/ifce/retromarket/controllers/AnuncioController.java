package br.edu.ifce.retromarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifce.retromarket.dtos.response.AnunciosPageResponse;
import br.edu.ifce.retromarket.entities.Completude;
import br.edu.ifce.retromarket.services.AnuncioService;

@RestController
@RequestMapping(value = "/anuncios")
public class AnuncioController {

  @Autowired
  private AnuncioService service;

  // Método para retornar a lista de
  @GetMapping("/completudes")
  public List<Completude> buscarCompletudes() {
    return service.buscarCompletudes();
  }

  @GetMapping
  public AnunciosPageResponse listarAnuncios(
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "20") int tamanho) {

    Pageable pageable = PageRequest.of(pagina, tamanho);

    return service.listar(pageable);

  }

}
