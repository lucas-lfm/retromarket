package br.edu.ifce.retromarket.dtos.response;

import java.math.BigDecimal;

public record AnuncioResumoResponse(Long id,
    String titulo,
    BigDecimal preco,
    String condicao,
    String completude,
    String status,
    String localizacao,
    PlataformaResumo plataforma,
    CategoriaResumo categoria,
    FotoPrincipal fotoPrincipal) {

  public record PlataformaResumo(Long id, String nome) {
  }

  public record CategoriaResumo(Long id, String nome) {
  }

  public record FotoPrincipal(String url) {
  }

}
