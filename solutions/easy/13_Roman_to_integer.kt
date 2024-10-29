package solutions.easy

/*
13. Roman to Integer

link: https://leetcode.com/problems/roman-to-integer/description/

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I               1
V              5
X              10
L              50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.



Example 1:

Input: s = "III"
Output: 3
Explanation: III = 3.
Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 3:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */

class RomanToIntegerSolution {

    private val symbolValueRoman : HashMap<Char, Int> = hashMapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )

    private data class RomanChar(
        val position: Int,
        val value: Int,
        val char: Char,
        var isAddedToSubtratedNumber: Boolean = false
    )

    private data class CombinedRomanNumber(
        val actualPosition: Int,
        val beforePosition: Int,
        val value: Int,
        val symbol: String
    )

    fun romanToInt(s: String): Int {
        val romanMapedInfos = mutableListOf<RomanChar>()

        val subtractedRomanNumbers = mutableListOf<CombinedRomanNumber>()

        for (index in s.indices) {
           val char = s[index]
            val value = symbolValueRoman[char] ?: 0
            romanMapedInfos.add(RomanChar(index + 1, value, char))
        }

        for (romanNumber in romanMapedInfos) {
            val actualPosition = romanNumber.position
            val beforePosition = romanNumber.position - 1

            if (beforePosition > 0) {
                val beforeItem = romanMapedInfos[beforePosition - 1]
                if (beforeItem.value < romanNumber.value) {
                    beforeItem.isAddedToSubtratedNumber = true
                    romanNumber.isAddedToSubtratedNumber = true
                    subtractedRomanNumbers.add(
                        CombinedRomanNumber(
                            actualPosition,
                            beforePosition,
                            romanNumber.value - beforeItem.value,
                            "${beforeItem.char}${romanNumber.char}"
                        )
                    )
                }
            }
        }

        var result = 0

        for (romanNumber in romanMapedInfos) {
            if (romanNumber.isAddedToSubtratedNumber.not()) {
                result += romanNumber.value
            }
        }

        for (subtratedNumber in subtractedRomanNumbers) {
            result += subtratedNumber.value
        }

        return result
    }
}

fun main() {
    val input = "MCMXCIV"
    val solutionClass = RomanToIntegerSolution()
    solutionClass.romanToInt(input)
}