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
}