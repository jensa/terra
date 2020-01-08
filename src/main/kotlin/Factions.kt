import kotlin.math.abs
// https://boardgamegeek.com/thread/923271/faction-summary
enum class Faction(
    val startResources: Capital,
    val terraformCosts: Map<Terrain, Int>,
    val strongholdTrack:Pair<Cost,Income>,
    val spadeStepCost: Cost = Cost(workers = 2, coins=5, priests = 1),
    val spadeSteps:Int = 3,
    val hasShippingTrack:Boolean = true, //if faction has track, it always costs 1 priest 4 coin to advance, gives 2,3,4 VP
    val powerBowls: PowerBowls = PowerBowls(5,7),
    val sanctuaryTrack:Pair<Cost, Income> = Cost(workers = 4, coins = 6) to Income(priests = 1),
    val strongholdMove:Move? = null,
    val strongholdOneTimeBonus:Capital = Capital(),
    val specialMove:Move? = null,
    val dwellingTrack:Pair<Cost, List<Income>> = Cost(workers = 1, coins = 2) to normalDwellingTrack(),
    val tradingHouseTrack:Pair<Cost, List<Income>> = Cost(workers = 2, coins = 6) to normalTradingHouseTrack(),
    val templeTrack:Pair<Cost, List<Income>> = Cost(workers = 2, coins = 5) to normalTempleTrack(),
    val favorTokensPerTemple:Int = 1
) {
    ChaosMagicians(
        Capital(coins = 15, workers = 4, cults = listOf(Cult.Fire, Cult.Fire)),
        terrainWheel(Terrain.Red),
        Cost(workers = 4, coins = 4) to Income(workers = 2),
        sanctuaryTrack = Cost(workers = 4, coins = 8) to Income(priests = 1),
        strongholdMove = DoubleTurn(),
        favorTokensPerTemple = 2
    ),
    Giants(
        Capital(coins = 15, workers = 3, cults = listOf(Cult.Fire, Cult.Air)),
        (Terrain.allLand().filter { it != Terrain.Red }.map { it to 2 } + (Terrain.Red to 0)).toMap(),
        Cost(workers = 4, coins = 6) to Income(power = 4),
        strongholdMove = TwoSpades()
    ),
    Fakirs(
        Capital(coins = 15, workers = 3, cults = listOf(Cult.Air, Cult.Fire)),
        terrainWheel(Terrain.Yellow),
        Cost(workers = 4, coins = 10) to Income(priests = 1),
        spadeSteps = 2,
        hasShippingTrack = false,
        powerBowls = PowerBowls(7,5)
    ),
    Nomads(
        Capital(coins = 15, workers = 2, cults = listOf(Cult.Fire, Cult.Earth), dwellings = 1),
        terrainWheel(Terrain.Yellow),
        Cost(workers = 4, coins = 8) to Income(power = 2),
        tradingHouseTrack = Cost(workers = 2, coins = 6) to lowPowerTradingHouseTrack(),
        strongholdMove = Sandstorm()
    ),
    Halflings(
        Capital(coins = 15, workers = 3, cults = listOf(Cult.Earth, Cult.Air), dwellings = 1),
        terrainWheel(Terrain.Brown),
        Cost(workers = 4, coins = 8) to Income(power = 2),
        spadeStepCost = Cost(workers = 2, coins = 1, priests = 1),
        powerBowls = PowerBowls(3, 9),
        strongholdOneTimeBonus = Capital(spades = 3)
    ),
    Cultists(
        Capital(coins = 15, workers = 3, cults = listOf(Cult.Earth, Cult.Fire)),
        terrainWheel(Terrain.Brown),
        Cost(workers = 4, coins = 8) to Income(power = 2),
        sanctuaryTrack = Cost(workers = 4, coins = 8) to Income(priests = 1),
        strongholdOneTimeBonus = Capital(victoryPoints = 7)
    ),
    Alchemists(
        Capital(coins = 15, workers = 3, cults = listOf(Cult.Water, Cult.Fire)),
        terrainWheel(Terrain.Black),
        Cost(workers = 4, coins = 6) to Income(coins = 6),
        strongholdOneTimeBonus = Capital(power = 12),
        tradingHouseTrack = Cost(workers = 2, coins = 6) to lowPowerTradingHouseTrack(),
        specialMove = SorcerersStone()
    ),
    Darklings(
        Capital(coins = 15, workers = 1, priests = 1, cults = listOf(Cult.Water, Cult.Earth)),
        terrainWheel(Terrain.Black),
        Cost(workers = 4, coins = 6) to Income(power = 2),
        sanctuaryTrack = Cost(workers = 4, coins = 10) to Income(priests = 2),
        strongholdMove = OrdinationOfPriests(),
        spadeSteps = 0
    ),
    Mermaids(
        Capital(coins = 15, workers = 3, cults = listOf(Cult.Water, Cult.Water)),
        terrainWheel(Terrain.Blue),
        Cost(workers = 4, coins = 6) to Income(power = 4),
        sanctuaryTrack = Cost(workers = 4, coins = 8) to Income(priests = 1),
        powerBowls = PowerBowls(3,9),
        strongholdOneTimeBonus = Capital(shippingTrackSteps = 1)
    ),
    Swarmlings(
        Capital(coins = 20, workers = 8, cults = Cult.values().toList()),
        terrainWheel(Terrain.Blue),
        Cost(workers = 5, coins = 8) to Income(power = 4),
        sanctuaryTrack = Cost(workers = 5, coins = 8) to Income(priests = 2),
        templeTrack = Cost(workers = 3, coins = 6) to (0..2).map { Income(priests = 1) },
        tradingHouseTrack = Cost(workers = 3, coins = 8) to (0..2).map { Income(power = 2, coins = 2) } + Income(power = 2, coins = 3),
        dwellingTrack = Cost(workers = 2, coins = 3) to listOf(Income(workers = 2)) + (0..6).map { Income(workers = 1) } + Income(),
        powerBowls = PowerBowls(3,9),
        strongholdMove = UpgradeDwelling()
    ),
    Auren(
        Capital(coins = 15, workers = 3, cults = listOf(Cult.Water, Cult.Air)),
        terrainWheel(Terrain.Green),
        Cost(workers = 4, coins = 6) to Income(power = 2),
        strongholdOneTimeBonus = Capital(favorTokens = 1),
        sanctuaryTrack = Cost(workers = 4, coins = 8) to Income(priests = 1),
        strongholdMove = AdvanceTwoOnCultTrack()
    ),
    Witches(
        Capital(coins = 15, workers = 3, cults = listOf(Cult.Air, Cult.Air)),
        terrainWheel(Terrain.Green),
        Cost(workers = 4, coins = 6) to Income(power = 2),
        strongholdMove = WitchesRide()
    ),
    Engineers(
        Capital(coins = 10, workers = 2, cults = listOf()),
        terrainWheel(Terrain.Gray),
        Cost(workers = 3, coins = 6) to Income(power = 2),
        sanctuaryTrack = Cost(workers = 3, coins = 6) to Income(priests = 1),
        templeTrack = Cost(workers = 1, coins = 4) to listOf(Income(priests = 1), Income(power = 5), Income(priests = 1)),
        tradingHouseTrack = Cost(workers = 1, coins = 4) to (0..2).map { Income(power = 2, coins = 2) } + Income(power = 2, coins = 3),
        dwellingTrack = Cost(workers = 1, coins = 1) to listOf(Income()) + (0..1).map { Income(workers = 1) } + Income() + (0..1).map { Income(workers = 1) } + Income() + (0..1).map { Income(workers = 1) },
        powerBowls = PowerBowls(3, 9),
        specialMove = BuildBridge()
    ),
    Dwarves(
        Capital(coins = 15, workers = 3, cults = listOf(Cult.Earth, Cult.Earth)),
        terrainWheel(Terrain.Gray),
        Cost(workers = 4, coins = 6) to Income(power = 2),
        tradingHouseTrack = Cost(workers = 2, coins = 6) to listOf(Income(power = 1, coins = 3), Income(power = 1, coins = 2), Income(power = 2, coins = 2), Income(power = 2, coins = 3)),
        hasShippingTrack = false
    )
}

fun normalDwellingTrack() = (0..7).map { Income(workers = 1) } + Income()
fun normalTradingHouseTrack() = (0..1).map { Income(power = 1, coins = 2) } + (0..1).map { Income(power = 2, coins = 2) }
fun lowPowerTradingHouseTrack() = (0..1).map { Income(power = 1, coins = 2) } + Income(power = 1, coins = 3) + Income(power = 1, coins = 4)
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
