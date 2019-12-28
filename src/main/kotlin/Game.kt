import java.util.*

class Game {
    val id:String = UUID.randomUUID().toString()
    private val tokens = mutableMapOf<String, Player>()
    var started = false
    var ended = false
    val players:List<Player>
        get() = tokens.values.toList()

    var internalState:State? = null

    val state:State get() = internalState!!

    fun start() {
        started = true
        internalState = State(players.mapIndexed{i,p -> PlayerState(p.id, i, "Player $i")})
    }

    fun addPlayer(player:Player) : Pair<String,Int> {
        val token = UUID.randomUUID().toString()
        tokens[token] = player
        return token to tokens.size
    }

    fun isPlayer(token:String) : Boolean = tokens.containsKey(token)
    fun getPlayer(token:String) = tokens[token]
    fun makeMove(player: Player): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

data class Player(
    val id:String = UUID.randomUUID().toString()
)