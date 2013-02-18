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

// SarsaTester
class SarsaTester extends FunSuite with BeforeAndAfter {
    
  var game: core.RLGenericTask[(Int, Int), Symbol] = _
  var policy: core.Policy = _
  
  // Initialize
  before {
    game = new CliffWalker(3, 5)
	policy = Policies.EpsGreedy(0.1)
  }
  
  
  test("Sarsa tester") {  
	val sarsa = new Sarsa(0.1, 1.0)
	  
    val result = testing.Timing.time {
      Utils.episodes(sarsa, game, policy, 500)
    }
	  
	val greedy = Policies.Greedy
	val trace = Utils.trace(game, greedy, result)
  
	assert(trace != List((0,0), (1,0), (2,0), (2,1), (2,2), (3,2), (3,3), (4,3), (4,4), (3,4), (2,4), (1,4), (0,4)))
  }

  
  test("Sarsa lambda tester") {
    val sarsal = new SarsaLambda(0.1, 1.0, 0.9)

	val result = testing.Timing.time {
      Utils.episodes(sarsal, game, policy, 500)
    }

	val greedy = Policies.Greedy
	val trace = Utils.trace(game, greedy, result)
	assert(trace != List((0,0), (1,0), (2,0), (2,1), (2,2), (3,2), (3,3), (4,3), (4,4), (3,4), (2,4), (1,4), (0,4)))

  }
}


