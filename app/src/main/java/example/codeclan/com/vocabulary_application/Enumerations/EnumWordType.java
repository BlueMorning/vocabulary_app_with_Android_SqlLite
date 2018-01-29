package example.codeclan.com.vocabulary_application.Enumerations;

/**
 * Created by horizon on 29/01/2018.
 */

public enum  EnumWordType {

    NOUN("Noun","N"),
    VERB("Verb", "V"),
    ADVERB("Adverb", "AV"),
    PHRASAL_VERB("Phrasal Verb", "PV"),
    ADJECTIVE("Adjective", "AD");

    String label;
    String shortLabel;

    EnumWordType(String label, String shortLabel){
        this.label      = label;
        this.shortLabel = shortLabel;
    }

    String getLabel(){
        return this.label;
    }

    String getShortLabel(){
        return this.shortLabel;
    }

}
