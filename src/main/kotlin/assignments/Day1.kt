package assignments

object Day1 {

    fun calculateAnswerPart1(input: List<Int> = input()): Int {
        for (entry in input) {
            for (entry2 in input) {
                val sum = entry + entry2

                if (sum == EXPECTED_SUM) {
                    return entry * entry2
                }
            }
        }

        return -1
    }

    fun calculateAnswerPart2(input: List<Int> = input()): Int {
        for (entry in input) {
            for (entry2 in input) {
                for (entry3 in input) {
                    val sum = entry + entry2 + entry3

                    if (sum == EXPECTED_SUM) {
                        return entry * entry2 * entry3
                    }
                }
            }
        }

        return -1
    }

    private fun input(): List<Int> {
        return this::class.java.classLoader.getResourceAsStream("day1").bufferedReader().useLines {
            it.map { it.toInt() }.toList()
        }
    }

    private const val EXPECTED_SUM = 2020
}