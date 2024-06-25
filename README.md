# Value Iteration Simulation

## Overview

This project implements a Value Iteration algorithm to simulate decision-making under uncertainty. It uses a simple model where actions can lead to states being either "understood" or "not understood," with associated rewards and transition probabilities.

## Project Structure

- **ValueIteration.java**: Main Java class containing the implementation of the Value Iteration algorithm.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.

### Running the Simulation

1. **Compile the Java file**:

    ```sh
    javac ValueIteration.java
    ```

2. **Run the main simulation**:

    ```sh
    java ValueIteration
    ```

## Simulation Details

### Classes and Methods

- **ValueIteration**:
  - `main(String[] args)`: Entry point of the simulation.
  - Implements the Value Iteration algorithm for 5 iterations to update the state values based on given transition probabilities and rewards.

### Constants

- **States**:
  - `UNDERSTOOD`: Represents the state where the situation is understood.
  - `NOT_UNDERSTOOD`: Represents the state where the situation is not understood.
- **Actions**:
  - `STAY_HOME`: Represents the action of staying home.
  - `GO_OUT`: Represents the action of going out.
- **Discount Factor**:
  - `DISCOUNT_FACTOR = 0.9`: Discount factor for future rewards.

### Transition Probabilities

- Transition probabilities for each state-action pair:
  - From `UNDERSTOOD`:
    - `STAY_HOME`: 0.8
    - `GO_OUT`: 0.5
  - From `NOT_UNDERSTOOD`:
    - `STAY_HOME`: 0.5
    - `GO_OUT`: 0.25

### Rewards

- Rewards for each state-action pair:
  - From `UNDERSTOOD`:
    - `STAY_HOME`: 3.0
    - `GO_OUT`: 1.0
  - From `NOT_UNDERSTOOD`:
    - `STAY_HOME`: 3.0
    - `GO_OUT`: 1.0
- Additional rewards for transitioning to `NOT_UNDERSTOOD`:
  - `rewardS1SyToS2 = 6.0`
  - `rewardS1GoToS2 = 4.0`
  - `rewardS2SyToS2 = 6.0`
  - `rewardS2GoToS2 = 4.0`

### Value Iteration

- The algorithm iterates 5 times to update the state values based on the Bellman equation.
- For each state, the algorithm calculates the expected total value for each action, including immediate rewards and discounted future rewards, and updates the state value to the minimum of these expected values.

### Example Output

```sh
Values for iteration 1: {understood=2.7000000000000006, not_understood=4.95}
Values for iteration 2: {understood=3.5550000000000006, not_understood=5.895}
Values for iteration 3: {understood=4.319400000000001, not_understood=6.7255}
Values for iteration 4: {understood=5.053260000000001, not_understood=7.48095}
Values for iteration 5: {understood=5.747547000000001, not_understood=8.167435}
Final Values: {understood=5.747547000000001, not_understood=8.167435}
