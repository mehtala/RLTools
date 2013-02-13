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

import core.StateActionValues

import examples.GamblersProblem
import examples.CliffWalker

import algorithms.Sarsa
import algorithms.SarsaLambda

import core.Utils

import policies.Policies

// RLTester
object RLTester extends App {
  println("Reinforcement Learning Tester")

  val sav : StateActionValues[Symbol,Symbol] = new StateActionValues()
  println(sav)
  
  val newSav = sav.assoc('up,'down,0.0)
  println(newSav)


  // Run the gamblers problem
//  val game = new GamblersProblem(100, 0.4)
  val game = new CliffWalker(5, 5)
  
  println("Start")
  
  //val sarsa = new Sarsa(0.1, 1.0)
  val sarsal = new SarsaLambda(0.5, 1.0, 0.9)
  val policy = Policies.EpsGreedy(0.1)
  
  println("Computing values")
  
  val result = testing.Timing.time {
    Utils.episodes(sarsal, game, policy, 500)
  }
  
  println("Values")
  println(result)
  
  println("trace")
  val greedy = Policies.Greedy
  val trace1 = Utils.trace(game, greedy, result)
  println(trace1)
  val trace2 = Utils.trace(game, greedy, result)
  println(trace2)
  val trace3 = Utils.trace(game, greedy, result)
  println(trace3)
}


