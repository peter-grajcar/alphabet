package keyboard.evolution;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class EvolutionRunner {

    public static void run() {
        KeyboardPopulation population = new KeyboardPopulation(100);

        for(int i = 0; i < 30; i++) {
            /*for(KeyboardIndividual a : population.getPopulation()) {
                System.out.println(a.getFitness());
            }
            System.out.println();*/

            KeyboardIndividual a = population.getFittest(population.getPopulation());
            //a.getKeyboard().print();
            System.out.println(a.getFitness());

            population.evolve();
        }

        KeyboardIndividual a = population.getFittest(population.getPopulation());
        System.out.printf("\"%s\"\n", a.getKeyboard().getKeys());
        System.out.println(a.getGeneticCode());
    }

}
