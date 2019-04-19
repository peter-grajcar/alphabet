package keyboard;

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

    public Keyboard(int width, String keys) {
        this.width = width;
        this.keys = keys;
        this.size = keys.length();
    }

    public int getDistance(int a, int b) {
        int x1 = a % width; int y1 = a / width;
        int x2 = b % width; int y2 = b / width;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public int minPath(String text) {
        List<Main.PV> A = new ArrayList<>();
        List<Main.PV> B = new ArrayList<>();

        A.add(new Main.PV(0, 0));
        for(int i = 0; i < text.length(); i++) {
            for(int j = 0; j < getSize(); j++) {
                if(text.charAt(i) == get(j)) {
                    int min = -1;
                    for(Main.PV pv : A) {
                        int dist = getDistance(pv.pos, j);
                        if(min == -1 || pv.val + dist + 1 < min)
                            min = pv.val + dist + 1;
                    }
                    B.add(new Main.PV(j, min));
                }
            }
            A = B;
            B = new ArrayList<>();
        }

        int min = -1;
        for(Main.PV pv : A) {
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

    public int getSize() {
        return size;
    }
}
