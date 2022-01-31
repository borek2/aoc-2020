package assignments

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day1Test {

    private val input = this::class.java.classLoader.getResourceAsStream("day1").bufferedReader().useLines {
        it.map { it.toInt() }.toList()
    }

    @Test
    fun calculateAnswerPart1() {
        val result = Day1.calculateAnswerPart1(input)
        assertEquals(514579, result)
    }

    @Test
    fun calculateAnswerPart2() {
        val result = Day1.calculateAnswerPart2(input)
        assertEquals(241861950, result)
    }
}