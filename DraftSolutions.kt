class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        val expectedNums: MutableList<Int> = mutableListOf()
        for (i in nums.indices) {
            if (expectedNums.contains(nums[i]).not()) {
                expectedNums.add(nums[i])
            }
        }

        for (i in expectedNums.indices) {
            nums[i] = expectedNums[i]
        }
        return expectedNums.size
    }
}

fun main() {
    val solution = Solution()
    val nums = intArrayOf(1, 1)
    println(solution.removeDuplicates(nums))
}