package example.codeclan.com.vocabulary_application.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumQuestionType;

/**
 * Created by horizon on 02/02/2018.
 */

public class QuestionModel {

    private EnumQuestionType enumQuestionType;
    private WordModel wordModelToPractice;
    private MeaningModel meaningAnswer;
    private ArrayList<PropositionModel> propositions;
    private WordsRoomDatabase db;
    private int propositionCount;


    public QuestionModel(EnumQuestionType enumQuestionType,
                         WordModel wordToPractice,
                         MeaningModel answerToFind,
                         int propositionCount,
                         WordsRoomDatabase db){
        this.enumQuestionType       = enumQuestionType;
        this.wordModelToPractice    = wordToPractice;
        this.meaningAnswer          = answerToFind;
        this.db                     = db;
        this.propositionCount       = propositionCount;
    }

    public EnumQuestionType getEnumQuestionType() {

        return enumQuestionType;
    }

    public MeaningModel getMeaningAnswer() {
        return meaningAnswer;
    }


    public ArrayList<PropositionModel> getPropositions() {

        return propositions;
    }

    public Boolean checkAnswer(MeaningModel answer){

        return this.meaningAnswer == answer;
    }

    public void initializePropositions(){
        propositions = new ArrayList<>(db.meaningDao().getAnswerPropositions(wordModelToPractice.getWordEntity().getId(),
                EnumMasteryLevel.HIGH,
                wordModelToPractice.getWordEntity().getType(),
                this.propositionCount-1).stream().map(
                        meaningEntity -> {
                            return new PropositionModel(
                                    new MeaningModel(meaningEntity, this.wordModelToPractice), enumQuestionType);
                        }
        ).collect(Collectors.toList()));


        MeaningModel answerMeaningModel = new MeaningModel(this.meaningAnswer.getMeaningEntity(), this.wordModelToPractice);
        propositions.add(new PropositionModel(answerMeaningModel, enumQuestionType));
    }

    public void shuffleAnswers(){
        Collections.shuffle(propositions);
    }



}
