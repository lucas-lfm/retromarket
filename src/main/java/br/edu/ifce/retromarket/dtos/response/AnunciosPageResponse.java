package br.edu.ifce.retromarket.dtos.response;

import java.util.List;

public record AnunciosPageResponse(
    List<AnuncioResumoResponse> conteudo,
    int pagina,
    int tamanho,
    long totalElementos,
    int totalPaginas) {

}
