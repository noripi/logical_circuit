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

abstract class Tuple<out T>(vararg private val output: T) {
    override fun toString(): String {
        return this.output.map { it.toString() }.joinToString("")
    }

    operator fun get(idx: Int): T {
        return this.output[idx]
    }

    fun toReversedString(): String {
        return this.output.reversed().map { it.toString() }.joinToString("")
    }
}

class Single<out T>(private val first: T) : Tuple<T>(first) {
    operator fun component1(): T = this.first
}

class Twins<out T>(private val first: T, private val second: T) : Tuple<T>(first, second) {
    operator fun component1(): T = this.first
    operator fun component2(): T = this.second
}

class Triplets<out T>(private val first: T, private val second: T,
                      private val third: T) : Tuple<T>(first, second, third) {
    operator fun component1(): T = this.first
    operator fun component2(): T = this.second
    operator fun component3(): T = this.third
}

class Quadruplets<out T>(private val first: T, private val second: T, private val third: T,
                         private val fourth: T) : Tuple<T>(first, second, third, fourth) {
    operator fun component1(): T = this.first
    operator fun component2(): T = this.second
    operator fun component3(): T = this.third
    operator fun component4(): T = this.fourth
}

class Septuplets<out T>(private val first: T, private val second: T, private val third: T,
                        private val fourth: T, private val fifth: T, private val sixth: T,
                        private val seventh: T) : Tuple<T>(first, second, third, fourth, fifth,
        sixth, seventh) {
    operator fun component1(): T = this.first
    operator fun component2(): T = this.second
    operator fun component3(): T = this.third
    operator fun component4(): T = this.fourth
    operator fun component5(): T = this.fifth
    operator fun component6(): T = this.sixth
    operator fun component7(): T = this.seventh
}

