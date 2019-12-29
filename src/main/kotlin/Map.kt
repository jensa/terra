import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

class TerraMysticaMap {

    val hexes:Map<AxialCoordinate, Hex> = (0..12).map { column(it) }.reduce{acc,c-> acc.plus(c)}

    private fun column(c:Int) : Map<AxialCoordinate, Hex> {
        return mapOf(
            hex(c + 0,0, Terrain.BROWN),
            hex(c + 0,1, Terrain.BROWN),
            hex(c + -1,2, Terrain.BROWN),
            hex(c + -1,3, Terrain.BROWN),
            hex(c + -2,4, Terrain.BROWN),
            hex(c + -2,5, Terrain.BROWN),
            hex(c + -3,6, Terrain.BROWN),
            hex(c + -3,7, Terrain.BROWN),
            hex(c + -4,8, Terrain.BROWN)
        )
    }

    private fun neighbours(hex: Hex) : List<Hex> {
        return listOf(
            AxialCoordinate(+1, 0),
            AxialCoordinate(+1, -1),
            AxialCoordinate(0, -1),
            AxialCoordinate(-1, 0),
            AxialCoordinate(-1, +1),
            AxialCoordinate(0, +1)
        ).map { hex.c + it }.mapNotNull { hexes[it] }
    }

    private fun hex(x:Int, y:Int, terrain:Terrain) :Pair<AxialCoordinate, Hex>{
        val c = AxialCoordinate(x,y)
        return c to Hex(terrain,c)
    }

    data class Hex(
        @JsonProperty("t")
        val terrain: Terrain,
        @JsonIgnore
        val c: AxialCoordinate
    ){
    }


    enum class Terrain{
        BROWN,GRAY,GREEN,BLUE,YELLOW,RED,BLACK, WATER;
        override fun toString(): String = "$ordinal"
    }

    data class AxialCoordinate(val x:Int, val y:Int){

        operator fun plus(other:AxialCoordinate) : AxialCoordinate {
            return AxialCoordinate(x+other.x, y + other.y)
        }

        override fun toString(): String = "$x,$y"

        override fun equals(other: Any?): Boolean {
            return if(other is AxialCoordinate){
                toString() == other.toString()
            } else false
        }

        override fun hashCode(): Int = 31 * x + y
    }
}
