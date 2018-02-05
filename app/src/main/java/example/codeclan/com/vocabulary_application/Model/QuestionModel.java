package example.codeclan.com.vocabulary_application.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumQuestionType;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.Utils.StringUtils;

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

    public String getQuestionLabel(){

        String questionLabel;

        switch(enumQuestionType){
            case DEFINITION_WORD:
                questionLabel = this.meaningAnswer.getMeaningEntity().getMeaning();
                break;

            case WORD_EXAMPLE:
                questionLabel = this.meaningAnswer.getWordModel().getWordEntity().getSpelling();
                break;

            case EXAMPLE_WORD:
                questionLabel = this.meaningAnswer.getMeaningEntity().getExample();
                break;

            case WORD_DEFINITION:
                questionLabel = this.meaningAnswer.getWordModel().getWordEntity().getSpelling();
                break;

            default:
                questionLabel = this.meaningAnswer.getWordModel().getWordEntity().getSpelling();
                break;
        }

        return questionLabel;
    }


    public ArrayList<PropositionModel> getPropositions() {
        return propositions;
    }

    public Boolean checkAnswer(MeaningModel answer){

        return this.meaningAnswer.getMeaningEntity().getId() == answer.getMeaningEntity().getId();
    }

    public void initializePropositions(){
        propositions = new ArrayList<>(db.meaningDao().getAnswerPropositions(wordModelToPractice.getWordEntity().getId(),
                EnumMasteryLevel.HIGH,
                wordModelToPractice.getWordEntity().getType(),
                this.propositionCount-1).stream().map(
                        meaningEntity -> {
                            return new PropositionModel(
                                    new MeaningModel(meaningEntity,
                                            new WordModel(db.wordDao().getWordByWordId(meaningEntity.getWordId()), db)), enumQuestionType);
                        }
        ).collect(Collectors.toList()));


        MeaningModel answerMeaningModel = new MeaningModel(this.meaningAnswer.getMeaningEntity(), this.wordModelToPractice);
        propositions.add(new PropositionModel(answerMeaningModel, enumQuestionType));
    }

    public void shuffleAnswers(){

        Collections.shuffle(propositions);
    }

    public String getMessageCorrectAnswer(MeaningModel meaningModel){
        String answerMessage = "Correct answer !";

        if(! StringUtils.isEmptyOrNull(meaningModel.getMeaningEntity().getSynonyms())){
            answerMessage += " Synonyms = " + meaningModel.getMeaningEntity().getSynonyms();
        }

        if(! StringUtils.isEmptyOrNull(meaningModel.getMeaningEntity().getAntonyms())){
            answerMessage += " Antonyms = " + meaningModel.getMeaningEntity().getSynonyms();
        }
        return answerMessage;
    }

    public String getMessageIncorrectAnswer(MeaningModel userAnswer){

        String incorrectAnswerMessage = "Incorrect answer. ";

        switch(enumQuestionType){
            case DEFINITION_WORD:
                incorrectAnswerMessage += String.format(" %s = %s ",
                        userAnswer.getWordModel().getWordEntity().getSpelling(),
                        userAnswer.getMeaningEntity().getMeaning());
                break;

            case WORD_EXAMPLE:
                incorrectAnswerMessage += String.format(" %s = %s ",
                        userAnswer.getWordModel().getWordEntity().getSpelling(),
                        userAnswer.getMeaningEntity().getExample());
                break;

            case EXAMPLE_WORD:
                incorrectAnswerMessage += String.format(" %s = %s ",
                        userAnswer.getWordModel().getWordEntity().getSpelling(),
                        userAnswer.getMeaningEntity().getMeaning());
                break;

            case WORD_DEFINITION:
                incorrectAnswerMessage += String.format(" %s = %s ",
                        userAnswer.getWordModel().getWordEntity().getSpelling(),
                        userAnswer.getMeaningEntity().getMeaning());
                break;

            default:
                incorrectAnswerMessage += String.format(" %s = %s ",
                        userAnswer.getMeaningEntity().getMeaning(),
                        userAnswer.getWordModel().getWordEntity().getSpelling());
                break;
        }

        return incorrectAnswerMessage;
    }

}
