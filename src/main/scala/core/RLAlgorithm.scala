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
 * Defines the interface that the reinforcement learning algorithm should implement
 * The algorithm uses two generic parameters S for the states and A for the actions.
 */
trait RLAlgorithm {
  
	// Method to initialize the algorithm
	def initialize[S,A] (task: RLGenericTask[S,A]): StateActionValues[S,A]
	
	// Method that performs a single episode
	def performEpisode[S,A] (task: RLGenericTask[S,A], policy: Policy)
		(currentValues: StateActionValues[S,A]): StateActionValues[S,A]
}
