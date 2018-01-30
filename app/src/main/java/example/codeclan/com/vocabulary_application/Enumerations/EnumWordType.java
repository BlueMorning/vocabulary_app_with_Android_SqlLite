package example.codeclan.com.vocabulary_application.Enumerations;

/**
 * Created by horizon on 29/01/2018.
 */

public enum  EnumWordType {

    ALL("All", null),
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

    public String getLabel(){
        return this.label;
    }

    public String getShortLabel(){
        return this.shortLabel;
    }

}
