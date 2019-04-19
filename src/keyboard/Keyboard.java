package keyboard;

import keyboard.exception.MissingKeyException;

import java.util.ArrayList;
import java.util.List;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class Keyboard {

    private int width;
    private int size;
    private String keys;
    private int[] keyCount;

    class PV {
        int pos;
        int val;

        public PV(int pos, int val){
            this.pos = pos;
            this.val = val;
        }
    }

    public Keyboard(int width, String keys) {
        this.width = width;
        this.keys = keys;
        this.size = keys.length();
        this.keyCount = new int[Alphabet.CHAR_COUNT];

        for(int i = 0; i < size; i++){
            keyCount[Alphabet.charToInt(keys.charAt(i))]++;
        }
    }

    public int getDistance(int a, int b) {
        int x1 = a % width; int y1 = a / width;
        int x2 = b % width; int y2 = b / width;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public int minPath(String text) throws MissingKeyException {
        List<PV> A = new ArrayList<>();
        List<PV> B = new ArrayList<>();

        A.add(new PV(0, 0));
        for(int i = 0; i < text.length(); i++) {
            for(int j = 0; j < size(); j++) {
                if(text.charAt(i) == get(j)) {
                    int min = -1;
                    for(PV pv : A) {
                        int dist = getDistance(pv.pos, j);
                        if(min == -1 || pv.val + dist + 1 < min)
                            min = pv.val + dist + 1;
                    }
                    B.add(new PV(j, min));
                }
            }

            if(B.size() == 0)
                throw new MissingKeyException(text.charAt(i));

            A = B;
            B = new ArrayList<>();
        }

        int min = -1;
        for(PV pv : A) {
            if(min == -1 || pv.val < min)
                min = pv.val;
        }

        return min;
    }

    public void print() {
        for(int i = 0; i < keys.length(); i++){
            if(i > 0 && i % width == 0)
                System.out.println();
            System.out.print(keys.charAt(i));

        }
        System.out.println();
    }

    public char get(int i){
        return keys.charAt(i);
    }

    public int size() {
        return size;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
        this.size = keys.length();

        for(int i = 0; i < size; i++){
            keyCount[Alphabet.charToInt(keys.charAt(i))]++;
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[] getKeyCount() {
        return keyCount;
    }
}
