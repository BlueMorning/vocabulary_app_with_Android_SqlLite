package example.codeclan.com.vocabulary_application.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;


@Entity(tableName = "words")
public class WordEntity implements Serializable {


    // Properties

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wrd_id")
    @NonNull
    public Long id;

    @ColumnInfo(name = "wrd_type")
    private EnumWordType type;

    @ColumnInfo(name="wrd_spelling")
    private String spelling;

    @ColumnInfo(name = "wrd_metadata")
    private String metadata;

    public WordEntity(Long id, EnumWordType type, String spelling, String metadata) {
        this.id       = id;
        this.type     = type;
        this.spelling = spelling;
        this.metadata = metadata;
    }

    @Ignore
    public WordEntity(EnumWordType type, String spelling, String metadata) {
        this.type     = type;
        this.spelling = spelling;
        this.metadata = metadata;
    }


    // Setters and Getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumWordType getType() {
        return this.type;
    }

    public void setType(EnumWordType type) {
        this.type = type;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
