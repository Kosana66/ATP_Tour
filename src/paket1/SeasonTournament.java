package paket1;
import java.util.ArrayList;
import java.util.Collections;

public class SeasonTournament extends Tournament {

    private ArrayList<Player> roundOf16;
    private ArrayList<Player> quaterFinalists;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;
    
    public SeasonTournament(String tekst, ArrayList<Player> contestants){
        super(tekst, contestants);
        roundOf16 = contestants;
        quaterFinalists = new ArrayList<Player>();
        semiFinalists = new ArrayList<Player>();
        finalists = new ArrayList<Player>();
    }
    
    @Override
    public void play(){
        super.setPlayable(true);
        Match match;
        Player winner;
        
        System.out.println("========== Round of 16 ============== \n");
        
        Collections.shuffle(roundOf16);
        for(int i = 0; i <= 15; i+=2) {
            
            match = new Match(roundOf16.get(i), roundOf16.get(i+1), super.tourSurface, super.numOfSets);
            quaterFinalists.add(match.playMatch());
            match.printMatchResult();
            if (quaterFinalists.get(i/2) == roundOf16.get(i)) 
                roundOf16.get(i+1).setAtpPoints(roundOf16.get(i+1).getAtpPoints() + (super.tourSurface.equals("Masters1000") ? 100 : 180) );
            else 
                roundOf16.get(i).setAtpPoints(roundOf16.get(i).getAtpPoints() + (super.tourSurface.equals("Masters1000") ? 100 : 180) ); 
        }
        
        System.out.println("========== Quarterfinals ============== \n");
        Collections.shuffle(quaterFinalists);
        for(int i = 0; i <= 7; i+=2) {
            
            match = new Match(quaterFinalists.get(i), quaterFinalists.get(i+1), super.tourSurface, super.numOfSets);
            semiFinalists.add(match.playMatch());
            match.printMatchResult();
            if (semiFinalists.get(i/2) == quaterFinalists.get(i)) 
                quaterFinalists.get(i+1).setAtpPoints(quaterFinalists.get(i+1).getAtpPoints() + (super.tourSurface.equals("Masters1000") ? 200 : 360) );
            else 
                quaterFinalists.get(i).setAtpPoints(quaterFinalists.get(i).getAtpPoints() + (super.tourSurface.equals("Masters1000") ? 200 : 360) );
        }
        
        System.out.println("========== Semifinals ============== \n");
        Collections.shuffle(semiFinalists);
        for(int i = 0; i <= 3; i+=2) {
            
            match = new Match(semiFinalists.get(i), semiFinalists.get(i+1), super.tourSurface, super.numOfSets);
            finalists.add(match.playMatch());
            match.printMatchResult();
            if (finalists.get(i/2) == semiFinalists.get(i)) 
                semiFinalists.get(i+1).setAtpPoints(semiFinalists.get(i+1).getAtpPoints() + (super.tourSurface.equals("Masters1000") ? 400 : 720) );
            else 
                semiFinalists.get(i).setAtpPoints(semiFinalists.get(i).getAtpPoints() + (super.tourSurface.equals("Masters1000") ? 400 : 720) ); 
        }
        
        System.out.println("========== Final ============== \n");
        match = new Match(finalists.get(0), finalists.get(1), super.tourSurface, super.numOfSets);
        winner = match.playMatch();    
        match.printMatchResult();
        if (winner == finalists.get(0)) {
            finalists.get(1).setAtpPoints(finalists.get(1).getAtpPoints() + (super.tourSurface.equals("Masters1000") ? 650 : 1200) );
            finalists.get(0).setAtpPoints(finalists.get(0).getAtpPoints() + (super.tourSurface.equals("Masters1000") ? 1000 : 2000) );
        } else {
            finalists.get(0).setAtpPoints(finalists.get(0).getAtpPoints() + (super.tourSurface.equals("Masters1000") ? 650 : 1200) );
            finalists.get(1).setAtpPoints(finalists.get(1).getAtpPoints() + (super.tourSurface.equals("Masters1000") ? 1000 : 2000) );
        } 
        
        
        
    }
}