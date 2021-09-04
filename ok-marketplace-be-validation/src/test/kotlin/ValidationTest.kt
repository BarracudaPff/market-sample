import kotlinx.coroutines.runBlocking
import org.junit.Test
import ru.otus.otuskotlin.marketplace.backend.common.context.CorStatus
import ru.otus.otuskotlin.marketplace.backend.common.context.MpContext
import ru.otus.otuskotlin.marketplace.backend.common.models.CommonErrorModel
import ru.otus.otuskotlin.marketplace.common.cor.chain
import ru.otus.otuskotlin.marketplace.validation.ValidationResult
import ru.otus.otuskotlin.marketplace.validation.cor.workers.validation
import ru.otus.otuskotlin.marketplace.validation.validators.ValidatorStringNonEmpty

class ValidationTest {
    @Test
    fun pipelineValidation() {
        val chain = chain<MpContext> {
            validation {
                errorHandler { v: ValidationResult ->
                    if (v.isSuccess) return@errorHandler
                    errors.addAll(v.errors.map { CommonErrorModel(message = it.message) })
                }
                validate<String?> { validator(ValidatorStringNonEmpty()); on { onRequest } }
                validate<String?> { validator(ValidatorStringNonEmpty()); on { this.requestAd.title } }
            }
        }

        val c = MpContext()

        runBlocking {
            chain.build().exec(c)
            println("A: ${c.errors}")
        }
    }
}