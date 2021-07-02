package br.com.zupacademy.witer.autor.controller

import br.com.zupacademy.witer.autor.Autor
import br.com.zupacademy.witer.autor.dto.DetalhesAutorResponse
import br.com.zupacademy.witer.autor.repository.AutorRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaAutoresControllerTest{

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {
        autor = Autor(nome = "Witer Mendonça", email = "witer@zup.com.br", descricao = "Teste")
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    fun deveBuscarAutorPorEmailValido(){
        val response = client.toBlocking().exchange("/autores?email=${autor.email}", DetalhesAutorResponse::class.java)

        assertEquals(HttpStatus.OK, response.status())
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body().nome)
        assertEquals(autor.email, response.body().email)
    }
}