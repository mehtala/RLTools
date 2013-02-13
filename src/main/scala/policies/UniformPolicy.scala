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

package policies

import core.Policy
import core.RLGenericTask
import core.StateActionValues

/**
 * Uniform policy returns an action for given state using uniform distribution of actions.
 */
class UniformPolicy extends Policy {
  override def next[S,A](task: RLGenericTask[S, A], currentValues: StateActionValues[S,A]): A = {
    val acts = task.actions
    val nActions = acts.length
    
    val iAction = Math.floor(Math.random * nActions).toInt
    
    acts(iAction)
  }

}
