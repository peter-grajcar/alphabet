package keyboard;

import keyboard.exception.MissingKeyException;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class KeyboardPopulation {

    // Value from 0-255
    public static final int MUTATION_RATE = 16;

    private List<Individual> population;
    private String text;
    private Random random;

    public class Individual {
        Keyboard keyboard;
        private int fitness = 0;

        public Individual(Keyboard keyboard){
            this.keyboard = keyboard;
            setFitness();
        }

        public void setFitness(){
            try {
                this.fitness = keyboard.minPath(text);
            } catch (MissingKeyException e){
                this.fitness = 0;
            }
        }

        public int getFitness(){
            if(this.fitness == -1)
                setFitness();
            return this.fitness;
        }

        public Keyboard getKeyboard() {
            return keyboard;
        }

        public void setKeyboard(Keyboard keyboard) {
            this.keyboard = keyboard;
        }
    }

    public KeyboardPopulation(int size, String text) {
        this.text = text;
        population = new ArrayList<>();
        random = new Random();

        for(int i = 0; i < size; i++) {
            Individual a = newIndividual();
            population.add(a);
        }
    }

    public void evolve() {
        List<Individual> newPopulation = new ArrayList<>();

        newPopulation.add(getFittest(population));
        for(int i = 1; i < population.size(); i++) {
            Individual a = competition(5);
            mutate(a);
            newPopulation.add(a);
        }

        this.population = newPopulation;
    }

    public void mutate(Individual a){
        StringBuilder newKeys = new StringBuilder(a.getKeyboard().getKeys());

        boolean changed = false;
        for(int i = 0; i < a.getKeyboard().size(); i++) {
            if(random.nextInt(MUTATION_RATE) == 0) {
                if(random.nextBoolean()) {
                    // Transposition
                    int x = random.nextInt(a.getKeyboard().size());
                    int y = random.nextInt(a.getKeyboard().size());
                    newKeys.setCharAt(y, a.getKeyboard().getKeys().charAt(x));
                    newKeys.setCharAt(x, a.getKeyboard().getKeys().charAt(y));
                } else {
                    char c = Alphabet.intToChar(random.nextInt(Alphabet.CHAR_COUNT - 1) + 1);
                    newKeys.setCharAt(i, c);
                }
                changed = true;
            }
        }

        if(changed)
            a.setFitness();

        a.getKeyboard().setKeys(newKeys.toString());
    }

    public Individual crossover(Individual a, Individual b) {
        StringBuilder newKeys = new StringBuilder();

        for(int i = 0; i < a.getKeyboard().size(); i++){
            if(random.nextBoolean())
                newKeys.append(a.getKeyboard().get(i));
            else
                newKeys.append(b.getKeyboard().get(i));
        }

        Keyboard keyboard = new Keyboard(a.getKeyboard().getWidth(), newKeys.toString());
        return new Individual(keyboard);
    }

    public Individual newIndividual() {
        StringBuilder newKeys = new StringBuilder();
        //Create List of all chars
        List<Character> chars = new ArrayList<>();
        for(int i = 1; i < Alphabet.CHAR_COUNT; i++){
            chars.add(Alphabet.intToChar(i));
        }

        for(int i = 1; i < Alphabet.CHAR_COUNT; i++){
            int j = random.nextInt(chars.size());
            newKeys.append(chars.get(j));
            chars.remove(j);
        }
        for(int i = Alphabet.CHAR_COUNT; i <= 64; i++) {
            char c = Alphabet.intToChar(random.nextInt(200) + 1);
            newKeys.append(c);
        }
        //Transpose
        for(int i = 1; i < 32; i++){
            int x = random.nextInt(newKeys.length());
            int y = random.nextInt(newKeys.length());
            char c = newKeys.charAt(x);
            newKeys.setCharAt(x, newKeys.charAt(y));
            newKeys.setCharAt(y, c);
        }

        Keyboard keyboard = new Keyboard(8, newKeys.toString());
        return new Individual(keyboard);
    }

    public Individual getFittest(List<Individual> subpopulation) {
        Individual min = null;
        for(int i = 0; i < subpopulation.size(); i++) {
            Individual a = subpopulation.get(i);
            if(min == null || (a.getFitness() > 0 && min.getFitness() > a.getFitness()))
                min = a;
        }
        return min;
    }

    public Individual competition(int groupSize) {
        List<Individual> group = new ArrayList<>();

        while(group.size() < groupSize){
            int i = random.nextInt(population.size());
            Individual a = population.get(i);
            if(!group.contains(a))
                group.add(a);
        }

        Individual parentA = getFittest(group);
        group.remove(parentA);
        Individual parentB = getFittest(group);
        Individual child = crossover(parentA, parentB);

        return child;
    }


    public List<Individual> getPopulation() {
        return population;
    }
}

