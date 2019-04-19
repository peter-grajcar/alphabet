package keyboard;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class Analysis {

    public static void run() {
        int[] charCount = new int[Alphabet.CHAR_COUNT];

        for(int i = 0; i < Alphabet.TEXT.length(); i++){
            int index = Alphabet.charToInt(Alphabet.TEXT.charAt(i));
            charCount[index]++;
        }

        for(int i = 0; i < Alphabet.CHAR_COUNT; i++){
            System.out.printf("%c: %d\n", Alphabet.intToChar(i), charCount[i]);
        }
    }

}
