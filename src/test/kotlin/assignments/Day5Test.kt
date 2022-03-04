package assignments

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day5Test {

    private val input = this::class.java.classLoader.getResourceAsStream("day5")?.bufferedReader()?.useLines {
        it.toList()
    }.orEmpty()

    @Test
    fun calculateFirst() {
        val expected = 820
        assertEquals(expected, Day5.runFirst(input))
    }

    @Test
    fun calculateFirst_2() {
        val expected = 0 * 8 + 0
        assertEquals(expected, Day5.runFirst(listOf("FFFFFFFLLL")))
    }

    @Test
    fun calculateSecond() {
        val expected = 820
        assertEquals(expected, Day5.runSecond(input))
    }
}