package keyboard.evolution;

import java.security.Key;
import java.util.Scanner;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class EvolutionRunner {

    public static void run() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Count: ");
        scanner.useDelimiter("\n");
        int keyboardCount = scanner.nextInt();

        String[] keyboards = new String[keyboardCount];
        for(int i = 0; i < keyboardCount; i++) {
            keyboards[i] = scanner.next();
            if(keyboards[i].length() != 64)
                System.exit(1);
        }

        for(int j = 0; j < 10*keyboardCount; j++) {
            if(j % keyboardCount == 0){
                System.out.println();
                for(int i = 0; i < keyboardCount; i++) {
                    System.out.printf("Keyboard #%d: %s\n", i+1, keyboards[i]);
                }
                System.out.println();
            }

            KeyboardIndividual.initialKeyboard = keyboards[j % keyboardCount];

            System.out.printf("=========================Keyboard #%3d==========================\n", (j % keyboardCount) + 1);
            System.out.println(KeyboardIndividual.initialKeyboard);
            System.out.println("================================================================");

            KeyboardPopulation population = new KeyboardPopulation(100);
            int min = population.getFittest(population.getPopulation()).getFitness();

            for (int i = 0; i < 40; i++) {
                KeyboardIndividual a = population.getFittest(population.getPopulation());

                System.out.printf("Keyboard #%d generation #%d:\n\tFitness:  %d\n\tKeyboard: \"%s\"\n\tGenes:    %s\n",
                            (j % keyboardCount) + 1, i,
                            a.getFitness(), a.getKeyboard().getKeys(),
                            a.getGeneticCode()
                );

                population.evolve();
            }

            KeyboardIndividual best = population.getFittest(population.getPopulation());
            System.out.printf("\n########################FITNESS: %5d##############################\n",best.getFitness());
            System.out.printf("# %s #\n", best.getKeyboard().getKeys());
            System.out.println("####################################################################\n");

            if(best.getFitness() < min)
            keyboards[j % keyboardCount] = best.getKeyboard().getKeys();
        }


        System.out.println("END:");
        for(int i = 0; i < keyboardCount; i++) {
            System.out.printf("Keyboard #%d: %s\n", i+1, keyboards[i]);
        }
        System.out.println();

    }

}
