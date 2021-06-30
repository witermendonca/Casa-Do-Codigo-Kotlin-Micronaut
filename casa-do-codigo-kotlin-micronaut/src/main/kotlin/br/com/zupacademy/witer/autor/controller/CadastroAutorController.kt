package br.com.zupacademy.witer.autor.controller

import br.com.zupacademy.witer.autor.dto.NovoAutorRequest
import br.com.zupacademy.witer.autor.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastroAutorController(val autorRepository: AutorRepository) {

    @Post
    @Transactional
    fun cadastrarAutor(@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {
        val novoAutor = autorRepository.save(request.paraAutor())

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf("id" to novoAutor.id))
        return HttpResponse.created(uri)
    }

}