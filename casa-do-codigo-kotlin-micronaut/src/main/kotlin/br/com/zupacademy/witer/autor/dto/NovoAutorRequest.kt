package br.com.zupacademy.witer.autor.dto

import br.com.zupacademy.witer.autor.Autor
import br.com.zupacademy.witer.validator.EmailUnico
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field: NotBlank val nome: String,
    @field:NotBlank @field:Email @field:EmailUnico val email: String,
    @field: NotBlank @field: Size(max = 400) val descricao: String,
) {
    fun paraAutor(): Autor {
        return Autor(nome, email, descricao)
    }

}
