
import com.google.gson.Gson;

public class JSONOperation {
    private static Gson gson = new Gson();

    public static String ObjectToJSON(Object object){
        return gson.toJson(object);
    }

    public static Object JSONToObject(String json){
        return gson.fromJson(json, ScoreBoard.class);
    }



}
