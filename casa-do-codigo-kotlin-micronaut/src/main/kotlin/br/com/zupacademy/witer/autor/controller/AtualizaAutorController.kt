package br.com.zupacademy.witer.autor.controller

import br.com.zupacademy.witer.autor.dto.DetalhesAutorResponse
import br.com.zupacademy.witer.autor.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import javax.transaction.Transactional

@Controller("/autores/{id}")
class AtualizaAutorController(val autorRepository: AutorRepository) {

    @Put
    @Transactional
    fun atualizaAutor(@PathVariable id: Long, descricao: String): HttpResponse<Any> {

        val possivelAutor = autorRepository.findById(id)

        if (possivelAutor.isEmpty) return HttpResponse.notFound()

        val autor = possivelAutor.get()
        autor.descricao = descricao

//        //atualização explícita desnecessária
//        autorRepository.update(autor)

        return HttpResponse.ok(DetalhesAutorResponse(autor))

    }
}