package example.codeclan.com.vocabulary_application.Model;

import example.codeclan.com.vocabulary_application.Entity.StatsEntity;

/**
 * Created by horizon on 29/01/2018.
 */

public class StatsModel extends StatsEntity {

    public StatsModel(Long id, Long wordId, int trainingStep, int confidenceLevel, int totalAnswers, int totalCorrectAnswers, int totalIncorrectAnswers, int lastTrainingTotalAnswers, int lastTrainingTotalCorrectAnswers, int lastTrainingTotalIncorrectAnswers) {
        super(id, wordId, trainingStep, confidenceLevel, totalAnswers, totalCorrectAnswers, totalIncorrectAnswers, lastTrainingTotalAnswers, lastTrainingTotalCorrectAnswers, lastTrainingTotalIncorrectAnswers);
    }

    public StatsModel(Long wordId, int trainingStep, int confidenceLevel, int totalAnswers, int totalCorrectAnswers, int totalIncorrectAnswers, int lastTrainingTotalAnswers, int lastTrainingTotalCorrectAnswers, int lastTrainingTotalIncorrectAnswers) {
        super(wordId, trainingStep, confidenceLevel, totalAnswers, totalCorrectAnswers, totalIncorrectAnswers, lastTrainingTotalAnswers, lastTrainingTotalCorrectAnswers, lastTrainingTotalIncorrectAnswers);
    }
}
