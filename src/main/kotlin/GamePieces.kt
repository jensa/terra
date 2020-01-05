
enum class BonusCard {
    PriestIncome, WorkerAnd3Power, SixCoins,
    ShippingTrackAnd3Power, SpadeAnd2Coins, CultAnd4Coins,
    DwellingPointsAnd2Coins, TradingHousePOintsAnd1Worker, StrongholdPointsAnd2Workers
}

enum class ScoringTile {

}

enum class Cult{
    Earth, Water, Fire, Air
}

class Cost(val workers:Int = 0, val coins:Int = 0, val power:Int = 0, val priests:Int=0, val spades:Int=0)
class Income(val workers:Int = 0, val coins:Int = 0, val power:Int = 0, val priests:Int=0, val spades:Int=0)

enum class Terrain {
    Red, Yellow, Brown, Black, Blue, Green, Gray
}