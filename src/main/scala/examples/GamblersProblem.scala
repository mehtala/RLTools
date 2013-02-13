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

package examples

import core.RLGenericTask

/**
 * Gambler's Problem is a coin flip game. The problem is to find optimal actions,
 * given current money the gambler holds. The gambler wins if he achieves
 * the target amount of money and loses if all the money is lost. 
 * Possible actions in each state are the bet gambler puts on stake and can be upto the money he holds.
 * States are then s <- {0, 1, ..., N}, and actions a <- {1, 2, ..., min(s, N-s)} 
 *
 * GamblersProblem implements the RLProtocol interface for the Gambler's Problem.
 */
class GamblersProblem(targetMoney: Int, prob: Double) 
	extends RLGenericTask[Int,Int] {

  var currentMoney: Int = 0

  private def helperIsTerminal = (currentMoney == targetMoney) || (currentMoney == 0)
 
  // Implementation of the initialize -method
  override def initialize() = {
    currentMoney = Math.floor(targetMoney / 2).toInt
  }

  // Implementation of the states -method
  override def state = currentMoney
  
  // Implementation of the actions -method
  override def actions = 
    if (helperIsTerminal) List(0)
    else {
      val maxBet = Math.min(currentMoney, targetMoney - currentMoney)
      Range.inclusive(1, maxBet).toList
    }
  
  // Implementation of the performAction -method	
  override def performAction(action: Int): Double = {
    if (prob > Math.random) currentMoney += action
    else currentMoney -= action
    
    if (currentMoney == targetMoney) 1.0
    else 0.0
  } 

  // Implementation of the isTerminal -method
  override def isTerminal = helperIsTerminal
  
}
  
