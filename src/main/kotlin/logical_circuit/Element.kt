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

import main.toInt

enum class Value(val value: Boolean) {
    Zero(false), One(true), ;

    override fun toString(): String {
        return this.value.toInt().toString();
    }
}

interface Circuit {
    val output: Value
}

class Const(override val output: Value) : Circuit {
    companion object {
        val ONE = Const(Value.One)
        val ZERO = Const(Value.Zero)
    }

    override fun toString(): String = this.output.toString()
}

class Nand(private val inputA: Circuit, private val inputB: Circuit) : Circuit {
    override val output: Value = if (this.inputA.output.value && this.inputB.output.value) Value.Zero else Value.One
}

class Not(input: Circuit) : Circuit by Nand(input, input)
class And(inputA: Circuit, inputB: Circuit) : Circuit by Not(Nand(inputA, inputB))
class Nor(inputA: Circuit, inputB: Circuit) : Circuit by And(Not(inputA), Not(inputB))
class Or(inputA: Circuit, inputB: Circuit) : Circuit by Not(Nor(inputA, inputB))
class Xor(inputA: Circuit, inputB: Circuit) : Circuit by Not(Or(Nor(inputA, inputB),
        And(inputA, inputB)))
