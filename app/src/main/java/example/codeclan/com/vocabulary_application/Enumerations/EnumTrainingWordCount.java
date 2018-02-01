package example.codeclan.com.vocabulary_application.Enumerations;

/**
 * Created by horizon on 01/02/2018.
 */

public enum EnumTrainingWordCount {

    ALL(0, "All"),
    FIVE(5, "5"),
    TEN(10, "10"),
    FIFTEEN(15, "15"),
    TWENTY(20, "20");

    int wordCount;
    String wordCountLabel;

    EnumTrainingWordCount(int wordCount, String label){
        this.wordCount      = wordCount;
        this.wordCountLabel = label;
    }

    public int getWordCount(){
        return this.wordCount;
    }

    public String getWordCountLabel(){
        return this.wordCountLabel;
    }


}
