package assignments

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day7Test {

    private val input = this::class.java.classLoader.getResourceAsStream("day7")?.bufferedReader()?.useLines {
        it.toList()
    }.orEmpty()

    private val input2 = this::class.java.classLoader.getResourceAsStream("day7_2")?.bufferedReader()?.useLines {
        it.toList()
    }.orEmpty()

    @Test
    fun testFirst() {
        val expected = 4
        Assertions.assertEquals(expected, Day7.runFirst(input))
    }

    @Test
    fun testSecond_1() {
        val expected = 32
        Assertions.assertEquals(expected, Day7.runSecond(input))
    }

    @Test
    fun testSecond_2() {
        val expected = 126
        Assertions.assertEquals(expected, Day7.runSecond(input2))
    }
}