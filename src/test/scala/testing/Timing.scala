/**
 * This file is part of RLTools.
 * 
 * RLTools is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * RLTools is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with RLTools.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package testing

/**
 * Timing of the code block
 */
object Timing {
  def time[R](block: => R): R = {
    val t0 = System.nanoTime() / 1.0e6
    val result = block    // call-by-name
    val t1 = System.nanoTime() / 1.0e6
    println("Elapsed time: " + (t1 - t0) + "ms")
    result
  }
}