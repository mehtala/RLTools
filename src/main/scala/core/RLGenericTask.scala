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

 */

package core

 /**
  * Defines the protocol for generic reinforcement learning tasks can implement.
  * This protocol assumes no prior knowledge of the states, actions, transitions, and rewards.
  * All information can be obtained on-line.
  * The task uses two generic parameters S for the states and A for the actions.
  */
trait RLGenericTask[S, A] {
  
	// Method that initializes the tasks. This method is called at the beginning of episode.
    // Any initialization that task requires should be placed into this method.
	def initialize()
	
	// Method that return the current state
	def state: S

	// Method that returns possible actions in current state
	def actions: List[A]

	// Method to perform given action. Returns the reward
	def performAction(action: A): Double
	
    // Method that returns the logical true if the current state is terminal state, otherwise false	
	def isTerminal: Boolean
}
