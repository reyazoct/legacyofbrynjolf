import org.junit.jupiter.api.Test
import java.lang.Exception
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class EntityTest {

    @Test
    fun shouldReturnBrynjolfWhenSymbolIsB() {
        val expectEntity = Entity.BRYNJOLF
        val actualEntity = Entity.fromSymbol('b')
        assertEquals(expectEntity, actualEntity)
    }

    @Test
    fun shouldReturnEmptySpaceWhenSymbolIsDot() {
        val expectEntity = Entity.EMPTY_SPACE
        val actualEntity = Entity.fromSymbol('.')
        assertEquals(expectEntity, actualEntity)
    }

    @Test
    fun shouldThrowExceptionForInvalidSymbol() {
        val exception = assertFailsWith<Exception> { Entity.fromSymbol('z') }
        assertEquals("Invalid Entity Symbol", exception.message)
    }
}