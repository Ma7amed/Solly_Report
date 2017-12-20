package sample.model;

import java.util.Arrays;

/**
 * Created by m80028770 on 4/22/2017.
 */
public class Util {


    public static String nullRemover(String input) {
        return input==null?"":input;
    }



    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }

}