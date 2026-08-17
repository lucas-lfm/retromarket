package br.edu.ifce.retromarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifce.retromarket.dtos.response.AnuncioResumoResponse;
import br.edu.ifce.retromarket.dtos.response.AnunciosPageResponse;
import br.edu.ifce.retromarket.entities.Anuncio;
import br.edu.ifce.retromarket.entities.Completude;
import br.edu.ifce.retromarket.repositories.AnuncioRepository;
import br.edu.ifce.retromarket.repositories.CompletudeRepository;

@Service
public class AnuncioService {

  @Autowired
  private AnuncioRepository anuncioRepository;

  @Autowired
  private CompletudeRepository completudeRepository;

  public AnunciosPageResponse listar(Pageable pageable) {
    Page<Anuncio> pagina = anuncioRepository.findByStatusCodigoIn(List.of("ATIVO", "RESERVADO"), pageable);

    List<AnuncioResumoResponse> conteudo = pagina.getContent().stream().map(this::toResponse).toList();

    return new AnunciosPageResponse(
        conteudo,
        pagina.getNumber(),
        pagina.getSize(),
        pagina.getTotalElements(),
        pagina.getTotalPages());
  }

  public List<Completude> buscarCompletudes() {
    return completudeRepository.findAll();
  }

  private AnuncioResumoResponse toResponse(Anuncio anuncio) {
    AnuncioResumoResponse.PlataformaResumo plataforma = new AnuncioResumoResponse.PlataformaResumo(
        anuncio.getPlataforma().getId(),
        anuncio.getPlataforma().getNome());

    AnuncioResumoResponse.CategoriaResumo categoria = new AnuncioResumoResponse.CategoriaResumo(
        anuncio.getCategoria().getId(),
        anuncio.getCategoria().getNome());

    AnuncioResumoResponse.FotoPrincipal fotoPrincipal = new AnuncioResumoResponse.FotoPrincipal(
        anuncio.getFotos().getFirst().getUrl());

    return new AnuncioResumoResponse(
        anuncio.getId(),
        anuncio.getTitulo(),
        anuncio.getPreco(),
        anuncio.getCondicao().getCodigo(),
        anuncio.getCompletude().getCodigo(),
        anuncio.getStatus().getCodigo(),
        anuncio.getLocalizacao(),
        plataforma,
        categoria,
        fotoPrincipal);
  }

}
