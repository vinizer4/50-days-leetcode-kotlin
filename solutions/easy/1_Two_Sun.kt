package solutions.easy

/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
 */


class TwoSumSolution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var indexSum: MutableList<Int> = mutableListOf()

        var increment = 0

        var jumpToNextValue = 1

        var maxIndex = nums.size - 1

        while (indexSum.size < 2) {

            if (increment > maxIndex) {
                increment = 0
                jumpToNextValue++
            }

            if (increment + jumpToNextValue > maxIndex) {
                jumpToNextValue = 0
            }


            if (nums[increment] + nums[increment + jumpToNextValue] == target) {

                if (indexSum.contains(increment).not()) {
                    indexSum.add(increment)
                }
                if (indexSum.contains(increment + jumpToNextValue).not()) {
                    indexSum.add(increment + jumpToNextValue)
                }
            }

            if (indexSum.size == 2) {
                return indexSum.toIntArray()
            } else {
                indexSum = mutableListOf()
            }

            if (increment + jumpToNextValue == maxIndex) {
                increment = 0
                jumpToNextValue++
            } else {
                increment++
            }
        }

        return indexSum.toIntArray()
    }

}

fun main() {
    val solution = TwoSumSolution()
    val nums = intArrayOf(2,7,11,15)
    val target = 9
    println(solution.twoSum(nums, target).contentToString())
}