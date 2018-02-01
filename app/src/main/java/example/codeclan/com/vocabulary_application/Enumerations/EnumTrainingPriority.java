package example.codeclan.com.vocabulary_application.Enumerations;

/**
 * Created by horizon on 01/02/2018.
 */

public enum EnumTrainingPriority {

    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    String label;

    EnumTrainingPriority(String label){
        this.label = label;
    }

    String getLabel(){
        return this.label;
    }

}
