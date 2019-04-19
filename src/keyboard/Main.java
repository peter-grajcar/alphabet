package keyboard;

import keyboard.evolution.EvolutionRunner;
import keyboard.evolution.GeneParser;
import keyboard.evolution.GeneticCode;
import keyboard.evolution.KeyboardIndividual;

public class Main {

    public static void main(String[] args) {
        //KeyboardTest.run();
        EvolutionRunner.run();
        //Analysis.run();

        /*GeneticCode code = GeneParser.parse("22,50 41,39 13,7 13,7 10,1 62,29 32,54 28,38 28,38 20,51 13,7 13,7 28,38 24,43 34,45 47,39 23,55 38,36 48,35 38,36 38,36 38,36 27,58 40,51");
        KeyboardIndividual individual = new KeyboardIndividual(code);
        System.out.println(individual.getFitness());*/
    }
}
