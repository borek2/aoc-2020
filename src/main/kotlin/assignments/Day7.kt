package assignments

object Day7 {

    fun runFirst(input: List<String> = input()): Int {
        val parsedBags = createBagList(input)
        return createBagList(input).filter { it.containsShinyGoldBag(parsedBags) }.size
    }

    fun runSecond(input: List<String> = input()): Int {
        val parsedBags = createBagList(input)
        return parsedBags.find { it.color == "shiny gold" }!!.sumAllBags(parsedBags)
    }

    // Parsing

    private fun createBagList(input: List<String>): List<Bag> {
        val parsedBags = mutableListOf<Bag>()
        for (line in input) {
            val createdBag = createBag(line, parsedBags)

            // If did not already contain the bag, add a new one, or else put it at the index
            parsedBags
                .indexOfFirst { it.color == createdBag.color }
                .takeIf { it >= 0 }
                ?.let { parsedBags[it] = createdBag }
                ?: parsedBags.add(createdBag)
        }
        return parsedBags
    }

    private fun createBag(line: String, parsedBags: MutableList<Bag>): Bag {
        val bagColor = getBag(line)
        val containingBags = getContainingBags(line).let { rawContainingBags ->
            Array(rawContainingBags.size) { i ->
                val (amount, color) = rawContainingBags[i]
                amount to findOrCreateBag(parsedBags, color)
            }
        }

        return Bag(bagColor, containingBags)
    }

    private fun getBag(line: String): String =
        Regex("^[a-zA-Z]+ [a-zA-Z]+").find(line)!!.value

    private fun getContainingBags(line: String): List<Pair<Int, String>> =
        Regex("(?<amount>[0-9]) (?<color>[a-zA-Z]+ [a-zA-Z]+)")
            .findAll(line)
            .toList()
            .map { it.groups["amount"]?.value!!.toInt() to it.groups["color"]?.value!! }

    // Exercise calculation

    private fun Bag.containsShinyGoldBag(allBags: List<Bag>): Boolean {
        if (containsBags.isEmpty()) return false

        return containsBags
            .any { it.second.color == "shiny gold" } ||
            containsBags.any { b -> allBags.find { it.color == b.second.color }!!.containsShinyGoldBag(allBags) }
    }

    private fun Bag.sumAllBags(allBags: List<Bag>): Int {
        if (containsBags.isEmpty()) return 0

        return containsBags
            .sumOf { b -> b.first * allBags.find { it.color == b.second.color }!!.sumAllBags(allBags) }
            .plus(containsBags.sumOf { it.first })
    }

    private fun findOrCreateBag(parsedBags: MutableList<Bag>, color: String): Bag {
        return (parsedBags.find { it.color == color } ?: Bag(color, emptyArray()).also { parsedBags.add(it) })
    }

    class Bag(val color: String, val containsBags: Array<Pair<Int, Bag>>)

    private fun input(): List<String> {
        return this::class.java.classLoader.getResourceAsStream("day7")?.bufferedReader()?.useLines {
            it.toList()
        }.orEmpty()
    }
}