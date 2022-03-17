package assignments

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day6Test {

    private val input = this::class.java.classLoader.getResourceAsStream("day6")?.bufferedReader()?.useLines {
        it.toList()
    }.orEmpty()

    @Test
    fun testFirst() {
        val expected = 11
        assertEquals(expected, Day6.part1(input))
    }

    @Test
    fun testSecond() {
        val expected = 6
        assertEquals(expected, Day6.part2(input))
    }
}