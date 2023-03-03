package tabby.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public class Transformer {

    public static int[] setToIntArray(Set<Integer> set){
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    public static Set<Integer> intArrayToSet(int[] array){
        return Arrays.stream(array).boxed().collect(Collectors.toSet());
    }

    public static Set<Integer> flat(int[][] array){
        int[] ret = Arrays.stream(array).flatMapToInt(o -> Arrays.stream(o)).toArray();
        return intArrayToSet(ret);
    }


}
