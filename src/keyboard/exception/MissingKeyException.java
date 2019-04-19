package keyboard.exception;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class MissingKeyException extends Exception {

    public MissingKeyException(char key){
        super("Missing key: " + key);
    }

}
