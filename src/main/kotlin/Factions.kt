import kotlin.math.abs

enum class Faction(
    val coins:Int,
    val workers:Int,
    val cultMoves: List<Cult>,
    val spadeSteps:Int,
    val spadeStepCost: Cost,
    val hasShippingTrack:Boolean, //if faction has track, it always costs 1 priest 4 coin to advance, gives 2,3,4 VP
    val terraformCosts: Map<Terrain, Int>,
    val powerBowls: PowerBowls,
    val sanctuaryTrack:Pair<Cost, Income>,
    val strongholdTrack:Pair<Cost,Income>,
    val strongholdMove:Move? = null,
    val dwellingTrack:Pair<Cost, List<Income>> = Cost(workers = 1, coins = 2) to normalDwellingTrack(),
    val tradingHouseTrack:Pair<Cost, List<Income>> = Cost(workers = 2, coins = 6) to normalTradingHouseTrack(),
    val templeTrack:Pair<Cost, List<Income>> = Cost(workers = 2, coins = 5) to normalTempleTrack(),
    val favorTokensPerTemple:Int = 1

) {
    ChaosMagicians(15,4, listOf(Cult.Fire, Cult.Fire), 3, Cost(workers = 2, coins=5, priests = 1),
        true, terrainWheel(Terrain.Red), PowerBowls(5,7),
        Cost(workers = 4, coins = 8) to Income(priests = 1),
        Cost(workers = 4, coins = 4) to Income(workers = 2),
        strongholdMove = DoubleTurn()
    )
}

fun normalDwellingTrack() = (0..8).map { Income(workers = 1) }
fun normalTradingHouseTrack() = (0..1).map { Income(power = 1, coins = 2) } + (0..1).map { Income(power = 2, coins = 2) }
fun normalTempleTrack() = (0..2).map { Income(priests = 1) }

fun terrainWheel(t:Terrain) : Map<Terrain, Int>{
    val neighbors = mapOf(
        Terrain.Red to listOf(Terrain.Yellow, Terrain.Gray),
        Terrain.Yellow to listOf(Terrain.Red, Terrain.Brown),
        Terrain.Brown to listOf(Terrain.Yellow, Terrain.Black),
        Terrain.Black to listOf(Terrain.Brown, Terrain.Blue),
        Terrain.Blue to listOf(Terrain.Black, Terrain.Green),
        Terrain.Green to listOf(Terrain.Blue, Terrain.Gray),
        Terrain.Gray to listOf(Terrain.Green, Terrain.Red)
    )

    val oneCosts = neighbors.getOrDefault(t, listOf())
    val twoCosts = neighbors.filter { oneCosts.contains(it.key) }.map { it.value }.flatten().filter { it != t }
    val threeCosts = neighbors.filter { twoCosts.contains(it.key) }.map { it.value }.flatten().filter { !twoCosts.contains(it) && !oneCosts.contains(it) }
    return mapOf(t to 0) + oneCosts.map { it to 1 } + twoCosts.map { it to 2 } + threeCosts.map { it to 3 }
}

class PowerBowls(
    start1:Int,
    start2:Int
){
    var first:Int = start1
    var second:Int = start2
    var third:Int = 0


}

// https://boardgamegeek.com/thread/923271/faction-summary



