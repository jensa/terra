//TODO consider making this a data class so we can copy() it to set new states when making a move, and keeping the old state when we validate
data class State(val players:List<PlayerState>) {

    init {
        incomePhase()
    }

    private fun incomePhase() {

    }

    private fun cleanupPhase(){
        //first to pass this round gets to be first player
        playersTurn = round.playersEndedAt.entries.sortedBy { it.value }.first().key
        if(round.index == 6){
            endGame()
        } else {
            round = Round(round.index+ 1)
            incomePhase()
        }
    }

    private fun endGame() {
    }

    var playersTurn = 1
    var playerOrder = players.mapIndexed { i, _ -> i+1 }
    var round = Round(1)
    val map = TerraMysticaMap()

    fun passTurn(){
        playersTurn = playerOrder[(playerOrder.indexOf(playersTurn)+1) % playerOrder.size] // (playersTurn % players.size) + 1
        //keep track of if everyone passed their turn
        //maybe calculate bonus points here, or do it at each action 
    }

    fun endActionPhase(player:PlayerState){
        round.playersEndedAt[player.index] = System.currentTimeMillis()
        if(round.playersEndedAt.size == players.size) {
            cleanupPhase()
        }
    }

    class Round(val index:Int) {
        val playersEndedAt = mutableMapOf<Int, Long>()
    }

}

data class PlayerState(
    val id: String,
    val index: Int,
    val name:String,
    var boardState:PlayerBoardState)