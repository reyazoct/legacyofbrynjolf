import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SimulatorTest {

    @Test
    fun showCommandsWhenBrynjolfAndExitIsInSameAlignmentCaseOne() {
        val initialState = listOf(
                listOf(Entity.EMPTY_SPACE, Entity.GUARD, Entity.EMPTY_SPACE),
                listOf(Entity.BRYNJOLF, Entity.EMPTY_SPACE, Entity.EXIT),
                listOf(Entity.EMPTY_SPACE, Entity.WALL, Entity.GUARD)
        )
        val room = Room(initialState)
        val simulator = Simulator(room)
        simulator.simulate()
        assertEquals(listOf(Command.RIGHT), simulator.getRemainingMoves())
    }

    @Test
    fun showCommandsWhenBrynjolfAndExitIsInSameAlignmentCaseTwo() {
        val initialState = listOf(
                listOf(Entity.EMPTY_SPACE, Entity.EMPTY_SPACE, Entity.EMPTY_SPACE),
                listOf(Entity.BRYNJOLF, Entity.GUARD, Entity.EXIT),
                listOf(Entity.EMPTY_SPACE, Entity.WALL, Entity.GUARD)
        )
        val room = Room(initialState)
        val simulator = Simulator(room)
        simulator.simulate()
        assertEquals(emptyList(), simulator.getRemainingMoves())
    }

    @Test
    fun showCommandsWhenBrynjolfAndExitIsInSameAlignmentCaseThree() {
        val initialState = listOf(
                listOf(Entity.EMPTY_SPACE, Entity.EXIT, Entity.EMPTY_SPACE),
                listOf(Entity.GUARD, Entity.EMPTY_SPACE, Entity.GUARD),
                listOf(Entity.EMPTY_SPACE, Entity.BRYNJOLF, Entity.GUARD)
        )
        val room = Room(initialState)
        val simulator = Simulator(room)
        simulator.simulate()
        assertEquals(listOf(Command.UP), simulator.getRemainingMoves())
    }
}