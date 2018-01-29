package example.codeclan.com.vocabulary_application.Entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "meanings", foreignKeys = @ForeignKey(   entity        = WordEntity.class,
                                                            parentColumns = "wrd_id",
                                                            childColumns  = "mig_wrd_id"))
public class MeaningEntity {


    // Properties

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="mig_id")
    private Long id;

    @ColumnInfo(name="mig_definition")
    private String definition;

    @ColumnInfo(name = "mig_example")
    private String example;

    @ColumnInfo(name = "mig_synonym")
    private String synonyms;

    @ColumnInfo(name = "mig_antonym")
    private String antonyms;

    @ColumnInfo(name= "mig_wrd_id")
    private Long wordId;

    public MeaningEntity(Long id, Long wordId, String definition, String example, String synonyms, String antonyms) {
        this.id         = id;
        this.definition = definition;
        this.example    = example;
        this.synonyms   = synonyms;
        this.antonyms   = antonyms;
        this.wordId     = wordId;
    }

    @Ignore
    public MeaningEntity(Long wordId, String definition, String example, String synonyms, String antonyms) {
        this.wordId     = wordId;
        this.definition = definition;
        this.example    = example;
        this.synonyms   = synonyms;
        this.antonyms   = antonyms;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(String antonyms) {
        this.antonyms = antonyms;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }
}
