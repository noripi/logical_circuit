package main

import display.NumericCharacter
import display.SevenSegmentDisplay
import logical_circuit.Circuit
import logical_circuit.FourBitAdder
import logical_circuit.Quadruplets
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Copyright (C) 2016 Retty, Inc.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 *
 * @author Noriyuki Ishida
 */

private val BINARY: Map<Int, Quadruplets<Circuit>> = mapOf(
        0 to listOf(0, 0, 0, 0).reversed().toQuadruplets(),
        1 to listOf(0, 0, 0, 1).reversed().toQuadruplets(),
        2 to listOf(0, 0, 1, 0).reversed().toQuadruplets(),
        3 to listOf(0, 0, 1, 1).reversed().toQuadruplets(),
        4 to listOf(0, 1, 0, 0).reversed().toQuadruplets(),
        5 to listOf(0, 1, 0, 1).reversed().toQuadruplets(),
        6 to listOf(0, 1, 1, 0).reversed().toQuadruplets(),
        7 to listOf(0, 1, 1, 1).reversed().toQuadruplets(),
        -8 to listOf(1, 0, 0, 0).reversed().toQuadruplets(),
        -7 to listOf(1, 0, 0, 1).reversed().toQuadruplets(),
        -6 to listOf(1, 0, 1, 0).reversed().toQuadruplets(),
        -5 to listOf(1, 0, 1, 1).reversed().toQuadruplets(),
        -4 to listOf(1, 1, 0, 0).reversed().toQuadruplets(),
        -3 to listOf(1, 1, 0, 1).reversed().toQuadruplets(),
        -2 to listOf(1, 1, 1, 0).reversed().toQuadruplets(),
        -1 to listOf(1, 1, 1, 1).reversed().toQuadruplets()
)

fun main(args: Array<String>) {
    println("this program supports -8 ~ 7 Integer number and '+' operator")

    val pattern: Pattern = "(-?\\d+)\\s*\\+\\s*(-?\\d+)".toPattern()

    BufferedReader(InputStreamReader(System.`in`, Charsets.UTF_8)).use {
        while (true) {
            print("> ")

            val input: String = it.readLine()
            val matcher: Matcher = pattern.matcher(input)
            if (!matcher.matches()) {
                println("illegal format")
                continue
            }

            val operand0: Int = matcher.group(1).toInt()
            val operand1: Int = matcher.group(2).toInt()

            if (operand0 !in -8..7 || operand1 !in -8..7) {
                println("operand is beyond acceptable range")
            }

            println(BINARY[operand0]!!.toReversedString() + "+" + BINARY[operand1]!!.toReversedString() + "=")
            SevenSegmentDisplay.println(
                    *NumericCharacter(FourBitAdder(BINARY[operand0]!!,
                            BINARY[operand1]!!).outputs as Quadruplets<Circuit>).toTypedArray())
        }
    }

}

