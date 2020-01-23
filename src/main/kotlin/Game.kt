import java.util.*

class Game {
    val id:String = UUID.randomUUID().toString()
    private val tokens = mutableMapOf<String, Player>()
    var started = false
    var ended = false
    val players:List<Player> get() = tokens.values.toList()

    var internalState:State? = null

    val state:State get() = internalState!!

    fun start() {
        started = true
        internalState = State(players.mapIndexed{i,p -> PlayerState(p.id, i, "Player $i")})
    }

    fun addPlayer() : Pair<String,Int> {
        val player = Player(index = tokens.size + 1)
        val token = UUID.randomUUID().toString()
        tokens[token] = player
        return token to player.index
    }

    fun isPlayer(token:String) : Boolean = tokens.containsKey(token)
    fun getPlayer(token:String) = tokens[token]
    fun makeMove(player: Player, params: Map<String, List<String>>): State {
        if(!validate(player, params)){
            return state
        }
        return state
    }

    fun validate(player: Player, params: Map<String, List<String>>): Boolean {
        if (state.playersTurn != player.index) {
            println("Player ${player.index} tried to make a move, but it wasn't their turn")
            return false
        }
        //TODO figure out which Move it is, do the required actions using the players PlayerBoardState and the map,
        // catch InvalidMove-exceptions and return false, else return true - never replace the State (only do that

        return true
    }
}

data class Player(
    val id:String = UUID.randomUUID().toString(),
    val index:Int
)