
enum class BonusCard {
    PriestIncome, WorkerAnd3Power, SixCoins,
    ShippingTrackAnd3Power, SpadeAnd2Coins, CultAnd4Coins,
    DwellingPointsAnd2Coins, TradingHousePOintsAnd1Worker, StrongholdPointsAnd2Workers
}

enum class FavorToken {

}

enum class ScoringTile {

}

enum class TownTile {

}

enum class Cult{
    Earth, Water, Fire, Air, Any
}

data class Cost(val workers:Int = 0, val coins:Int = 0, val power:Int = 0, val priests:Int=0, val spades:Int=0, val cults:List<Cult> = emptyList())
data class Income(val workers:Int = 0, val coins:Int = 0, val power:Int = 0, val priests:Int=0, val spades:Int=0, val cults:List<Cult> = emptyList(), val dwellings:Int=0)
data class Capital(val workers:Int = 0, val coins:Int = 0, val power:Int = 0, val priests:Int=0, val spades:Int=0, val cults:List<Cult> = emptyList(), val dwellings:Int=0, val shippingTrackSteps:Int = 0, val victoryPoints:Int = 0, val favorTokens:Int = 0)

enum class Terrain {
    Red, Yellow, Brown, Black, Blue, Green, Gray, Water;
    override fun toString(): String = "$ordinal"

    companion object {
        fun allLand() :List<Terrain> = Terrain.values().filter { it != Water }
    }
}

enum class Building(powerValue:Int){
    DWELLING(1), TRADING_HOUSE(2), TEMPLE(2), STRONGHOLD(3), SANCTUARY(3)
}
