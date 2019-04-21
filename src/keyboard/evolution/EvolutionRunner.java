package keyboard.evolution;

import java.io.*;
import java.util.Scanner;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class EvolutionRunner {

    private static int keyboardCount;
    private static String[] keyboards;

    public static void run() {

        FileInputStream is = null;
        try {
            File file = new File("res/input");
            is = new FileInputStream(file);

            Scanner scanner = new Scanner(is);

            scanner.useDelimiter("\n");
            keyboardCount = scanner.nextInt();

            keyboards = new String[keyboardCount];
            for(int i = 0; i < keyboardCount; i++) {
                keyboards[i] = scanner.next();
                if(keyboards[i].length() != 64) {
                    System.err.println("invalid length");
                    System.exit(1);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.exit(1);
        } finally {
            try {
                is.close();
            } catch (Exception e) {}
        }



        for(int j = 0; j < 200*keyboardCount; j++) {
            if(j % keyboardCount == 0){
                System.out.println();
                for(int i = 0; i < keyboardCount; i++) {
                    System.out.println(keyboards[i]);
                }
                System.out.println();

                saveKeyboards();
            }

            KeyboardIndividual.initialKeyboard = keyboards[j % keyboardCount];

            System.out.printf("=========================Keyboard #%3d==========================\n", (j % keyboardCount) + 1);
            System.out.println(KeyboardIndividual.initialKeyboard);
            System.out.println("================================================================");

            KeyboardPopulation population = new KeyboardPopulation(100);
            int min = population.getFittest(population.getPopulation()).getFitness();

            for (int i = 0; i < 20; i++) {
                KeyboardIndividual a = population.getFittest(population.getPopulation());

                System.out.printf("Keyboard #%d generation #%d:\n\tFitness:  %d\n\tKeyboard: \"%s\"\n\tGenes:    %s\n",
                            (j % keyboardCount) + 1, i,
                            a.getFitness(), a.getKeyboard().getKeys(),
                            a.getGeneticCode()
                );

                population.evolve();
            }

            KeyboardIndividual best = population.getFittest(population.getPopulation());

            if(best.getFitness() < min)
            keyboards[j % keyboardCount] = best.getKeyboard().getKeys();
        }


        System.out.println("END:");

    }

    public static void saveKeyboards() {
        FileWriter writer = null;
        try {
            File file = new File("res/output");
            writer = new FileWriter(file);
            writer.write(Integer.toString(keyboardCount));
            writer.write("\n");
            for(int i = 0; i < keyboardCount; i++) {
                writer.write(keyboards[i]);
                writer.write("\n");
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (Exception e) {}
        }
    }

}
