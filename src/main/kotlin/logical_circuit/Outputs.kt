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

abstract class Data<out T>(vararg private val output: T) {
    override fun toString(): String {
        return this.output.map { it.toString() }.joinToString(", ")
    }

    operator fun get(idx: Int): T {
        return this.output[idx]
    }
}

class Single<out T>(first: T) : Data<T>(first)
class Twins<out T>(first: T, second: T) : Data<T>(first, second)
class Triplets<out T>(first: T, second: T, third: T) : Data<T>(first, second, third)
class Quadruplets<out T>(first: T, second: T, third: T, fourth: T) : Data<T>(first, second, third,
        fourth)
