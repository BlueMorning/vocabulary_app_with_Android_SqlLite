package example.codeclan.com.vocabulary_application.Model;

import example.codeclan.com.vocabulary_application.Enumerations.EnumQuestionType;

/**
 * Created by horizon on 03/02/2018.
 */

public class PropositionModel {


    private String label;
    private MeaningModel meaningModel;
    private EnumQuestionType enumQuestionType;

    public PropositionModel(MeaningModel meaningModel, EnumQuestionType enumQuestionType){
        this.meaningModel       = meaningModel;
        this.enumQuestionType   = enumQuestionType;
    }

    public MeaningModel getMeaningModel(){
        return this.meaningModel;
    }

    public String getProposition(){

        switch(enumQuestionType){
            case DEFINITION_WORD:
                this.label = this.meaningModel.getWordModel().getWordEntity().getSpelling();
                break;

            case WORD_EXAMPLE:
                this.label = this.meaningModel.getMeaningEntity().getExample();
                break;

            case EXAMPLE_WORD:
                this.label = this.meaningModel.getWordModel().getWordEntity().getSpelling();
                break;

            case WORD_DEFINITION:
                this.label = this.meaningModel.getMeaningEntity().getMeaning();
                break;

            default:
                this.label = this.meaningModel.getWordModel().getWordEntity().getSpelling();
                break;
        }

        return this.label;
    }
}
