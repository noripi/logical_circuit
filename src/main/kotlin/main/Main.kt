package main

import logical_circuit.FullAdder
import logical_circuit.Value

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
    for (i: Int in 0..1) {
        for (j: Int in 0..1) {
            for (k: Int in 0..1) {
                println("FullAdder($i, $j, $k) = " + FullAdder(Value.valueOf(i),
                        Value.valueOf(j), Value.valueOf(k)).outputs)
            }
        }
    }
}

