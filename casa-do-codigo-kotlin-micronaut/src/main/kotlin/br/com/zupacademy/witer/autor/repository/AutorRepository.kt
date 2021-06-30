package br.com.zupacademy.witer.autor.repository

import br.com.zupacademy.witer.autor.Autor
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository : JpaRepository<Autor, Long> {

    fun findByEmail(email: String): Optional<Autor>

//    @Query("select * from tb_autor where tb_autor.nome = :nome", nativeQuery = true)
//    fun buscaPorNome(nome: String): Optional<Autor>

    @Query("select a from Autor a where a.nome = :nome")
    fun buscaPorNome(nome: String): Optional<Autor>

}
