package sem3.experiments;

//Never use anything in here for real
public class SimpleSanitizer {


    public static String simpleSanitize(String s){

        String result = s.replaceAll("<b>","").replaceAll("</b>","");

        return result;

    }
}

