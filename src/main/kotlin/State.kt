
class State(val players:List<PlayerState>) {

}

data class PlayerState(val id: String, val index: Int, val name:String)