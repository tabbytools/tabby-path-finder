package tabby.util;

import com.google.gson.Gson;


public class JsonHelper {

    public static Gson gson = new Gson();

    public static int[] parsePollutedPosition(String position){
        try{
            return gson.fromJson(position, int[].class);
        }catch (Exception e){
            return new int[0];
        }
    }

    public static int[][] parse(String polluted){
        try{
            return gson.fromJson(polluted, int[][].class);
        }catch (Exception e){
            
            int[] position = gson.fromJson(polluted, int[].class);
            int[][] ret = new int[position.length][];
            for(int i=0;i<position.length;i++){
                ret[i] = new int[]{position[i]};
            }
            return ret;
        }
    }
}
