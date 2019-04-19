package keyboard;

import keyboard.exception.MissingKeyException;

import java.util.Scanner;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class KeyboardTest {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String keys = scanner.nextLine();

        Keyboard keyboard = new Keyboard(8, keys);

        try {
            System.out.println(keyboard.minPath(Alphabet.TEXT));
        } catch (MissingKeyException e) {

        }
    }

}
