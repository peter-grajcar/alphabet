package keyboard.evolution;

import keyboard.Alphabet;
import keyboard.Keyboard;
import keyboard.RandomUtil;
import keyboard.exception.MissingKeyException;

import java.util.ArrayList;
import java.util.List;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class KeyboardIndividual {

    // Value from 0-255
    public static final int MUTATION_RATE = 16;
    public static final String DEFAULT_KEYBOARD_KEYS = "ACDEFHIJKLMNOPQRSTUVWaabcdeeefghiiijlmnopqrssttuuvwy.           ";
    public static final int DEFAULT_KEYBOARD_SIZE = 64;
    public static final int DEFAULT_KEYBOARD_WIDTH = 8;

    private GeneticCode geneticCode;
    private Keyboard keyboard;
    private int fitness = -1;

    public KeyboardIndividual(GeneticCode geneticCode){
        setGeneticCode(geneticCode);
    }

    public GeneticCode getGeneticCode() {
        return geneticCode;
    }

    public void setGeneticCode(GeneticCode geneticCode) {
        this.geneticCode = geneticCode;
        createKeyboard();
    }

    public void createKeyboard() {
        StringBuilder builder = new StringBuilder(DEFAULT_KEYBOARD_KEYS);
        for(GeneticCode.Transposition t : geneticCode.getTranspositions()){
            char c = builder.charAt(t.x);
            builder.setCharAt(t.x, builder.charAt(t.y));
            builder.setCharAt(t.y, c);
        }
        this.keyboard = new Keyboard(DEFAULT_KEYBOARD_WIDTH, builder.toString());
        calculateFitness();
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public int getFitness() {
        if(fitness == -1)
            calculateFitness();
        return fitness;
    }

    public void calculateFitness() {
        try {
            this.fitness = keyboard.minPath(Alphabet.TEXT);
        } catch (MissingKeyException e) {
            this.fitness = 0;
        }
    }

    public void mutate(){
        boolean changed = false;

        for(int i = 0; i < geneticCode.length(); i++){
            if(RandomUtil.getRandom().nextInt(255) > MUTATION_RATE)
                continue;

            int x, y;
            switch(RandomUtil.getRandom().nextInt(3)) {
                case 0: // Insertion
                    x = RandomUtil.getRandom().nextInt(DEFAULT_KEYBOARD_SIZE);
                    y = RandomUtil.getRandom().nextInt(DEFAULT_KEYBOARD_SIZE);
                    geneticCode.getTranspositions().add(i, new GeneticCode.Transposition(x, y));
                    break;
                case 1: // Deletion
                    geneticCode.getTranspositions().remove(i);
                    break;
                case 2: // Missense
                    x = RandomUtil.getRandom().nextInt(DEFAULT_KEYBOARD_SIZE);
                    y = RandomUtil.getRandom().nextInt(DEFAULT_KEYBOARD_SIZE);
                    geneticCode.getTranspositions().set(i, new GeneticCode.Transposition(x, y));
                    break;
            }
            changed = true;
        }

        if(changed) {
            createKeyboard();
        }
    }

    public KeyboardIndividual crossover(KeyboardIndividual b) {
        List<GeneticCode.Transposition> transpositions = new ArrayList<>();

        int length = Math.min(geneticCode.length(), b.geneticCode.length());
        for(int i = 0; i < length; i++) {
            if(RandomUtil.getRandom().nextBoolean()){
                transpositions.add( geneticCode.getTranspositions().get(i) );
            } else {
                transpositions.add( b.geneticCode.getTranspositions().get(i) );
            }
        }
        for(int i = length; i < geneticCode.length(); i++) {
            transpositions.add( geneticCode.getTranspositions().get(i) );
        }

        GeneticCode geneticCode = new GeneticCode(transpositions);
        return new KeyboardIndividual(geneticCode);
    }

}
