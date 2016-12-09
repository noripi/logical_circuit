package main

import display.NumericCharacter
import display.SevenSegmentDisplay
import logical_circuit.Circuit
import logical_circuit.Data
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
    println("this program supports -8 ~ 7 Integer number and '+/-' operator")

    val pattern: Pattern = "(-?\\d+)\\s*\\+\\s*(-?\\d+)".toPattern()

    BufferedReader(InputStreamReader(System.`in`, Charsets.UTF_8)).use { reader ->
        InfiniteIntList(0).forEach {
            print("> ")

            val input: String = reader.readLine().replace("(-?\\d+)\\s*-\\s*(\\d+)".toRegex(),
                    "$1+-$2")

            val matcher: Matcher = pattern.matcher(input)
            if (!matcher.matches()) {
                println("illegal format")
                return@forEach
            }

            val operand0: Quadruplets<Circuit> = BINARY[matcher.group(1).toInt()] ?: run {
                println("operand0 is beyond range"); return@forEach
            }
            val operand1: Quadruplets<Circuit> = BINARY[matcher.group(2).toInt()] ?: run {
                println("operand1 is beyond range"); return@forEach
            }

            val result: Data<Circuit> = FourBitAdder(operand0, operand1).outputs
            println(operand0.toReversedString() + "+" + operand1.toReversedString() + "=" + result.toReversedString())
            SevenSegmentDisplay.println(
                    *NumericCharacter(result as Quadruplets<Circuit>).toTypedArray())
        }
    }
}

class InfiniteIntList(private val value: Int) : Iterable<Int> {
    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            override fun hasNext(): Boolean = true
            override fun next(): Int = this@InfiniteIntList.value
        }
    }
}

