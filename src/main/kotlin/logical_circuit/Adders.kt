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
            Xor(inputA, inputB).output,
            And(inputA, inputB).output
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
    override val outputs: Data<Circuit> = HalfAdder(inputA, inputB).outputs
            .let {
                val (sum1, carry1) = it as Twins; HalfAdder(sum1, x).outputs
                    .let {
                        val (sum2, carry2) = it as Twins; Twins(sum2, Or(carry1, carry2).output)
                    }
            }
}

class TwoBitAdder(inputA: Twins<Circuit>, inputB: Twins<Circuit>) : Circuit {
    override val outputs: Data<Circuit> = FullAdder(inputA[0], inputB[0], Value.Zero).outputs
            .let {
                val (sum0, carry0) = it as Twins
                Twins(sum0, FullAdder(inputA[1], inputB[1], carry0).output)
            }
}

class ThreeBitAdder(inputA: Triplets<Circuit>, inputB: Triplets<Circuit>) : Circuit {
    override val outputs: Data<Circuit> = FullAdder(inputA[0], inputB[0], Value.Zero).outputs
            .let {
                val (sum0, carry0) = it as Twins
                FullAdder(inputA[1], inputB[1], carry0).outputs
                        .let {
                            val (sum1, carry1) = it as Twins
                            Triplets(sum0, sum1, FullAdder(inputA[2], inputB[2], carry1).output)
                        }
            }
}

class FourBitAdder(inputA: Quadruplets<Circuit>, inputB: Quadruplets<Circuit>) : Circuit {
    override val outputs: Data<Circuit> = FullAdder(inputA[0], inputB[0], Value.Zero).outputs
            .let {
                val (sum0, carry0) = it as Twins
                FullAdder(inputA[1], inputB[1], carry0).outputs
                        .let {
                            val (sum1, carry1) = it as Twins
                            FullAdder(inputA[2], inputB[2], carry1).outputs
                                    .let {
                                        val (sum2, carry2) = it as Twins
                                        Quadruplets(sum0, sum1, sum2,
                                                FullAdder(inputA[3], inputB[3], carry2).output)
                                    }
                        }
            }
}
