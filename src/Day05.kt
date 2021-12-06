fun main() {
    val input = readInput("Day05")

    // region Part 1
    fun Char.isVowel(): Boolean = "aeiou".contains(this)

    fun String.isNicePart1(): Boolean =
        count { it.isVowel() } >= 3
                && zipWithNext().any { (first, second) -> first == second }
                && !contains("ab")
                && !contains("cd")
                && !contains("pq")
                && !contains("xy")

    fun part1(input: List<String>): Int = input.count { it.isNicePart1() }

    // test if implementation meets criteria from the description, like:
    val testInputPart1 = readInput("Day05_testPart1")
    check(part1(testInputPart1) == 2)

    println(part1(input))

    // endregion

    // region Part 2

    fun String.occursTwiceIn(searchString: String): Boolean {
        val position = searchString.indexOf(this)
        if (position == -1) return false
        return searchString.indexOf(this, position + 2) != -1
    }

    fun String.part2Condition1() =
        zipWithNext().any { (first, second) ->
            "$first$second".occursTwiceIn(this)
        }

    fun String.part2Condition2() = indices.drop(2).any { this[it - 2] == this[it] }

    fun String.isNicePart2(): Boolean {
        val condition1 = part2Condition1()
        val condition2 = part2Condition2()
        return condition1 && condition2
    }

    fun part2(input: List<String>): Int = input.count { it.isNicePart2() }
    
    // test if implementation meets criteria from the description, like:
    val testInputPart2 = readInput("Day05_testPart2")
    check(part2(testInputPart2) == 2)
    check("xyxy".part2Condition1())
    check("aabcdefgaa".part2Condition1())
    check(!"aaa".part2Condition1())
    check("xyx".part2Condition2())
    check("abcdefeghi".part2Condition2())
    check("aaa".part2Condition2())

    println(part2(input))

    // endregion
}
