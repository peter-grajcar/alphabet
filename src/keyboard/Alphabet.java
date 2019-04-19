package keyboard;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class Alphabet {

    public static final int CHAR_COUNT = 54;

    public static int charToInt(char c) {
        if(c == 0)
            return c;
        if(c == ' ')
            return 1;
        if(c == '.')
            return 2;
        if(c >= 'A' && c <= 'Z')
            return 3 + (c - 'A');
        if(c >= 'a' && c <= 'z')
            return 29 + (c - 'a');
        return -1;
    }

    public static char intToChar(int i) {
        if(i == 0)
            return 0;
        if(i == 1)
            return ' ';
        if(i == 2)
            return '.';
        if(i >= 3 && i <= 28)
            return (char) ('a' + (i - 3));
        if(i >= 29 && i <= 54)
            return (char) ('A' + (i - 29));
        return '?';
    }

}
