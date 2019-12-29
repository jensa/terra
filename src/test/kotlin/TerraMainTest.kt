import org.junit.Test
import org.junit.Assert

internal class TerraMainTest{
    @Test
    fun testSerializer(){
        mapper.writeValueAsString(Lol())
    }

    class Lol()
}