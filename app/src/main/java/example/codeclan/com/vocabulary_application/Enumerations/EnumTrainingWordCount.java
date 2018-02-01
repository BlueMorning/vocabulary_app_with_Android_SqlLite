package example.codeclan.com.vocabulary_application.Enumerations;

/**
 * Created by horizon on 01/02/2018.
 */

public enum EnumTrainingWordCount {

    FIVE(5),
    TEN(10),
    FIFTEEN(15),
    TWENTY(20);

    int wordCount;

    EnumTrainingWordCount(int wordCount){
        this.wordCount = wordCount;
    }

    int getWordCount(){
        return this.wordCount;
    }


}
