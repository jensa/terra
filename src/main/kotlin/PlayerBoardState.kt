data class PlayerBoardState(
    val faction: Faction,
    val capital:Capital = faction.startResources.copy(),
    val powerBowls:PowerBowls = faction.powerBowls,
    val victoryPoints:Int = 20,
    val buildings:List<Building> = listOf()) {

    fun addSpades(amount:Int) : PlayerBoardState = copy(
        capital = capital.copy(spades = capital.spades + amount),
        powerBowls = if(faction == Faction.Alchemists && hasStronghold()) powerBowls.increase(amount * 2) else powerBowls.copy(),
        victoryPoints = if(faction == Faction.Halflings) victoryPoints + amount else victoryPoints)

    fun hasStronghold() = buildings.any { it == Building.STRONGHOLD }

    companion object {
        fun randomFaction() = PlayerBoardState(Faction.values().random())
    }
}
