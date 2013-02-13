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

package policies

import core.Policy
import core.RLGenericTask
import core.StateActionValues

/**
 * Greedy policy returns an action for given state using uniform distribution of actions.
 */
class GreedyPolicy extends Policy {

  // Helper function to fin the maximum value action for given state
  private def findMaxValueAction[S,A](state: S, acts: List[A], currentValues: StateActionValues[S,A]): A = {
    
    // the loop function
    def loop(restActs: List[A], maxAct: A, maxVal: Double): A = {
      if (restActs.isEmpty) maxAct
      else {
        val a = restActs.head
        val valueA = currentValues(state, a)
        
        if (valueA > maxVal) loop(restActs.tail, a, valueA)
        else loop(restActs.tail, maxAct, maxVal)
        
      }
    }
    
    // start looping
    val startAct = acts.head
    loop(acts, startAct, currentValues(state, startAct))
  }
  
  // Implementation of the next -method
  override def next[S,A](task: RLGenericTask[S, A], currentValues: StateActionValues[S,A]): A = {
    findMaxValueAction(task.state, task.actions, currentValues)
  }

}
