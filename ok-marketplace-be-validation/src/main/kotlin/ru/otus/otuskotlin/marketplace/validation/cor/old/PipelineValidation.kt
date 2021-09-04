package ru.otus.otuskotlin.marketplace.validation.cor.old

class PipelineValidation<C>(
    private val validations: List<IValidationOperation<C, *>>,
) {
    fun exec(context: C) {
        validations.forEach { it.exec(context) }
    }
}
