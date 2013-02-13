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

package algorithms

import core.RLAlgorithm
import core.RLGenericTask
import core.Policy
import core.StateActionValues

/**
 * Sarsa implements the RLAlgorithm interface for sarsa algorithm
 */
class Sarsa(alpha: Double, gamma: Double)
	extends RLAlgorithm {

  require(alpha >= 0.0 || alpha <= 1.0)
  require(gamma >= 0.0 || gamma <= 1.0)
  
  // Implementation of the initialize -method
  override def initialize[S,A](task: RLGenericTask[S,A]) = new StateActionValues[S,A] ()
	
  
  // Implementation of the performEpisode -method
  override def performEpisode[S,A]
  	  (task: RLGenericTask[S,A], policy: Policy)
      (currentValues: StateActionValues[S,A]): StateActionValues[S,A] = {
	  
    // Initialize the task
	task.initialize()
	  
	// Define recursive loop function to loop over the steps until terminal state is reached
  	def loopSteps(s: S, a: A, values: StateActionValues[S,A]): StateActionValues[S,A] = {
	  
	  // Check if terminal state has been reached and return the current state,action -values or continue stepping
      if (task.isTerminal) values
      else {
        val (newState, newAction, newValues) = performStep(task, policy, s, a, values)
        loopSteps(newState, newAction, newValues)
      }
    }
	
	// Perform the step loop
    val s = task.state
    val a = policy.next(task, currentValues)
    val v = currentValues
    loopSteps(s, a, v)
  } 
  
  
  // Helper function to update the values of the initial-state, action -pair
  private def updateValue[S,A] (initialState: S, initialAction: A, finalState: S, finalAction: A, reward: Double, 
      currentValues: StateActionValues[S,A]): StateActionValues[S,A] = {
	  
  	val Qs = currentValues(initialState, initialAction)
	val QsPrime = currentValues(finalState, finalAction)
	val QsNew = Qs + (alpha * (reward + (gamma * QsPrime) - Qs))
	  
	// Update the value
	currentValues.assoc(initialState, initialAction, QsNew)
  }
	
  
  // Helper function to perform a single step of episode
  private def performStep[S,A] (
      task: RLGenericTask[S,A], 
      policy: Policy, 
	  state: S, 
	  initialAction: A, 
	  currentValues: StateActionValues[S,A]): (S,A,StateActionValues[S,A]) = {

    // Compute the reward
    val reward = task.performAction(initialAction)

    // Observe the final state
    val finalState = task.state
  
    // Make the action at final state
    val finalAction = policy.next(task, currentValues)
  
    // Compute the new state,action -values
    val newValues = updateValue(state, initialAction, finalState, finalAction, reward, currentValues)
  
    // Return the new triplet
    (finalState, finalAction, newValues)
  }
}

