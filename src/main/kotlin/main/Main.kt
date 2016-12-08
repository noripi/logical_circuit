package main

import logical_circuit.Const
import logical_circuit.HalfAdder
import logical_circuit.Xor

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

fun main(args: Array<String>) {
    println("HalfAdder(0, 0) = " + HalfAdder(Const.ZERO, Const.ZERO).output)
    println("HalfAdder(1, 0) = " + HalfAdder(Const.ONE, Const.ZERO).output)
    println("HalfAdder(0, 1) = " + HalfAdder(Const.ZERO, Const.ONE).output)
    println("HalfAdder(1, 1) = " + HalfAdder(Const.ONE, Const.ONE).output)
}
