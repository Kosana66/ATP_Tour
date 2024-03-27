package paket1;
import java.util.ArrayList;
import java.util.Collections;

public class AtpFinals extends Tournament{

    private ArrayList<Player> groupA;
    private ArrayList<Player> groupB;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;
    
    public AtpFinals(ArrayList<Player> contestants){
        super("ATP finals,concrete, ", contestants);
        groupA = new ArrayList<Player>();
        groupB = new ArrayList<Player>();
        semiFinalists = new ArrayList<Player>();
        finalists = new ArrayList<Player>();
        for(int i = 0; i < 8; i++) {
            if(contestants.get(i).getAtpRank() % 2 == 0)
                groupB.add(contestants.get(i));
            else
                groupA.add(contestants.get(i));
        }
    }
    
    public void updateAtpRanks(ArrayList<Player> collection) {
        // sortiranje po poenima
        Collections.sort(collection, collection.get(0));
        // izmena pozicije na listi
        for(int position = 1 ; position <= collection.size(); position++)
            collection.get(position-1).setAtpRank(position);   
    }
    
    @Override
    public void play() {
        Match match;
        Player winner;
        
        System.out.println("\n========== Group A ==============");
        for(int i = 0; i < 4; i++) 
            System.out.println(groupA.get(i).getName()); 
        
        System.out.println("\n========== Group B ==============");
        for(int i = 0; i < 4; i++) 
            System.out.println(groupB.get(i).getName());
        
        // Quarterfinals
        for(int i = 0; i < 3; i++) {
            for(int j = i+1; j < 4; j++) {
                match = new Match(groupA.get(i), groupA.get(j), super.tourSurface, super.numOfSets);
                winner = match.playMatch();
                winner.setAtpPoints(winner.getAtpPoints() + 200);
                updateAtpRanks(groupA);
            }
        }
        semiFinalists.add(groupA.get(0));
        semiFinalists.add(groupA.get(1));
        
        for(int i = 0; i < 3; i++) {
            for(int j = i+1; j < 4; j++) {
                match = new Match(groupB.get(i), groupB.get(j), super.tourSurface, super.numOfSets);
                winner = match.playMatch();
                winner.setAtpPoints(winner.getAtpPoints() + 200);
                updateAtpRanks(groupB);
            }
        }
        semiFinalists.add(groupB.get(0));
        semiFinalists.add(groupB.get(1));
        
        System.out.println("\n========== Semifinalists ==============");
        for(int i = 0; i < 4; i++) 
            System.out.println(semiFinalists.get(i).getName());
        
        // Semifinals
        match = new Match(semiFinalists.get(0), semiFinalists.get(1), super.tourSurface, super.numOfSets);
        winner = match.playMatch();
        winner.setAtpPoints(winner.getAtpPoints() + 400);
        finalists.add(winner);
        match = new Match(semiFinalists.get(2), semiFinalists.get(3), super.tourSurface, super.numOfSets);
        winner = match.playMatch();
        winner.setAtpPoints(winner.getAtpPoints() + 400);
        finalists.add(winner);
        
        System.out.println("\n========== Finalists ==============");
        for(int i = 0; i < 2; i++) 
            System.out.println(finalists.get(i).getName());
        
        // Final
        match = new Match(finalists.get(0), finalists.get(1), super.tourSurface, super.numOfSets);
        winner = match.playMatch();
        winner.setAtpPoints(winner.getAtpPoints() + 500);
        
        System.out.println("\n========== Winner ==============");
            System.out.println(winner.getName());
          
    }
}
