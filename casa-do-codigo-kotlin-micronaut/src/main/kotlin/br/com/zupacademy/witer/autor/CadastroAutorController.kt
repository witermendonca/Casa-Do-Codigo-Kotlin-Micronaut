package br.com.zupacademy.witer.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastroAutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastrarAutor(@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {
        autorRepository.save(request.paraAutor())
        return HttpResponse.ok()
    }

}