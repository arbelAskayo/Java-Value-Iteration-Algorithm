import java.util.HashMap;
import java.util.Map;

public class ValueIteration {
    private static final String UNDERSTOOD = "understood";
    private static final String NOT_UNDERSTOOD = "not_understood";
    private static final String STAY_HOME = "stay_home";
    private static final String GO_OUT = "go_out";
    private static final double DISCOUNT_FACTOR = 0.9;

    public static void main(String[] args) {
        Map<String, Double> values = new HashMap<>();
        values.put(UNDERSTOOD, 0.0);
        values.put(NOT_UNDERSTOOD, 0.0);

        Map<String, Map<String, Double>> transitionProbabilities = new HashMap<>();
        transitionProbabilities.put(UNDERSTOOD, new HashMap<>());
        transitionProbabilities.get(UNDERSTOOD).put(STAY_HOME, 0.8);
        transitionProbabilities.get(UNDERSTOOD).put(GO_OUT, 0.5);

        transitionProbabilities.put(NOT_UNDERSTOOD, new HashMap<>());
        transitionProbabilities.get(NOT_UNDERSTOOD).put(STAY_HOME, 0.5);
        transitionProbabilities.get(NOT_UNDERSTOOD).put(GO_OUT, 0.25);

        // Rewards
        Map<String, Map<String, Double>> rewards = new HashMap<>();
        rewards.put(UNDERSTOOD, new HashMap<>());
        rewards.get(UNDERSTOOD).put(STAY_HOME, 3.0); // from S1 sy to S1
        rewards.get(UNDERSTOOD).put(GO_OUT, 1.0); // from S1 go to S1

        rewards.put(NOT_UNDERSTOOD, new HashMap<>());
        rewards.get(NOT_UNDERSTOOD).put(STAY_HOME, 3.0); // from S2 sy to S1
        rewards.get(NOT_UNDERSTOOD).put(GO_OUT, 1.0); // from S2 go to S1

        // Additional rewards for transitioning to NOT_UNDERSTOOD
        double rewardS1SyToS2 = 6.0;
        double rewardS1GoToS2 = 4.0;
        double rewardS2SyToS2 = 6.0;
        double rewardS2GoToS2 = 4.0;

        // Value iteration for 5 iterations
        for (int i = 0; i < 5; i++) {
            Map<String, Double> newValues = new HashMap<>();

            for (String state : values.keySet()) {
                double minValue = Double.MAX_VALUE;

                for (String action : new String[]{STAY_HOME, GO_OUT}) {
                    double probSuccess = transitionProbabilities.get(state).get(action);
                    double reward = rewards.get(state).get(action);
                    double rewardForNotUnderstanding = (action.equals(STAY_HOME) && state.equals(UNDERSTOOD)) ? rewardS1SyToS2 :
                            (action.equals(GO_OUT) && state.equals(UNDERSTOOD)) ? rewardS1GoToS2 :
                                    (action.equals(STAY_HOME) && state.equals(NOT_UNDERSTOOD)) ? rewardS2SyToS2 : rewardS2GoToS2;

                    double expectedReward = probSuccess * reward + (1 - probSuccess) * rewardForNotUnderstanding;

                    double futureValue = probSuccess * values.get(UNDERSTOOD) + (1 - probSuccess) * values.get(NOT_UNDERSTOOD);
                    double totalValue = expectedReward + DISCOUNT_FACTOR * futureValue;

                    if (totalValue < minValue) {
                        minValue = totalValue;
                    }
                }

                newValues.put(state, minValue);
            }

            values = newValues;
            System.out.println("Values for iteration "+(i+1)+": " + values);
        }

        System.out.println("Final Values: " + values);
    }
}
