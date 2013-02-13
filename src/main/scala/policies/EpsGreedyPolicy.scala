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
 * Epsilon greedy policy returns an action for given state using epsilon greedy distribution of actions
 */
class EpsGreedyPolicy(eps: Double) extends Policy {
  require(eps >= 0.0)
  require(eps <= 1.0)
  
  val greedy = new GreedyPolicy()
  val uniform = new UniformPolicy()
  
  // Implementation of the next -method
  override def next[S,A](task: RLGenericTask[S, A], currentValues: StateActionValues[S,A]): A = {
	val r = Math.random()
	
	if (r > eps) greedy.next(task, currentValues)
	else uniform.next(task, currentValues)
  }
}
