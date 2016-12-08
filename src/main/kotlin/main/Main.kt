package main

import logical_circuit.FourBitAdder
import logical_circuit.Quadruplets

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
    for (i1: Int in 0..1) {
        for (i2: Int in 0..1) {
            for (i3: Int in 0..1) {
                for (i4: Int in 0..1) {
                    for (j1: Int in 0..1) {
                        for (j2: Int in 0..1) {
                            for (j3: Int in 0..1) {
                                for (j4: Int in 0..1) {
                                    val i = i1 * 1 + i2 * 2 + i3 * 4 + i4 * 8
                                    val j = j1 * 1 + j2 * 2 + j3 * 4 + j4 * 8
                                    println("FourBitAdder($i, $j) = " +
                                            FourBitAdder(
                                                    Quadruplets(i1.toValue(), i2.toValue(),
                                                            i3.toValue(), i4.toValue()),
                                                    Quadruplets(j1.toValue(), j2.toValue(),
                                                            j3.toValue(), j4.toValue()))
                                                    .outputs.toReversedString())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

