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

package examples

import core.RLGenericTask

/**
 * CliffWalker is problem of finding optimal route around grid world from start location to end location
 * so that walker does not fall over the cliff. Starting coordinates are (0,0) and 
 * end target coordinates are (0, nCols-1). The cliff is defined to be the line between start point and
 * end point: coordinates from (0, 1) to (0, nCols-2).
 * 
 * The states are grid world coordinates represented as tuple of two integers and actions are
 * list of directions where walker can move, represented as symbols: 'up, 'down, 'left, 'right. 
 * 
 * CliffWalker implements the RLProtocol interface for the Gambler's Problem.
 */
class CliffWalker(nRows: Int, nCols: Int) 
	extends RLGenericTask[(Int,Int),Symbol] {

  private val initialState = (0,0)
  
  var currentState = initialState

//  private def helperIsTerminal = (currentMoney == targetMoney) || (currentMoney == 0)
 
  // Implementation of the initialize -method
  override def initialize() = {
    currentState = initialState
  }


  // Implementation of the states -method
  override def state = currentState

  
  // Implementation of the actions -method
  override def actions = {
    val iEndCol = nCols - 1
    val iEndRow = nRows - 1
    
  	currentState match {
  	  case (0, 0) 					=> List('right, 'up)
  	  case (0, `iEndCol`)			=> List('left, 'up)
  	  case (`iEndRow`, 0)	  		=> List('right, 'down)
  	  case (`iEndRow`, `iEndCol`)	=> List('left, 'down)
  	  case (0, _)					=> List('left, 'right, 'up)
  	  case (_, 0)					=> List('right, 'up, 'down)
  	  case (`iEndRow`, _)			=> List('left, 'right, 'down)
  	  case (_, `iEndCol`)			=> List('left, 'up, 'down)
  	  case _						=> List('left, 'right, 'up, 'down)
  	} 
  }
  
  // Helper function to compute the reward
  private def reward(finalState: (Int, Int)) = {
    val (row, col) = finalState
    
    if (row == 0 && col == nCols - 1) 1.0
    else {
      if (row == 0 && col > 0) -1
      else 0
    }
  }
  
  
  // Implementation of the performAction -method	
  override def performAction(action: Symbol): Double = {
	val (row, col) = currentState
    
    action match {
      case 'down 	=> currentState = (row - 1, col)
      case 'up		=> currentState = (row + 1, col)
      case 'left	=> currentState = (row, col - 1)
      case 'right	=> currentState = (row, col + 1)
    }  
      
    reward(currentState)
  } 

  // Implementation of the isTerminal -method
  override def isTerminal = {
    val (row, col) = currentState
    
    (row == 0 && col > 0)
  }
}
