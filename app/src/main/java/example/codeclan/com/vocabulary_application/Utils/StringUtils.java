package example.codeclan.com.vocabulary_application.Utils;

/**
 * Created by horizon on 31/01/2018.
 */

public class StringUtils {


    public static String capitalize(String string){
        return string.substring(0,1).toUpperCase()+string.substring(1).toLowerCase();
    }

    public static String intToString(int integer){
        return ((Integer)integer).toString();
    }

}
