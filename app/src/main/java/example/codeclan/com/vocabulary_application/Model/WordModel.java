package example.codeclan.com.vocabulary_application.Model;

import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;

/**
 * Created by horizon on 29/01/2018.
 */

public class WordModel extends WordEntity {

    public WordModel(Long id, EnumWordType type, String spelling, String metadata) {
        super(id, type, spelling, metadata);
    }

    public WordModel(EnumWordType type, String spelling, String metadata) {
        super(type, spelling, metadata);
    }
}
