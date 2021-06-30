package br.com.zupacademy.witer.autor

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_autor")
class Autor(
    val nome: String,
    val email: String,
    val descricao: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val dataCriacao: LocalDateTime = LocalDateTime.now()
}
