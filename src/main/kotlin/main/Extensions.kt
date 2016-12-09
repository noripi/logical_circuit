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
package main

import logical_circuit.And
import logical_circuit.Circuit
import logical_circuit.Not
import logical_circuit.Or
import logical_circuit.Quadruplets
import logical_circuit.Value

fun Boolean.toInt(): Int = if (this) 1 else 0

fun Int.toValue(): Value = Value.valueOf(this)

fun List<Int>.toQuadruplets(): Quadruplets<Circuit> = if (this.size < 4) throw IllegalArgumentException() else Quadruplets(
        this[0].toValue(), this[1].toValue(), this[2].toValue(), this[3].toValue())

operator fun Circuit.plus(c: Circuit) = Or(this, c)

operator fun Circuit.times(c: Circuit) = And(this, c)

operator fun Circuit.not() = Not(this)
