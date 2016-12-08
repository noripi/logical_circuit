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

    fun asCompoundCircuit(): CompoundCircuit {
        return object : CompoundCircuit(this) {}
    }
}

abstract class CompoundCircuit(vararg circuits: Circuit) {
    val outputs: List<Value> = circuits.map { it.output }
}