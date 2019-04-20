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
            System.out.printf("%c: %d\t%.2f%%\n", Alphabet.intToChar(i), charCount[i], (float) charCount[i] / 23422 * 100);
        }

        System.out.println();
        for(int i = 0; i < Alphabet.CHAR_COUNT; i++){
            if(charCount[i] != 0)
            System.out.printf("%c", Alphabet.intToChar(i));
        }
    }

}
