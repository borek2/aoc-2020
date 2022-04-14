package assignments

object Day7 {

    fun runFirst(input: List<String> = input()): Int = createBagList(input).let { parsedBags ->
        parsedBags.filter { containsShinyGoldBag(it, parsedBags) }.size
    }

    fun runSecond(input: List<String> = input()): Int = createBagList(input).let { parsedBags ->
        sumAllBags(parsedBags.find { it.color == "shiny gold" }!!, parsedBags)
    }

    // Parsing

    private fun createBagList(input: List<String>): List<Bag> = input.fold(mutableListOf()) { acc, line ->
        createBag(line, acc).let { createdBag ->
            acc.apply {
                // If did not already contain the bag, add a new one, or else put it at the index
                indexOfFirst { it.color == createdBag.color }.takeIf { it >= 0 }?.let { set(it, createdBag) } ?: add(createdBag)
            }
        }
    }

    private fun createBag(line: String, parsedBags: MutableList<Bag>): Bag =
        Regex("(?<bagname>^[a-zA-Z]+ [a-zA-Z]+)|(?<amount>[0-9]) (?<containingbag>[a-zA-Z]+ [a-zA-Z]+)")
            .findAll(line)
            .toList()
            .let { matches ->
                val bagColor = matches.first { it.groups["bagname"] != null }.value
                val containingBags = matches
                    .mapNotNull { it.groups["amount"]?.value?.toInt() }
                    .zip(matches.mapNotNull { it.groups["containingbag"]?.value })
                    .let { rawContainingBags ->
                        Array(rawContainingBags.size) { i ->
                            val (amount, color) = rawContainingBags[i]
                            amount to findOrCreateBag(parsedBags, color)
                        }
                    }

                Bag(bagColor, containingBags)
            }

    // Exercise calculation

    private fun containsShinyGoldBag(bag: Bag, allBags: List<Bag>): Boolean =
        bag.containsBags.any { it.second.color == "shiny gold" } ||
            bag.containsBags.any { b -> containsShinyGoldBag(allBags.find { it.color == b.second.color }!!, allBags) }

    private fun sumAllBags(bag: Bag, allBags: List<Bag>): Int =
        bag.containsBags.sumOf { b -> b.first * sumAllBags(allBags.find { it.color == b.second.color }!!, allBags) }
            .plus(bag.containsBags.sumOf { it.first })

    private fun findOrCreateBag(parsedBags: MutableList<Bag>, color: String): Bag =
        (parsedBags.find { it.color == color } ?: Bag(color, emptyArray()).also { parsedBags.add(it) })

    class Bag(val color: String, val containsBags: Array<Pair<Int, Bag>>)

    private fun input(): List<String> {
        return this::class.java.classLoader.getResourceAsStream("day7")?.bufferedReader()?.useLines {
            it.toList()
        }.orEmpty()
    }
}