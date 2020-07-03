import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RoomTest {
    private lateinit var room: Room

    @BeforeEach
    fun initialize() {
        val initialState = listOf(
                listOf(Entity.EMPTY_SPACE, Entity.GUARD, Entity.EMPTY_SPACE),
                listOf(Entity.BRYNJOLF, Entity.EMPTY_SPACE, Entity.EXIT),
                listOf(Entity.EMPTY_SPACE, Entity.WALL, Entity.GUARD)
        )
        room = Room(initialState)
    }

    @Test
    fun shouldReturn10CoordinateForBrynjolf() {
        val expectedCoordinate = listOf(Coordinate(1, 0))
        assertEquals(expectedCoordinate, room.findCoordinates(Entity.BRYNJOLF))
    }

    @Test
    fun shouldReturn01And22CoordinateForBrynjolf() {
        val expectedCoordinate = listOf(Coordinate(2, 2), Coordinate(0, 1))
        assertListEquals(expectedCoordinate, room.findCoordinates(Entity.GUARD))
    }

    private fun <T> assertListEquals(listOne: List<T>, listTwo: List<T>) {
        assertTrue { listOne.size == listTwo.size && listOne.containsAll(listTwo) && listTwo.containsAll(listOne) }
    }
}