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
package display

import logical_circuit.Circuit
import logical_circuit.Septuplets
import logical_circuit.Value

class SevenSegmentDisplay {
    companion object {
        fun println(vararg chars: SevenSegmentCharacter) {
            val stringBuilder: StringBuilder = StringBuilder()
            for (i in 0..chars[0].lines.size - 1) {
                stringBuilder.append(chars.map { it.lines[i] }.joinToString(" "))
                stringBuilder.append("\n")
            }

            println(stringBuilder.toString())
        }
    }
}

class SevenSegmentCharacter(inputs: Septuplets<Circuit>) {
    private val s1: String = if (inputs[0].output == Value.One) "----" else "    "
    private val s2: String = if (inputs[1].output == Value.One) "|" else " "
    private val s3: String = if (inputs[2].output == Value.One) "|" else " "
    private val s4: String = if (inputs[3].output == Value.One) "----" else "    "
    private val s5: String = if (inputs[4].output == Value.One) "|" else " "
    private val s6: String = if (inputs[5].output == Value.One) "|" else " "
    private val s7: String = if (inputs[6].output == Value.One) "----" else "    "

    val lines: List<String> = listOf(
            " ${this.s1} ",
            "${this.s2}    ${this.s3}",
            " ${this.s4} ",
            "${this.s5}    ${this.s6}",
            " ${this.s7} "
    )
}
