package keyboard.evolution;

import keyboard.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class GeneticCode {

    private List<Transposition> code;

    static class Transposition {
        int x, y;
        public Transposition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public GeneticCode(int size) {
        code = new ArrayList<>();

        for(int i = 0; i < size; i++){
            int x = RandomUtil.getRandom().nextInt(64);
            int y = RandomUtil.getRandom().nextInt(64);
            code.add(new Transposition(x, y));
        }
    }

    public GeneticCode(List<Transposition> code) {
        this.code = code;
    }

    public int length(){
        return code.size();
    }

    public List<Transposition> getTranspositions() {
        return code;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Transposition t : code) {
            builder.append(t.x);
            builder.append(',');
            builder.append(t.y);
            builder.append(' ');
        }
        return builder.toString();
    }

}
