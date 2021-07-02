package br.com.zupacademy.witer.autor.controller

import br.com.zupacademy.witer.autor.dto.DetalhesAutorResponse
import br.com.zupacademy.witer.autor.repository.AutorRepository
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import javax.transaction.Transactional

@Controller("/autores")
class BuscaAutoresController(val autorRepository: AutorRepository) {


    //autores?email=  - retorna Page de autores
    //autores?email=jose@gmail.com  - retorna Autor email jose@gmail.com
    //autores - retorna Page autores
    @Get
    @Transactional
    fun listaAutores(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if (email.isBlank())
        //retorna Page de Autores.
            return HttpResponse.ok(autorRepository.findAll(Pageable.from(0, 3))
                .map { autor -> DetalhesAutorResponse(autor) })

//        //retorna Lista de Autores.
//        return HttpResponse.ok(autorRepository.findAll().map { autor -> DetalhesAutorResponse(autor) })

        val autor = autorRepository.findByEmail(email)
        if (autor.isEmpty) return HttpResponse.notFound()

        return HttpResponse.ok(DetalhesAutorResponse(autor.get()))
    }

    //autores/1 - retorna autor de id 1
    @Get("/{id}")
    @Transactional
    fun buscaAutorPorId(@PathVariable id: Long): HttpResponse<DetalhesAutorResponse> {
        val autor = autorRepository.findById(id)

        if (autor.isEmpty) return HttpResponse.notFound()

        return HttpResponse.ok(DetalhesAutorResponse(autor.get()))
    }

    //autores/nome/ana  - retorna autora nome ana
    @Get("/nome/{nome}")
    @Transactional
    fun buscaAutorPorNome(@PathVariable nome: String): HttpResponse<DetalhesAutorResponse> {
        val autor = autorRepository.buscaPorNome(nome)

        if (autor.isEmpty) return HttpResponse.notFound()

        return HttpResponse.ok(DetalhesAutorResponse(autor.get()))
    }

}