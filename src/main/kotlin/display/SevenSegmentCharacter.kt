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
import logical_circuit.Quadruplets
import logical_circuit.Septuplets
import main.not
import main.plus
import main.times
import main.toValue

/**
 *          1 2 3 4 5 6 7
 * 0000  0  1 1 1 0 1 1 1
 * 0001  1  0 0 1 0 0 1 0
 * 0010  2  1 0 1 1 1 0 1
 * 0011  3  1 0 1 1 0 1 1
 * 0100  4  0 1 1 1 0 1 0
 * 0101  5  1 1 0 1 0 1 1
 * 0110  6  1 1 0 1 1 1 1
 * 0111  7  1 0 1 0 0 1 0
 * 1000 -8  1 1 1 1 1 1 1
 * 1001 -7  1 0 1 0 0 1 0
 * 1010 -6  1 1 0 1 1 1 1
 * 1011 -5  1 1 0 1 0 1 1
 * 1100 -4  0 1 1 1 0 1 0
 * 1101 -3  1 0 1 1 0 1 1
 * 1110 -2  1 0 1 1 1 0 1
 * 1111 -1  0 0 1 0 0 1 0
 */
class NumericCharacter(val n: Quadruplets<Circuit>) : List<SevenSegmentCharacter> by listOf(
        SevenSegmentCharacter(Septuplets(
                0.toValue(),
                0.toValue(),
                0.toValue(),
                n[3].output,
                0.toValue(),
                0.toValue(),
                0.toValue()
        )),
        SevenSegmentCharacter(Septuplets(
                // !a0!a2 + a1!a3 + a1!a2 + !a0a1 + !a2a3 + a0a2!a3 + a0!a1a2 + a0!a1a3
                !n[0] * !n[2] + n[1] * !n[3] + n[1] * !n[2] + !n[0] * n[1] + !n[2] * n[3] + n[0] * n[2] * !n[3] + n[0] * !n[1] * n[2] + n[0] * !n[1] * n[3],
                // !a0!a1 + !a0a2!a3 + a1!a2a3 + !a1a2!a3
                !n[0] * !n[1] + !n[0] * n[2] * !n[3] + n[1] * !n[2] * n[3] + !n[1] * n[2] * !n[3],
                // !a0!a1 + !a1!a2 + !a2!a3 + a2a3 + a0a1a2
                !n[0] * !n[1] + !n[1] * !n[2] + !n[2] * !n[3] + n[2] * n[3] + n[0] * n[1] * n[2],
                // !a0a1 + !a0a3 + !a1a2 + a1!a2
                !n[0] * n[1] + !n[0] * n[3] + !n[1] * n[2] + n[1] * !n[2],
                // !a0a1 + !a0!a2
                !n[0] * n[1] + !n[0] * !n[2],
                // a0 + !a1 + a2!a3 + !a2a3
                n[0] + !n[1] + n[2] * !n[3] + !n[2] * n[3],
                // !a0a1 + !a0!a2 + a1!a2 + a0!a1a2
                !n[0] * n[1] + !n[0] * !n[2] + n[1] * !n[2] + n[0] * !n[1] * n[2]
        ))
)
