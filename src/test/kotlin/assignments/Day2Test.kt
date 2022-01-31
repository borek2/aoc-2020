package assignments

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day2Test {

    private val input = this::class.java.classLoader.getResourceAsStream("day2").bufferedReader().useLines {
        it.toList()
    }

    @Test
    fun calculateAnswerPart1() {
        val result = Day2.calculateAnswerPart1(input)
        assertEquals(2, result)
    }

    @Test
    fun calculateAnswerPart2() {
        val result = Day2.calculateAnswerPart2(input)
        assertEquals(1, result)
    }
}