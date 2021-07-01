package br.com.zupacademy.witer.validator

import br.com.zupacademy.witer.autor.repository.AutorRepository
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EmailUnicoValidator::class])
annotation class EmailUnico(
    val message: String = "Email já cadastrado.",
) {

}

@Singleton
class EmailUnicoValidator(val autorRepository: AutorRepository) : ConstraintValidator<EmailUnico, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<EmailUnico>,
        context: ConstraintValidatorContext,
    ): Boolean {
        if (value == null) return true

        val emailExistente = autorRepository.findByEmail(value)
        return emailExistente.isEmpty
    }

}