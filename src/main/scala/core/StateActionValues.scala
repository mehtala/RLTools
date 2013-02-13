/**
    RLTools is a library for reinforcement learning methods. 
   
    Copyright (C) 2013  Petteri Mehtala (petteri.mehtala@gmail.com)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

	Also add information on how to contact you by electronic and paper mail.

 */

package core

/**
 * StateActionValues represents the values of state,action -pairs.
 */
class StateActionValues[S,A] (init: Map[(S,A),Double]) {
  
  private val values: Map[(S,A),Double] = init
  
  // Additional constructor
  def this() = this(Map())
  
  // The assoc -method associates a new state, action -pair with a value
  def assoc (state: S, action: A, value: Double) = {
    val newMap = values + ((state,action) -> value)
    new StateActionValues(newMap)
  }
  
  // The apply -method return the current 
  def apply (state: S, action: A, default: Double = 0.0): Double = {
    if (values.contains((state, action))) values((state, action))
    else default
  }
  
  // The keys -method returns the state,action -paris
  def keys = values.keys
   
  // Override the toString method for nicer printing
  override def toString() = {
    val listOfValues = values.toList
    
    listOfValues.foldLeft("") { (acc, x) => 
      acc + "\n" + "state: " + x._1._1 + ",\taction: " + x._1._2 + ":\t" + x._2
    }
  } 
}
