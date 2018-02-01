package example.codeclan.com.vocabulary_application.Enumerations;


public enum EnumTrainingStatus {

    ALL("All"),
    ONGOING("Ongoing"),
    DONE("Done");

    private String label;

    EnumTrainingStatus(String label){
        this.label = label;
    }

    String getLabel(){
        return this.label;
    }

}
