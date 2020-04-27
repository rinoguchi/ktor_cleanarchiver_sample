package infrastructure

import com.fasterxml.jackson.databind.SerializationFeature
import infrastructure.framework.koinModules
import io.ktor.application.*
import io.ktor.jackson.jackson
import io.ktor.routing.*
import infrastructure.framework.root
import io.ktor.features.*
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.ext.Koin
import javax.validation.ValidationException

fun Application.module() {
    log.info("application starting...")
    install(Koin) {
        modules(listOf(koinModules))
    }
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    routing {
        root()
    }

    install(StatusPages) {
        data class ErrorResponse(val message: String?)

        exception<NotFoundException> {
            call.respond(HttpStatusCode.NotFound, ErrorResponse(it.message))
        }
        exception<BadRequestException> {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(it.message))
        }
        exception<MissingRequestParameterException> {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(it.message))
        }
        exception<ParameterConversionException> {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(it.message))
        }
        exception<ValidationException> {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(it.message))
        }
        exception<Throwable> {
            call.respond(HttpStatusCode.InternalServerError, ErrorResponse(it.message))
        }
    }


    Database.connect(
        url = environment.config.property("app.database.url").getString(),
        user = environment.config.property("app.database.user").getString(),
        password = environment.config.property("app.database.password").getString(),
        driver = "org.postgresql.Driver"
    )

}
