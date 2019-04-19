package keyboard.evolution;

import keyboard.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * created: 2019-04-19
 *
 * @author Peter Grajcar
 */
public class KeyboardPopulation {

    private List<KeyboardIndividual> population;


    public KeyboardPopulation(int size) {
        population = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            GeneticCode geneticCode = new GeneticCode(RandomUtil.getRandom().nextInt(59) + 5);
            KeyboardIndividual a = new KeyboardIndividual(geneticCode);
            population.add(a);
        }
    }

    public void evolve() {
        List<KeyboardIndividual> newPopulation = new ArrayList<>();

        KeyboardIndividual fittest = getFittest(population);
        newPopulation.add(fittest);

        for(int i = 1; i < 3*population.size()/4; i++) {
            KeyboardIndividual a = competition(10);
            a.mutate();
            newPopulation.add(a);
        }
        for(int i = 3*population.size()/4; i < population.size(); i++) {
            GeneticCode geneticCode = new GeneticCode(RandomUtil.getRandom().nextInt(59) + 5);
            KeyboardIndividual a = new KeyboardIndividual(geneticCode);
            newPopulation.add(a);
        }

        this.population = newPopulation;
    }

    public KeyboardIndividual getFittest(List<KeyboardIndividual> subpopulation) {
        KeyboardIndividual min = null;
        for(int i = 0; i < subpopulation.size(); i++) {
            KeyboardIndividual a = subpopulation.get(i);
            if(min == null || (a.getFitness() > 0 && min.getFitness() > a.getFitness()))
                min = a;
        }
        return min;
    }

    public KeyboardIndividual competition(int groupSize) {
        List<KeyboardIndividual> group = new ArrayList<>();

        while(group.size() < groupSize){
            int i = RandomUtil.getRandom().nextInt(population.size());
            KeyboardIndividual a = population.get(i);
            if(!group.contains(a))
                group.add(a);
        }

        KeyboardIndividual parentA = getFittest(group);
        group.remove(parentA);
        KeyboardIndividual parentB = getFittest(group);
        KeyboardIndividual child = parentA.crossover(parentB);

        return child;
    }


    public List<KeyboardIndividual> getPopulation() {
        return population;
    }
}

