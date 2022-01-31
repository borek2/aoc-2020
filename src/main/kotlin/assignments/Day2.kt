package assignments

object Day2 {

    fun calculateAnswerPart1(input: List<String> = input()): Int {
        return input.count { line -> line.parse().isValid() }
    }

    fun calculateAnswerPart2(input: List<String> = input()): Int {
        return input.count { line -> line.parse().isValid2() }
    }

    private data class Policy(
        private val password: String,
        private val firstInteger: Int,
        private val secondInteger: Int,
        private val character: Char
    ) {

        fun isValid(): Boolean {
            val amountOfCharacters = password.count { it == character }
            return amountOfCharacters in firstInteger..secondInteger
        }

        fun isValid2(): Boolean {
            val characterAtFirst = password[firstInteger - 1]
            val characterAtSecond = password[secondInteger - 1]

            return (characterAtFirst == character) xor (characterAtSecond == character)
        }
    }

    private fun String.parse(): Policy {
        val lineSplitted = split(" ")
        val password = lineSplitted.last()
        val (minimalAmount, maximumAmount) = lineSplitted.first().split("-").let {
            it.first().toInt() to it.last().toInt()
        }
        val character = lineSplitted[1].first()

        return Policy(
            password, minimalAmount, maximumAmount, character
        )
    }

    private fun input(): List<String> {
        return this::class.java.classLoader.getResourceAsStream("day2").bufferedReader().useLines {
            it.toList()
        }
    }
}