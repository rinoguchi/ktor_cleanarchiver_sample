package infrastructure.framework

import interfaces.controller.MemoController
import interfaces.controller.MemoController.*
import io.ktor.application.call
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.getOrFail
import org.koin.ktor.ext.inject
import javax.validation.*

fun Routing.root() {
    val validator: Validator by inject()

    route("/memos") {
        val memoController: MemoController by inject()
        get("/{id}") {
            val input = MemoInput(call.parameters.getOrFail<Int>("id"))
            call.respond(transactionWrapper{ memoController.get(input) })
        }
        post("/new") {
            val input = MemoPostInput(call.receiveParameters().getOrFail("body"))
            validator.validateAndThrow(input)
            call.respond(transactionWrapper { memoController.post(input) })
        }
        delete("/{id}") {
            val input = MemoInput(call.parameters.getOrFail<Int>("id"))
            call.respond(transactionWrapper{ memoController.delete(input) })
        }
        put("/{id}") {
            val input = MemoPutInput(call.parameters.getOrFail<Int>("id"), call.receiveParameters().getOrFail("body"))
            validator.validateAndThrow(input)
            call.respond(transactionWrapper{ memoController.put(input) })
        }
    }
}

fun <T> Validator.validateAndThrow(obj: T, vararg groups: Class<*>?) {
    val errors = this.validate(obj, *groups)
    if (errors.size > 0) throw ValidationException(errors.joinToString { e -> "${e.propertyPath}: ${e.message}" })
}