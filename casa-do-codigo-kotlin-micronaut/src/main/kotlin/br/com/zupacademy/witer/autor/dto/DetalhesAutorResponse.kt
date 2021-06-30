package br.com.zupacademy.witer.autor.dto

import br.com.zupacademy.witer.autor.Autor

class DetalhesAutorResponse(autor: Autor) {
    val id = autor.id
    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
}
