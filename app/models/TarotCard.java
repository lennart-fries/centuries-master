package models;

import java.util.*;
import java.util.function.Function;

public enum TarotCard {

    FOOL("The Fool", 3, question -> {
        return getWrongAnswers(1, question.getCorrectAnswer());
    }),
    WHEEL_OF_FORTUNE("Wheel of Fortune", 5, question -> {
        return getWrongAnswers(2, question.getCorrectAnswer());
    }),
    JUDGEMENT("Judgement", 7, question -> {
        return getWrongAnswers(3, question.getCorrectAnswer());
    });

    private String name;
    private int cost;
    private Function<QuestionFactory.Question, List<Integer>> function;

    TarotCard(String name, int cost, Function<QuestionFactory.Question, List<Integer>> function) {
        this.name = name;
        this.cost = cost;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    /**
     * Applies the Effect of a Card
     *
     * @param question the question the effect shall be applied to
     * @return a list containing integer values
     */
    public List<Integer> applyEffect(QuestionFactory.Question question) {
        return function.apply(question);
    }

    /**
     * Looks for wrong answers and returns them
     *
     * @param times       integer representing the amount of runs for this method
     * @param rightAnswer integer representing the correctAnswer
     * @return a list containing the indices of wrong answers
     */
    public static List<Integer> getWrongAnswers(int times, int rightAnswer) {
        List<Integer> wrongAnswers = new LinkedList<>();
        int wrongAnswer = 1;
        while (times > 0) {
            if (wrongAnswer == rightAnswer) {
                wrongAnswer++;
            }
            wrongAnswers.add(wrongAnswer);
            wrongAnswer++;
            times--;
        }
        return wrongAnswers;
    }
}
