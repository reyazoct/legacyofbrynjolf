import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CommandTest {

    @Test
    fun shouldReturnUpForCommandU() {
        val expectedCommand = Command.UP
        val actualCommand = Command.fromSymbol('u')
        assertEquals(expectedCommand, actualCommand)
    }

    @Test
    fun shouldReturnLeftForCommandL() {
        val expectedCommand = Command.LEFT
        val actualCommand = Command.fromSymbol('l')
        assertEquals(expectedCommand, actualCommand)
    }

    @Test
    fun shouldThrowExceptionForInvalidSymbol() {
        val exception = assertFailsWith<Exception> { Command.fromSymbol('z') }
        assertEquals("Invalid Command symbol", exception.message)
    }
}