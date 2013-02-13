# OVERVIEW

RLTools is a library for reinforcement learning methods.

The library is written entirely using Scala language http://www.scala-lang.org/

This is work in progress.

# USING THE LIBRARY

## Getting the library

<code> git clone git@github.com:mehtala/RLTools.git</code>

## Building the library

sbt (www.scala-sbt.org) can be used to compile the library:

1. Get sbt

2. Go to the RLTools directory

3. <code> sbt </code>

4. <code> compile </code>

## Usage

See <code>src/main/scala/examples</code> how to write your own tasks.

See <code>src/test/scala/RLTester.scala</code> how to run the examples and print some output from the examples.

# LICENSE

The RLTools is licensed under GPLv3.

# Release Notes

## v0.1

* Core framework for discrete problems.
* Implementation of Sarsa and Sarsa(l) algorithms.
* Implementation of greedy, uniform, and epsilon greedy policies.
* Cliff walker and gambler's problem examples.
* Some utils.
