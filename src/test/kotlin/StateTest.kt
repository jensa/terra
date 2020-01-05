import org.junit.Test
import org.junit.Assert

internal class StateTest{

    @Test
    fun testPassTurn(){
        val state = State(listOf(
            PlayerState("", 1, "lol"),
            PlayerState("", 2, "lol"),
            PlayerState("", 3, "lol"),
            PlayerState("", 4, "lol")))

        state.passTurn()
        Assert.assertEquals(2, state.playersTurn)
        state.passTurn()
        Assert.assertEquals(3, state.playersTurn)
        state.passTurn()
        Assert.assertEquals(4, state.playersTurn)
        state.passTurn()

        Assert.assertEquals(1, state.playersTurn)
    }

    @Test
    fun testTerrainWheel(){
        val fromRed = terrainWheel(Terrain.Red)
        Assert.assertEquals(1, fromRed[Terrain.Gray])

        Assert.assertEquals(0, fromRed[Terrain.Red])
        Assert.assertEquals(3, fromRed[Terrain.Black])
        Assert.assertEquals(2, fromRed[Terrain.Green])

        val fromGray = terrainWheel(Terrain.Gray)
        Assert.assertEquals(1, fromGray[Terrain.Red])

    }
}