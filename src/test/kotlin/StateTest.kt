import org.junit.Test
import org.junit.Assert
import org.junit.Assert.assertEquals

internal class StateTest{

    @Test
    fun testPassTurn(){
        val state = State(listOf(
            PlayerState("", 1, "lol"),
            PlayerState("", 2, "lol"),
            PlayerState("", 3, "lol"),
            PlayerState("", 4, "lol")))

        state.passTurn()
        assertEquals(2, state.playersTurn)
        state.passTurn()
        assertEquals(3, state.playersTurn)
        state.passTurn()
        assertEquals(4, state.playersTurn)
        state.passTurn()

        assertEquals(1, state.playersTurn)
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

    @Test
    fun testCopy() {
        val cap = Capital(workers = 2, cults = listOf(Cult.Earth, Cult.Fire))
        val cap2 = cap.copy(workers = 3)
        assertEquals(2, cap2.cults.size)
        assertEquals(3, cap2.workers)
    }

    @Test
    fun testPowerBowls() {
        val pb = PowerBowls(5,5)
        val pb2 = pb.increase(8)
        assertEquals(0, pb2.first)
        assertEquals(7, pb2.second)
        assertEquals(3, pb2.third)

        val pb3 = pb2.burn(3)
        assertEquals(0, pb3.first)
        assertEquals(1, pb3.second)
        assertEquals(6, pb3.third)

        val pb4 = pb3.use(5)

        assertEquals(5, pb4.first)
        assertEquals(1, pb4.second)
        assertEquals(1, pb4.third)

        var caught = false
        try{
            pb4.use(2)
        } catch (e:IllegalMove){
            caught = true
        }
        assertEquals(true, caught)
        caught = false
        try{
            pb4.burn(1)
        } catch (e:IllegalMove){
            caught = true
        }
        assertEquals(true, caught)
    }


}