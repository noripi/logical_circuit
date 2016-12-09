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
 * 0 0 -> 1
 * 0 1 -> 1
 * 1 0 -> 1
 * 1 1 -> 0
 */
class Nand(private val inputA: Circuit, private val inputB: Circuit) : Circuit {
    override val outputs: Data<Circuit>
        get() {
            var outputA: Circuit = this.inputA
            while (outputA !is Constant) outputA = outputA.output

            var outputB: Circuit = this.inputB
            while (outputB !is Constant) outputB = outputB.output

            return if (outputA == Value.One && outputB == Value.One) {
                Single(Value.Zero)
            } else {
                Single(Value.One)
            }
        }
}

/**
 * 0 -> 1
 * 1 -> 0
 */
class Not(input: Circuit) : Circuit by Nand(input, input)

/**
 * 0 0 -> 0
 * 0 1 -> 0
 * 1 0 -> 0
 * 1 1 -> 1
 */
class And(inputA: Circuit, inputB: Circuit) : Circuit by Not(Nand(inputA, inputB))

/**
 * 0 0 -> 1
 * 0 1 -> 0
 * 1 0 -> 0
 * 1 1 -> 0
 */
class Nor(inputA: Circuit, inputB: Circuit) : Circuit by And(Not(inputA), Not(inputB))

/**
 * 0 0 -> 0
 * 0 1 -> 1
 * 1 0 -> 1
 * 1 1 -> 1
 */
class Or(inputA: Circuit, inputB: Circuit) : Circuit by Not(Nor(inputA, inputB))

/**
 * 0 0 -> 0
 * 0 1 -> 1
 * 1 0 -> 1
 * 1 1 -> 0
 */
class Xor(inputA: Circuit, inputB: Circuit) : Circuit by Not(Or(Nor(inputA, inputB),
        And(inputA, inputB)))
