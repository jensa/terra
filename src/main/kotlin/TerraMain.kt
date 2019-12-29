import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
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
import io.ktor.util.toMap


fun main() {

    val games = mutableMapOf<String, Game>()
    embeddedServer(Netty, 8080) {
        routing {
            get("/game/new") {
                val game = Game()
                games[game.id] = game
                respond(game.id)
            }
            get("/game/join/{id}") {
                val game = games[call.parameters["id"]]
                val token = game?.addPlayer()
                if(token == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    println(token.first)
                    call.response.cookies.append(Cookie("token", token.first, path = "/"))
                    call.response.cookies.append(Cookie("gameId", game.id, path = "/"))
                    respond(token.second)
                }
            }
            get("/game/start") {
                val game = getGame(games)
                validatePlayer(game, getToken())
                game?.start()
                respond(EmptyResponse())
            }
            get("/game/move/validate") {
                val game = getGame(games)
                val player = validatePlayer(game, getToken())
                val valid = game?.validate(player, call.request.queryParameters.toMap())
                respond(mapOf("valid" to valid, "state" to game?.state))
            }
            get("/game/move/make") {
                val game = getGame(games)
                val player = validatePlayer(game, getToken())
                val state = game?.makeMove(player,call.request.queryParameters.toMap())
                respond(mapOf("ended" to game?.ended, "state" to state))
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

class EmptyResponse()

fun PipelineContext<Unit, ApplicationCall>.getToken():String? = call.request.cookies["token"]
fun PipelineContext<Unit, ApplicationCall>.getGame(games:Map<String, Game>):Game? = games[call.request.cookies["gameId"]]
suspend fun PipelineContext<Unit, ApplicationCall>.respond(o:Any)= call.respondText(toJson(o))

val mapper: ObjectWriter = jacksonObjectMapper()
    .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
    .writer()
fun toJson(o:Any): String = mapper.writeValueAsString(o)

