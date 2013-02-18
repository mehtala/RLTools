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

import core.StateActionValues

import examples.GamblersProblem
import examples.CliffWalker

import algorithms.Sarsa
import algorithms.SarsaLambda

import core.Utils

import policies.Policies

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

// StateActionValuesTester
class StateActionValuesTester extends FunSuite with BeforeAndAfter {
  
  var sav: StateActionValues[Symbol,Symbol] = _
    
  // Initialize
  before {
    sav = new StateActionValues()
  }
  
  test("StateActionValues is modified with new value") {
    sav = sav.assoc('up, 'down, 2.0)
	assert(sav('up, 'down) == 2.0)
  }
  
  test("Trying to get non-existence value from StateActionValues") {
	assert(sav('left, 'right, 1.2) == 1.2)
  }
  
}


