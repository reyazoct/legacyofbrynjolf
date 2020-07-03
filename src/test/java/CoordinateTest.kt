import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class CoordinateTest {

    @Test
    fun shouldReturnTrueForSameDataOfXAndY() {
        val coordinate = Coordinate(4, 6)
        val otherCoordinate = Coordinate(4, 6)
        assertEquals(coordinate, otherCoordinate)
    }

    @Test
    fun shouldReturnFalseForDifferentDataOfXAndY() {
        val coordinate = Coordinate(8, 12)
        val otherCoordinate = Coordinate(9, 12)
        assertNotEquals(coordinate, otherCoordinate)
    }

    @Test
    fun shouldReturn42ForUpCommand52() {
        val actualCoordinate = Coordinate(5, 2).move(Command.UP)
        val expectedCoordinate = Coordinate(5, 1)
        assertEquals(expectedCoordinate, actualCoordinate)
    }

    @Test
    fun shouldReturn31ForRightCommand30() {
        val actualCoordinate = Coordinate(3, 0).move(Command.RIGHT)
        val expectedCoordinate = Coordinate(4, 0)
        assertEquals(expectedCoordinate, actualCoordinate)
    }
}