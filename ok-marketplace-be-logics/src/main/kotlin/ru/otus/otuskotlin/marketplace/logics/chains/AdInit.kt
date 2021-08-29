package ru.otus.otuskotlin.marketplace.logics.chains

import ru.otus.otuskotlin.marketplace.backend.common.context.MpContext
import ru.otus.otuskotlin.marketplace.common.cor.ICorExec
import ru.otus.otuskotlin.marketplace.common.cor.chain
import ru.otus.otuskotlin.marketplace.logics.workers.AnswerPrepareChain
import ru.otus.otuskotlin.marketplace.logics.workers.ChainInitWorker
import ru.otus.otuskotlin.marketplace.logics.workers.CheckOperationWorker

object AdInit: ICorExec<MpContext> by chain<MpContext>({
    // Проверка, что операция соответствует выбранному чейну
    add(CheckOperationWorker(MpContext.MpOperations.INIT))
    // Инициализация статуса чейна
    add(ChainInitWorker)
    // Подготовка ответа
    add(AnswerPrepareChain)
}).build()
