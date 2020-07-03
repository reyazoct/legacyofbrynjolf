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
        val expectedCoordinate = listOf(Coordinate(0, 1))
        assertEquals(expectedCoordinate, room.findCoordinates(Entity.BRYNJOLF))
    }

    @Test
    fun shouldReturn01And22CoordinateForBrynjolf() {
        val expectedCoordinate = listOf(Coordinate(2, 2), Coordinate(1, 0))
        assertListEquals(expectedCoordinate, room.findCoordinates(Entity.GUARD))
    }

    @Test
    fun shouldReturnCorrectStateForCommandDownForAllMoveAbleEntities() {
        val finalState = listOf(
                listOf(Entity.EMPTY_SPACE, Entity.EMPTY_SPACE, Entity.EMPTY_SPACE),
                listOf(Entity.EMPTY_SPACE, Entity.GUARD, Entity.EXIT),
                listOf(Entity.BRYNJOLF, Entity.WALL, Entity.GUARD)
        )
        val expectedRoom = Room(finalState)
        room.executeCommand(Command.DOWN)
        assertEquals(expectedRoom, room)
    }

    @Test
    fun shouldReturnCorrectStateForCommandUpForAllMoveAbleEntities() {
        val finalState = listOf(
                listOf(Entity.BRYNJOLF, Entity.GUARD, Entity.EMPTY_SPACE),
                listOf(Entity.EMPTY_SPACE, Entity.EMPTY_SPACE, Entity.EXIT),
                listOf(Entity.EMPTY_SPACE, Entity.WALL, Entity.GUARD)
        )
        val expectedRoom = Room(finalState)
        room.executeCommand(Command.UP)
        assertEquals(expectedRoom, room)
    }

    @Test
    fun shouldNotOverLapOtherEntityWhileMove() {
        val finalState = listOf(
                listOf(Entity.EMPTY_SPACE, Entity.EMPTY_SPACE, Entity.GUARD),
                listOf(Entity.EMPTY_SPACE, Entity.EMPTY_SPACE, Entity.EXIT),
                listOf(Entity.EMPTY_SPACE, Entity.WALL, Entity.GUARD)
        )
        val expectedRoom = Room(finalState)
        room.executeCommand(Command.UP)
        room.executeCommand(Command.RIGHT)
        assertEquals(expectedRoom, room)
    }

    @Test
    fun shouldWonOnCommandRight() {
        room.executeCommand(Command.RIGHT)
        assertEquals(GameState.WIN, room.gameState)
    }

    @Test
    fun shouldLoseOnCommandUpAndRight() {
        room.executeCommand(Command.UP)
        room.executeCommand(Command.RIGHT)
        assertEquals(GameState.LOSE, room.gameState)
    }

    private fun <T> assertListEquals(listOne: List<T>, listTwo: List<T>) {
        assertTrue { listOne.size == listTwo.size && listOne.containsAll(listTwo) && listTwo.containsAll(listOne) }
    }

}