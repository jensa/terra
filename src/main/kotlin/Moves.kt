interface Move{
    fun changeState(state:State) : State
}

fun parseMove(params:Map<String, List<String>>) : Move{
    return when(params["move"]?.first()){
        "TransformAndBuild" -> TransformAndBuild.parse(params)
        "AdvanceOnShippingTrack" -> AdvanceOnShippingTrack.parse(params)
        "LowerSpadeExchangeRate" -> LowerSpadeExchangeRate.parse(params)
        "UpgradeStructure" -> UpgradeStructure.parse(params)
        "SendPriestToCult" -> SendPriestToCult.parse(params)
        "UsePowerAction" -> UsePowerAction.parse(params)
        "DoConversion" -> DoConversion.parse(params)
        "Pass" -> Pass.parse(params)
        "DoubleTurn" -> DoubleTurn.parse(params)
        "TwoSpades" -> TwoSpades.parse(params)
        "Sandstorm" -> Sandstorm.parse(params)
        "SorcerersStone" -> SorcerersStone.parse(params)
        "OrdinationOfPriests" -> OrdinationOfPriests.parse(params)
        "UpgradeDwelling" -> UpgradeDwelling.parse(params)
        "AdvanceTwoOnCultTrack" -> AdvanceTwoOnCultTrack.parse(params)
        "WitchesRide" -> WitchesRide.parse(params)
        "BuildBridge" -> BuildBridge.parse(params)
        else -> throw IllegalMove()
    }

}

class TransformAndBuild(val hex:AxialCoordinate, val building:Boolean) : Move {
    companion object {
        fun parse(params:Map<String, List<String>>): TransformAndBuild{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //if faction is fakir, they can pay an extra priest to get +1 shipping temp. This gains them 4 VP
    //if they also have stronghold, it's +2 shipping

    //if faction is Darkling, they pay priests instead of workers
    //If faction is Dwarves, they can pay 2(1w/ stronghold) workers to get 1+ shipping temp. This gains them 4 VP
}

class AdvanceOnShippingTrack() : Move{
    companion object {
        fun parse(params:Map<String, List<String>>): AdvanceOnShippingTrack{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class LowerSpadeExchangeRate() : Move{
    companion object {
        fun parse(params:Map<String, List<String>>): LowerSpadeExchangeRate{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class UpgradeStructure(val hex:AxialCoordinate, val to:Building) : Move{
    companion object {
        fun parse(params:Map<String, List<String>>): UpgradeStructure{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //founding a town:
    // Witches:Get 5 additional Victory points when founding a Town.
    // Swarmlings: Collect 3 Workers when founding a Town.

}

// permanentMove: you can return a priest to the supply (from resources) for 1 move up,
// but the normal move is permanently moving it to the cult track
class SendPriestToCult(val permanentMove:Boolean = true) : Move {
    companion object {
        fun parse(params:Map<String, List<String>>): SendPriestToCult{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

//spades action has an immediate secondary action, but other have null
class UsePowerAction(val powerAction:PowerAction, val secondaryMove:Move?) : Move {
    companion object {
        fun parse(params:Map<String, List<String>>): UsePowerAction{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

enum class PowerAction(val cost:Int) {
    BRIDGE(3), PRIEST(3), WORKERS(4), COINS(4), ONE_SPADE(4), TWO_SPADES(6)
}

class DoConversion(val conversionType:ConversionType, amount:Int) : Move {
    companion object {
        fun parse(params:Map<String, List<String>>): DoConversion{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

enum class ConversionType{
    Power2Priest, Power2Worker, Power2Coin, Priest2Worker, Worker2Coin, PowerOne2PowerTwo, PowerTwo2PowerThree, BurnPower
}


//faction actions
class DoubleTurn() : Move //After building the Stronghold, take 1 Action token. As a Special action (once per Action phase), you may take a double-turn. (On this double-turn, take any 2 Actions one after another – passing is also considered an Action.) Use the Action token to keep track of using this Special action.
{
    companion object {
        fun parse(params:Map<String, List<String>>): DoubleTurn{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class TwoSpades() : Move //After building the Stronghold, take an Action token. As a Special action (once per Action phase), get 2 free Spades to transform a reachable Terrain space into your Home terrain. On this space, you may immediately build a Dwelling by paying its costs. Use the Action token to keep track of using this Special action.
{
    companion object {
        fun parse(params:Map<String, List<String>>): TwoSpades {

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
class Sandstorm() : Move //After building the Stronghold, take an Action token. As a Special action (once per Action phase), you may transform a Terrain space directly adjacent to one of your Structures into your Home terrain (Sandstorm). On this space, you may immediately build a Dwelling by paying its costs. (This ability is not applicable past a River space or Bridge.) Use the Action token to keep track of using this Special action. (The Sandstorm is not considered a Spade.)
{
    companion object {
        fun parse(params:Map<String, List<String>>): Sandstorm{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
class SorcerersStone() : Move // You may trade 1 Victory point for 1 Coin, or 2 Coins for 1 Victory Point anytime and any number of times (Sorcerer’s Stone).
{
    companion object {
        fun parse(params:Map<String, List<String>>): SorcerersStone{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
class OrdinationOfPriests() : Move //After building the Stronghold, you may immediately and only once trade up to 3 Workers for 1 Priest each (Ordination of Priests).
{
    companion object {
        fun parse(params:Map<String, List<String>>): OrdinationOfPriests {

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
class UpgradeDwelling() : Move //After building the Stronghold, take an Action token. As a Special action (once per Action phase), you may upgrade a Dwelling to a Trading house. Neither pay Coins, nor Workers for this Trading house. Use the Action token to keep track of using this Special action.
{
    companion object {
        fun parse(params:Map<String, List<String>>): UpgradeDwelling{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
class AdvanceTwoOnCultTrack() : Move //After building the Stronghold, immediately and only once get 1 Favor tile. Also, take an Action token. As a Special action (once per Action phase), you may advance 2 spaces on a Cult track of your choice (only advancing to space 10 if you have a key). Use the Action token to keep track of using this Special action.
{
    companion object {
        fun parse(params:Map<String, List<String>>): AdvanceTwoOnCultTrack{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
class WitchesRide() : Move //After building the Stronghold, take an Action token. As a Special action (once per Action phase), you may build 1 Dwelling on an unoccupied Forest space. Neither pay 1 Worker, nor 2 Coins for this Dwelling. For this build only, ignore the adjacency rule. You may build on any free Forest space as long as it has been Forest at the beginning of this Action (Witches’ Ride). (Thus, you may not transform a Terrain space beforehand.) Use the Action token to keep track of using this Special action.
{
    companion object {
        fun parse(params:Map<String, List<String>>): WitchesRide{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
class BuildBridge() : Move //As an Action, you may build a Bridge for 2 Workers. (You may take this Action any number of times during a round. You may still build Bridges via the respective Power action.)
{
    companion object {
        fun parse(params:Map<String, List<String>>): BuildBridge{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


class Pass(val newBonus: BonusCard) : Move{
    companion object {
        fun parse(params:Map<String, List<String>>): Pass{

        }
    }

    override fun changeState(state: State): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //   The first player to pass becomes the Starting player for the next round.
    //   When passing, immediately return your Bonus card and take one of the three available ones.

    //Engineers: After building the stronghold, get 3 Victory points for each Bridge connecting two of your Structures.
}


/*
Building a Bridge
By moving 3 Power tokens from Bowl III to Bowl I, you may build a Bridge over a River space. In order to do this, you need to have a Structure on at least one of the Terrain spaces connected by the Bridge. Building the Bridge connects these Terrain spaces and they become directly adjacent to one another.

The Game board indicates where Bridges may be built by depicting unfinished bridges.

Without Shipping, you can only reach a Terrain space beyond a River space by building a Bridge. During your next Action or later, you may then transform this Terrain space and/or build a Dwelling on it (see Action #1).
Structures connected via Bridges count towards the founding of a Town, whereas indirect adjacency via Shipping does not.
Once built, you cannot reclaim your Bridges. Keep in mind that you can only build a total of three Bridges.

1 Spade
By moving 4 Power tokens from Bowl III to Bowl I, you may take Action #1 “Transform and Build” getting 1 free Spade for this purpose.

If this Spade does not suffice to transform a given Terrain space into your Home terrain, you may exchange Workers for the missing Spades – at the current Exchange rate on your Exchange track. (Darklings need to exchange 1 Priest for each missing Spade.)

2 Spades
By moving 6 Power tokens from Bowl III to Bowl I, you may take Action #1 “Transform and Build” getting 2 free Spades for this purpose.

If these Spades do not suffice to transform a given Terrain space into your Home terrain, you may exchange Workers for the missing Spade – at the current Exchange rate on your Exchange track. If you only need one Spade to transform a Terrain space into your Home terrain, you may spend the second Spade on another Terrain space. However, you may not place a Dwelling on this other space.
 */





/*
8) Passing and new Starting player
On your turn, if you cannot or do not want to take any more Action, you have to pass and stop taking Actions for the remainder of this round.

The first player to pass becomes the Starting player for the next round.

When passing, immediately return your Bonus card and take one of the three available ones.

You may get Victory points when returning certain Bonus cards.

When passing and returning the Bonus card depicted on the left, get 1 Victory point for each of your Dwellings on the Game board.

When passing and returning the Bonus card depicted on the left, get 2 Victory points for each of your Trading houses on the Game board.

When passing and returning the Bonus card depicted on the left, get 4 Victory points if you have already built your Stronghold and/or 4 Victory points if you have already built your Sanctuary.

Freely choose which one of the three available Bonus cards you want to take. (You may not immediately take the card that you have just returned.)
You may wish to put the newly taken Bonus card face-down in front of you to indicate that you have already passed.
There is no limitation for how many resources you may keep for the next round.
You may take as many Actions as you wish, even if all other players have already passed. As long as there is at least one player left taking Actions, the current Action phase continues.

 */


/*
https://www.yucata.de/en/Rules/TerraMystica
1) Transform and Build
First, you may change the type of one Terrain space. Then, if you have changed its type to your Home terrain, you may immediately build a Dwelling on that space.

Building a Dwelling
In order to build a Dwelling on a Terrain space, this space needs to be…
of your color (i.e. it needs to be your Home terrain)
unoccupied
directly or indirectly adjacent to one of your Structures
Each Dwelling costs 1 Worker and 2 Coins.

If the current Scoring tile depicts a Dwelling, you get 2 Victory points for each Dwelling you build.

Power via Structures
Building a Dwelling or Upgrading a Structure gives your opponents the possibility to gain Power.
Amount of Power gained is the sum of Power values of opponent's Structures directly adjacent to the new/upgraded Structure.

Price of Power: One less Victory Point than Power gained. It's not allowed to gain less Power to keep the price low.

Transforming a Terrain space
Transforming an unoccupied Terrain space into your Home Terrain (Costs see the Exchange track on your Faction board).
Gain Spades:

You may exchange Workers for Spades
through Power actions
Bonus card
Cult bonus in Phase III (but no possibility to build a Dwelling then)
Scoring tile may give points when using a Spade.

First, you may change the type of one Terrain space. Then, if you have changed its type to your Home terrain, you may immediately build a Dwelling on that space.

Building a Dwelling
In order to build a Dwelling on a Terrain space, this space needs to be…
of your color (i.e. it needs to be your Home terrain)
unoccupied
directly or indirectly adjacent to one of your Structures (“Adjacency” is defined here).
Also, you need to pay its building costs. Each Dwelling costs 1 Worker and 2 Coins. (Exceptions: The Engineers build Dwellings for 1 Worker and 1 Coin, the Swarmlings for 2 Workers and 3 Coins.)

When building, take Structures (incl. Dwellings) from left to right off your Faction board.

If the current Scoring tile depicts a Dwelling, you get 2 Victory points for each Dwelling you build.

Power via Structures
When building a Dwelling or upgrading a Structure, your opponents may gain Power.
Each Structure has a Power value. The Power value of each Structure is depicted to the right of its track on the Faction board:

The Stronghold and Sanctuary have a Power value of 3.
Trading houses and Temples have a Power value of 2.
Dwellings have a Power value of 1.
When building a Dwelling (Action #1) or upgrading a Structure (Action #4), you must inform the owners of Structures directly adjacent to your Structure that they may gain Power (see “The Bowls of Power”).

In order to determine the total number of Power an opponent may gain, add up the Power values of their Structures directly adjacent to your newly built Structure.

[Picture Power by Upgrade]
Example: The Mermaid player (blue) needs to inform the Nomad player (yellow) that he may gain exactly 3 Power due to the newly built blue Dwelling: 1 Power for the yellow Dwelling plus 2 Power for the yellow Temple. The Nomad player does not gain Power for the yellow Trading house as it is not directly adjacent to the blue Dwelling.

Details
You do not gain Power for your own Structures. Power can only be gained when an opponent builds Structures.
Beginning with the player to the left of the active player and in clockwise order, each affected opponent needs to decide whether to actually gain the Power or not. (The next section explains why you might want to refuse to gain Power.)
The price of Power
Unfortunately, Power gained via Structures is not free. To gain that Power, you need to lose a number of Victory points equal to one fewer than the number of Power gained.
Thus, gaining 1/2/3/4/… Power costs 0/1/2/3/… Victory points.
Details
You may not end up with a negative score when losing Victory points.
You may not gain less Power in order to save Victory points. Either take it all or gain nothing. (Exceptions: If you have not enough Power tokens in Bowls I and II, gain as many Power as needed to move all of them to Bowl III and lose Victory points accordingly. Also, you may gain less Power to avoid a negative score. In this case, only gain as many Power as needed to end up with 0 Victory points.)
You only lose Victory points when gaining Power via Structures. You do not lose any Victory points when gaining Power otherwise.
Transforming a Terrain space
To ensure the first condition is met, you are allowed to transform one unoccupied Terrain space into your Home terrain immediately before building a Dwelling. Place a Terrain tile of your color on that space.
Transformation has its price: Each step between the source and destination terrain on the Transformation cycle of your Faction board costs 1 Spade. Thus, transforming adjacent types of terrain (on the Transformation cycle) into one another costs 1 Spade, with a maximum cost of up to 3 Spades if they are on opposite sides of the cycle as you can move in either direction on the cycle. (Exception: Giants always have to pay exactly 2 Spades to transform any type of terrain into their Home terrain.)

Spades can be acquired in a variety of ways.

You may exchange Workers for Spades. The Exchange track of your Faction board displays the Exchange rate: At the beginning of the game, this is usually 3 Workers for 1 Spade (see Action #3). (The Darklings pay 1 Priest for each missing Spade instead.)

1 or 2 Spades can be acquired via certain Power actions (see siehe Action #6 or Appendix I).

One of the Bonus cards provides 1 Spade (see Action #7, or Appendix IV).

(Halflings and Giants gain additional Spades for immediate use after building their Stronghold.)


Spades may only be applied to a single Terrain space. However, there are two special cases:

If the Spades gained via a Power action or Bonus card do not suffice to transform a Terrain space into your Home terrain, you may exchange Workers for the missing Spades ( at the current Exchange rate of your Exchange track ).
(The Darklings pay 1 Priest for each missing Spade instead.)
When gaining 2 Spades, if you only need one of them to transform a Terrain space into your Home terrain, you may apply the second Spade on another Terrain space. However, you may not place a Dwelling on this second space. (The same is true when the Halflings build their Stronghold, immediately gaining 3 Spades, see last page.)
Details on using Spades
You may not save Spades for future Actions. Spades need to be used immediately.
If the left side of the current Scoring tile depicts a Spade, you get Victory points for using Spades during the current Action phase.
After transforming a Terrain space, you do not need to build a Dwelling immediately. You can do this in another Action at a later point in time.
You do not need to transform a Terrain space into your Home terrain, you may stop at any other type of terrain (e.g. if you can not afford more Spades) . However, during their turns, your opponents may claim that Terrain space for themselves. (Terrain spaces with Terrain tiles on them but no Structures are considered unoccupied.)
Even when transforming a Terrain space without building a Dwelling, the transformed Terrain space needs to be directly or indirectly adjacent to one of your Structures.
You may not transform a Terrain space containing Structures.
You may also get Spades via Cult bonuses (see Phase III of a round) . As these Spades are gained outside the Action phase, you may not build a Dwelling immediately afterwards, nor do you get to exchange Workers for more Spades. (You have to wait for the next Action phase to build a Dwelling.)
If you gain more than 1 spade you may use them on several spaces as long as those spaces are adjacent to any of your buildings.
2) Advancing on the Shipping track
For 1 Priest and 4 Coins you can move the Marker on your Shipping Track forward one space.
Each move is one Action.
As a reward for taking this Action, get a number of Victory points as indicated on the Shipping space you move to.
Terrain spaces and Structures need to be indirectly adjacent to one another if you want to expand beyond River spaces. In order to transform Terrain spaces or build Structures beyond River spaces, as an Action, you may move the Marker on your Shipping track forward one space. Your Faction board displays the costs of this Action: 1 Priest and 4 Coins.
As a reward for taking this Action, get a number of Victory points as indicated on the Shipping space you move to.
3) Lowering the Exchange rate for Spades
For 2 Workers, 5 Coins and 1 Priest may move the Marker on your Exchange track up one space.
Each move is one Action.
As a reward for taking this Action, you get 6 Victory points.
At the beginning of the game, Spades cost 3 Workers each (see Action #1). In order to reduce this cost down to 2 Workers or even down to 1 Worker, as an Action, you may move the Marker on your Exchange track up one space. Your Faction board displays the costs of this Action: 2 Workers, 5 Coins, and 1 Priest. (Exception: Halflings pay fewer Coins, and Darklings do not even have an Exchange track as they exchange Priests for Spades.)
As a reward for taking this Action, you get 6 Victory points.
4) Upgrading a Structure
You can upgrade Structures. The old Structure will be replaced by the new one and put back onto the Faction board.

 Dwelling → Trading house
Cost: Workers and 6 Coins (3 instead of 6 coins if there's an opponent's Structure adjacent)

Victory Points: Depending on Scoring tile, possibly 3 Victory points.

 Trading house (1x)→ Stronghold
Cost: a number of Workers and Coins depending on the faction.

Victory Points: Depending on Scoring tile, possibly 5 Victory points.

Reward: After building its Stronghold, each faction gains a specific special ability.

 Trading house → Temple
Cost: 2 Workers and 5 Coins

Reward: 1 Favor tile, which may immediately be taken advantage of.

 Temple (1x)→ Sanctuary
Cost: a number of Workers and Coins depending on the faction.

Victory Points: Depending on Scoring tile, possibly 5 Victory points.

Reward: 1 Favor tile, which may immediately be taken advantage of.

Structures can be upgraded step by step. The costs for each upgrade are depicted on the Faction board to the left of a Structure.

Always take newly placed Structures from left to right off your Faction board.
When upgrading, you are replacing Structures by one another. Take the Structure that you wish to upgrade off the Game board and return it to your Faction board. This reduces your income for the given type of Structure. When returning Structures to your Faction board, always put them as far to the right as possible on their proper track. Place the new Structure on the same space on the Game board where the just removed Structure has been.
There are four possible upgrades (two of which can only be done once per game).

 Dwelling → Trading house
Upgrading a Dwelling to a Trading house costs 2 Workers and 6 Coins. If there is at least one opponent’s Structure directly adjacent to that Dwelling, you only need to pay 3 Coins instead of 6. If the current Scoring tile depicts a Trading house, get 3 Victory points for this upgrade.

(Engineers pay less and Swarmlings pay more when upgrading to a Trading House or Temple.)

 Trading house (1x)→ Stronghold
Upgrading a Trading house to a Stronghold costs a number of Workers and Coins depending on the faction. After building its Stronghold, each faction gains a specific special ability (see Appendix VI). If the current Scoring tile depicts a Stronghold, get 5 Victory points for this upgrade.

 Trading house → Temple
Upgrading a Trading house to a Temple costs 2 Workers and 5 Coins. As a reward, immediately choose and take one Favor tile and put it face-up in front of you. (Chaos Magicians always get 2 Favor tiles instead of 1.) You may immediately take advantage of the newly gained Favor tile, or use it during the current Action phase, respectively. You may not take a Favor tile that you already have. For further details on Favor tiles see Appendix II.

 Temple (1x)→ Sanctuary
Upgrading a Temple to a Sanctuary costs a number of Workers and Coins depending on the faction. This is also awarded with a Favor tile. (Chaos Magicians always get 2 Favor tiles instead of 1.) If the current Scoring tile depicts a Sanctuary, get 5 Victory points for this upgrade.

5) Send a Priest to the Order of a Cult
There are 4 spaces below each of the Cult tracks of Earth, Water, Fire, and Air that each can hold exactly 1 Priest. As an Action, you may place one of your Priests on one such space to advance 3 or 2 spaces (as indicated on the space) on the corresponding Cult track.

Those Priests remain there until end of the game. Keep in mind that you only have a total of 7 Priests at your disposal.

Alternatively you can return a Priest to your supply and only advance 1 space.

Only one player may advance to space 10 of each Cult track. To do so, you also need to have founded a Town for each space 10 you want to advance to.

There are 4 spaces below each of the Cult tracks of Earth, Water, Fire, and Air that each can hold exactly 1 Priest. As an Action, you may place one of your Priests on one such space to advance 3 or 2 spaces (as indicated on the space) on the corresponding Cult track.

You may gain Power when advancing on a track. You gain 1/2/2/3 Power when advancing to the 3rd/5th/7th/10th space of a Cult track. You only gain this Power once when advancing to or passing by these spaces.

There is no way of getting these Priests back. Keep in mind that you only have a total of 7 Priests at your disposal.

If you do not want to lose a Priest, alternatively, return him to your supply (next to your Faction board) and only advance 1 space.

Only one player on space 10
Only one player may advance to space 10 of each Cult track. To do so, you also need to have founded a Town for each space 10 you want to advance to. See here for details on founding a Town.

6) Power actions
The Bowls of Power
Each player has 12 Power tokens. Whenever you gain or use Power, you move Power tokens between the bowls. For Power actions you can only use Power tokens in Bowl III; these are moved to Bowl I.

Sacrificing Power
If you do not have enough Power in Bowl III to take a specific Power or Conversion action, on top of your Action, you may move Power tokens from Bowl II to Bowl III and then immediately take the desired Action. However, for each Power token moved in this fashion, you need to remove 1 Power token from Bowl II from the game .
Summary: How do I gain Power?
You gain Power…

when an opponent builds Structures
as income from your Trading houses and Stronghold
as income from two specific Bonus cards
as income from two specific Favor tiles
as a one-time reward when founding a Town and taking the specific Town tile
as a one-time Cult bonus in Phase III if the specific Scoring tile is up for the current round
when advancing on the Cult tracks

There are two types of Power actions: fully-fledged Actions and auxiliary Actions.

Power actions on the Game board
The Power actions on the Game board (indicated by orange octagons) may only be taken once per round – first come, first served.

Conversions (anytime)
Anytime during your turn, on top of your Action, you may do any number of Conversions. Therefore, you have the following options

Spend 5 Power for 1 Priest.
Spend 3 Power for 1 Worker.
Spend 1 Power for 1 Coin.
Convert a Priest to a Worker.
Convert a Worker to a Coin.
There are two types of Power actions: fully-fledged Actions and auxiliary Actions.

Power actions on the Game board
The Power actions on the Game board (indicated by orange octagons) may only be taken once per round – first come, first served.

Whenever taking one of these Actions, move a number of Power tokens from Bowl III to Bowl I equal to its indicated cost. Then, put an Action token on its space on the Game board to indicate that this Action may not be taken any more this round.

All the Power actions on the Game board are explained in detail in Appendix I.

Conversions (anytime)
Anytime during your turn, on top of your Action, you may do any number of Conversions. Therefore, you have the following options (also depicted in Bowl III on your Faction board):

Spend 5 Power for 1 Priest.
Spend 3 Power for 1 Worker.
Spend 1 Power for 1 Coin.
Convert a Priest to a Worker.
Convert a Worker to a Coin.
A Conversion is not considered your Turn action.

On your turn, you may do any number of Conversions before or after your Action.

The Bowls of Power
Each player has 12 Power tokens that are distributed among three Bowls. Power actions require Power in Bowl III (the one on the right).

Whenever you gain Power in the game (as income, when an opponent builds Structures, or by advancing on the Cult tracks), you do not get new Power tokens, but rather move the existing ones from Bowl to Bowl. Also, when spending Power on a Power action, you do not lose Power tokens, but move them around again.

Power tokens move (always in clockwise order) according to the following rules:

Gaining Power
When gaining Power, proceed in the following order:
If there are Power tokens in Bowl I, for each 1 Power you gain move one token from Bowl I to Bowl II.
Once Bowl I is empty, for each 1 Power you gain move one token from Bowl II to Bowl III.
Once all Power tokens are in Bowl III, you cannot gain further Power.
Spending Power
You can only spend Power tokens that are in Bowl III. Move them to Bowl I when spending them.
Sacrificing Power
If you do not have enough Power in Bowl III to take a specific Power or Conversion action, on top of your Action, you may move Power tokens from Bowl II to Bowl III and then immediately take the desired Action. However, for each Power token moved in this fashion, you need to remove 1 Power token from Bowl II from the game . From now on, you will irrevocably have to deal with fewer Power tokens in your cycle. You may not sacrifice Power if you only have 1 Power token in Bowl II.
Summary: How do I gain Power?
You gain Power…

when an opponent builds Structures
as income from your Trading houses and Stronghold (and Temples if you are playing the Engineers)
as income from two specific Bonus cards
as income from two specific Favor tiles
as a one-time reward when founding a Town and taking the specific Town tile
as a one-time Cult bonus in Phase III if the specific Scoring tile is up for the current round
when advancing on the Cult tracks
7) Special actions
Special actions can be recognized like Power actions by the orange octagons and can be used exactly once per turn as a regular action.

You get Special actions through

some Favor tiles
some Bonus cards
for some factions the building of their Stronghold
Each Special action may be taken only once per round. Special actions, like Power actions, are indicated by orange octagons and can be gained in a variety of ways. Some factions unlock a Special action when building their Stronghold. (These are explained in Appendix VI.)

One Favor tile and one Bonus card provide a Special action to advance 1 space on a Cult track. Another Bonus card provides a free Spade for transforming a Terrain space.

Use Action tokens to cover taken Special actions.

8) Passing and new Starting player
On your turn, if you cannot or do not want to take any more Action, you have to pass and stop taking Actions for the remainder of this round.

The first player to pass becomes the Starting player for the next round.

When passing, immediately return your Bonus card and take one of the three available ones.

You may get Victory points when returning certain Bonus cards.

On your turn, if you cannot or do not want to take any more Action, you have to pass and stop taking Actions for the remainder of this round.

The first player to pass becomes the Starting player for the next round (and takes the Starting player token).

When passing, immediately return your Bonus card and take one of the three available ones. This may be a card another player has already returned this round. (Exception: Do not take any Bonus cards in the last round of the game – round 6.) If there were Coins on the newly taken Bonus card, put them on your Faction board.


You may get Victory points when returning certain Bonus cards.


When passing and returning the Bonus card depicted on the left, get 1 Victory point for each of your Dwellings on the Game board.

When passing and returning the Bonus card depicted on the left, get 2 Victory points for each of your Trading houses on the Game board.

When passing and returning the Bonus card depicted on the left, get 4 Victory points if you have already built your Stronghold and/or 4 Victory points if you have already built your Sanctuary.
Freely choose which one of the three available Bonus cards you want to take. (You may not immediately take the card that you have just returned.)
You may wish to put the newly taken Bonus card face-down in front of you to indicate that you have already passed.
There is no limitation for how many resources you may keep for the next round.
You may take as many Actions as you wish, even if all other players have already passed. As long as there is at least one player left taking Actions, the current Action phase continues.
 */