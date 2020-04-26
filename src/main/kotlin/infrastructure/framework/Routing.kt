package infrastructure.framework

import interfaces.controller.MemoController
import interfaces.controller.MemoController.*
import io.ktor.application.call
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.getOrFail
import org.koin.ktor.ext.inject

fun Routing.root() {
    route("/memos") {
        val memoController: MemoController by inject()
        get("/{id}") {
            val input = MemoInput(call.parameters.getOrFail("id").toInt())
            call.respond(transactionWrapper{ memoController.get(input) })
        }
        post("/new") {
            val input = MemoPostInput(call.receiveParameters().getOrFail("body"))
            call.respond(transactionWrapper { memoController.post(input) })
        }
        delete("/{id}") {
            val input = MemoInput(call.parameters.getOrFail("id").toInt())
            call.respond(transactionWrapper{ memoController.delete(input) })
        }
        put("/{id}") {
            val input = MemoInput(call.parameters.getOrFail("id").toInt(), call.receiveParameters().getOrFail("body"))
            call.respond(transactionWrapper{ memoController.put(input) })
        }
    }
}