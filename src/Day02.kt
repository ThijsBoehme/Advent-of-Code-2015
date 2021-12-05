fun main() {
    fun part1(input: List<String>): Int {
        return input.map { line -> line.split("x").map { it.toInt() } }
            .sumOf { (l, w, h) ->
                2 * l * w + 2 * w * h + 2 * h * l + minOf(l * w, w * h, h * l)
            }
    }

    fun part2(input: List<String>): Int {
        return input.map { line -> line.split("x").map { it.toInt() } }
            .sumOf { (l, w, h) ->
                minOf(2 * (l + w), 2 * (w + h), 2 * (l + h)) + l * w * h
            }
    }

    // test if implementation meets criteria from the description, like:
    check(part1(listOf("2x3x4")) == 58)
    check(part1(listOf("1x1x10")) == 43)

    check(part2(listOf("2x3x4")) == 34)
    check(part2(listOf("1x1x10")) == 14)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
