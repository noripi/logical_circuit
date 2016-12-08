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
package logical_circuit

/**
 * 0 0 -> 0 0
 * 0 1 -> 0 1
 * 1 0 -> 0 1
 * 1 1 -> 1 0
 */
class HalfAdder(inputA: Circuit, inputB: Circuit) : Circuit {
    override val outputs: Data<Circuit> = Twins(
            And(inputA, inputB).output,
            Xor(inputA, inputB).output
    )
}


/**
 * 0 0 0 -> 0 0
 * 0 0 1 -> 0 1
 * 0 1 0 -> 0 1
 * 0 1 1 -> 1 0
 * 1 0 0 -> 0 1
 * 1 0 1 -> 1 0
 * 1 1 0 -> 1 0
 * 1 1 1 -> 1 1
 */
class FullAdder(inputA: Circuit, inputB: Circuit, x: Circuit) : Circuit {
    override val outputs: Data<Circuit> = HalfAdder(inputA, inputB).outputs.let let1@ {
        val (carry1, sum1) = it as Twins
        return@let1 HalfAdder(sum1, x).outputs.let let2@ {
            val (carry2, sum2) = it as Twins
            return@let2 Twins(Or(carry1, carry2).output, sum2)
        }
    }
}
