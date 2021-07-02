package br.com.zupacademy.witer.autor.controller

import br.com.zupacademy.witer.autor.dto.NovoAutorRequest
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class CadastroAutorControllerTest {

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `deve cadastrar um novo autor`() {

        val novoAutorRequest = NovoAutorRequest(nome = "Witer Mendonça", email = "witer@zup.com.br", descricao = "Teste")

        val request = HttpRequest.POST("/autores", novoAutorRequest)

        val response = client.toBlocking().exchange(request, Any::class.java)

        assertEquals(HttpStatus.CREATED, response.status())
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location").matches("/autores/\\d".toRegex()))

    }
}