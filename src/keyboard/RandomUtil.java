package keyboard;

import java.util.Random;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class RandomUtil {

    private static Random random;

    public static Random getRandom() {
        if(random == null)
            random = new Random();
        return random;
    }

}
