package keyboard;

import keyboard.exception.MissingKeyException;

/**
 * created: 2019-04-21
 *
 * @author Peter Grajcar
 */
public class Swapping {

    public static void run() {
        String keys = "W eVQfJRyhPNq LFjbs utiEupimle. snteasgA a ro vMwCducnDTHISetiUO";
        StringBuilder builder = new StringBuilder(keys);
        Keyboard keyboard = new Keyboard(8, keys);

        try {
            int score = keyboard.minPath(Alphabet.TEXT);
            System.out.println(score);
            System.out.println(keyboard.getKeys());

            main: for(int i = 0; i < 64; i++) {
                for(int j = i + 1; j < 64; j++) {
                            System.out.printf("\r(%d, %d)", i, j);

                            swap(builder, i, j);

                            keyboard.setKeys(builder.toString());
                            int x = keyboard.minPath(Alphabet.TEXT);

                            if (x < score) {
                                score = x;
                                System.out.println();
                                System.out.println(score);
                                System.out.println(keyboard.getKeys());
                                i = 0;
                                continue main;
                            } else {
                                swap(builder, i, j);
                            }
                        }
            }
        } catch (MissingKeyException e) {}

        System.out.println(keyboard.getKeys());

    }

    private static void swap(StringBuilder builder, int i, int j) {
        char c = builder.charAt(i);
        builder.setCharAt(i, builder.charAt(j));
        builder.setCharAt(j, c);
    }
}
