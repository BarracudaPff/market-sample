package ru.otus.otuskotlin.marketplace.validation.cor.old

interface IValidationOperation<C, T> {
    fun exec(context: C)
}

