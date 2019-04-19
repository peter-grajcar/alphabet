package keyboard.evolution;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class EvolutionRunner {

    public static void run() {
        KeyboardPopulation population = new KeyboardPopulation(95);

        GeneticCode code = GeneParser.parse("29,59 28,37 33,39 24,52 42,36 48,35 33,46 55,51 61,18 11,54 25,54 32,53 24,20 50,19 40,27 62,50 44,21");
        KeyboardIndividual individual = new KeyboardIndividual(code);
        population.getPopulation().add(individual);

        code = GeneParser.parse("22,50 41,39 13,7 13,7 10,1 62,29 32,54 28,38 28,38 20,51 13,7 13,7 28,38 24,43 34,45 47,39 23,55 38,36 48,35 38,36 38,36 38,36 27,58 40,51");
        individual = new KeyboardIndividual(code);
        population.getPopulation().add(individual);

        code = GeneParser.parse("54,11 28,37 24,28 46,40 33,53 40,0 27,39 60,29 35,39 2,8 48,35 17,63 12,52 29,44 51,22 11,8 24,52 46,32 24,52 47,30 22,16 13,55 24,20 36,42 32,39");
        individual = new KeyboardIndividual(code);
        population.getPopulation().add(individual);

        for(int i = 0; i < 2000; i++) {
            KeyboardIndividual a = population.getFittest(population.getPopulation());

            if(i % 100 == 0) {
                System.out.printf("\"%s\"\n", a.getKeyboard().getKeys());
                System.out.println(a.getGeneticCode());
            }
            //a.getKeyboard().print();
            System.out.printf("generation #%d: %s\n", i, a.getFitness());

            population.evolve();
        }

        KeyboardIndividual a = population.getFittest(population.getPopulation());
        System.out.printf("\"%s\"\n", a.getKeyboard().getKeys());
        System.out.println(a.getGeneticCode());
    }

}
