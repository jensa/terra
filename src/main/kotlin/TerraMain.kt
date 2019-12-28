import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.application.*
import io.ktor.http.Cookie
import io.ktor.http.HttpStatusCode
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.pipeline.PipelineContext


fun main() {

    val games = mutableMapOf<String, Game>()
    embeddedServer(Netty, 8080) {
        routing {
            get("/game/new") {
                val game = Game()
                games[game.id] = game
                respond(game)
            }
            get("/game/join/{id}") {
                val player = Player()
                val game = games[call.parameters["id"]]
                val token = game?.addPlayer(player)
                if(token == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    println(token.first)
                    call.response.cookies.append(Cookie("token", token.first))
                    respond(token.second)
                }
            }
            get("/game/start/{id}") {
                val game = games[call.parameters["id"]]
                validatePlayer(games[call.parameters["id"]], getToken())
                game?.started = true
                respond(Any())
            }
            get("/game/move/{id}") {
                val player = validatePlayer(games[call.parameters["id"]], getToken())
                val game = games[call.parameters["id"]]!!
                val valid = game.makeMove(player)
                respond(mapOf("valid" to valid, "ended" to game.ended, "state" to game.state))
            }
        }
    }.start(wait = true)
}

fun validatePlayer(game:Game?, token:String?) : Player {
    if(game == null || token == null || !game.isPlayer(token)){
        throw IllegalAccessException()
    }
    return game.getPlayer(token)!!
}



fun PipelineContext<Unit, ApplicationCall>.getToken():String? = call.request.cookies["token"]
suspend fun PipelineContext<Unit, ApplicationCall>.respond(o:Any)= call.respondText(toJson(o))

val mapper: ObjectWriter = jacksonObjectMapper().writerWithDefaultPrettyPrinter()
fun toJson(o:Any): String = mapper.writeValueAsString(o)

