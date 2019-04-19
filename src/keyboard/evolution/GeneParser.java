package keyboard.evolution;

import java.util.ArrayList;
import java.util.List;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class GeneParser {

    public static GeneticCode parse(String s) {
        String[] arr = s.split(",| ");
        List<GeneticCode.Transposition> transpositions = new ArrayList<>();

        for(int i = 0; i < arr.length; i += 2) {
            int x = Integer.parseInt(arr[i]);
            int y = Integer.parseInt(arr[i + 1]);
            transpositions.add(new GeneticCode.Transposition(x, y));
        }

        return new GeneticCode(transpositions);
    }

}
