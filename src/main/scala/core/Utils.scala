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
 * Utils
 */
object Utils {

  // The episodes method return the state,action -pair values after performing n episodes
  def episodes[S,A] (
      algorithm: RLAlgorithm, 
      task: RLGenericTask[S,A], 
      policy: Policy, 
      n: Int): StateActionValues[S,A] = {
    
    // Initialize the algorithm
    val initialValues = algorithm.initialize(task)
    
    // Define the iterate function
    val iterFn = algorithm.performEpisode(task, policy)_
    
    // Perform the iteration
    repeatedly(n, iterFn)(initialValues)
  }
  

  // Helper function to perform x, f(x), f(f(x)), ...
  private def repeatedly[T] (n: Int, fn: T => T)(x: T): T= {
	println("episode: " + n)
    if (n>0) repeatedly(n-1, fn)(fn(x))
    else x
  }
  
  
  // Method to return a trace of state and actions given the setup, starting state, policy, and current values
  def trace[S,A](task: RLGenericTask[S,A], policy: Policy, values: StateActionValues[S,A]): List[S] = {
    task.initialize()
    
    // Recursive loop function
    def loop(state: S, history: List[S]): List[S] = {
      if (task.isTerminal) state :: history
      else {
        val action = policy.next(task, values)
        val r = task.performAction(action)
        val newState = task.state
        val newHistory = state :: history
        
        loop(newState, newHistory)
      } 
    }
    
    val trace = loop(task.state, List())
    trace.reverse
  }
}

