package paket1;
import java.util.ArrayList;
import java.util.Random;

public class SeasonTournament extends Tournament {

    private ArrayList<Player> roundOf16;
    private ArrayList<Player> quaterFinalists;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;
            
    public SeasonTournament(String tekst, ArrayList<Player> contestants){
        super(tekst, contestants);
        roundOf16 = contestants;
        quaterFinalists = new ArrayList<Player>();
    }
    
    @Override
    public void play(){
        super.setPlayable(true);
        Random rng = new Random();
        int randomNumber1, randomNumber2;
        int numOfContestants = 15;
        Match match;
        
        for(int i = 1; i <= 8; i++) {
            
            randomNumber1 = rng.nextInt(numOfContestants-0+1);
            do {
                randomNumber2 = rng.nextInt(numOfContestants-0+1);
            } while(randomNumber1 == randomNumber2);
            match = new Match(roundOf16.get(randomNumber1), roundOf16.get(randomNumber2), super.tourSurface, super.numOfSets);
            // konverzija plejera da moze u listu
            winner = match.playMatch();
            System.out.println(winner.toString());
            quaterFinalists.add(winner);
            roundOf16.remove(randomNumber1);
            roundOf16.remove(randomNumber2);
            if(numOfContestants > 1)
                numOfContestants -= 2;
            
            System.out.println("randomNumber1 : " + randomNumber1);
            System.out.println("randomNumber2 : " + randomNumber2);
            System.out.println("Pobednik : " );
            System.out.println("\t" + quaterFinalists.get(i-1).toString());
            System.out.println();
        }
        System.out.println("KRAJ TURNIRA");
        
    }
    
    public static void main(String args[]) {
        ArrayList<Player> contestants = new ArrayList<Player>();
        contestants.add(new Player("1,Novak Djokovic,mentality,hard,0"));
        contestants.add(new Player("2,Carlos Alcaraz,forehand,clay,0"));
        contestants.add(new Player("3,Jannik Sinner,backhand,hard,0"));
        contestants.add(new Player("4,Daniil Medvedev,serve,hard,0"));
        contestants.add(new Player("5,Andrey Rublev,forehand,hard,0"));
        contestants.add(new Player("6,Alexander Zverev,backhand,hard,0"));
        contestants.add(new Player("7,Holger Rune,serve,clay,0"));
        contestants.add(new Player("8,Hubert Hurkacz,serve,grass,0"));
        contestants.add(new Player("9,Casper Ruud,forehand,clay,0"));
        contestants.add(new Player("10,Alex de Minaur,forehand,grass,0"));
        contestants.add(new Player("11,Stefanos Tsitsipas,backhand,grass,0"));
        contestants.add(new Player("12,Taylor Fritz,serve,hard,0"));
        contestants.add(new Player("13,Grigor Dimitrov,forehand,hard,0"));
        contestants.add(new Player("14,Tommy Paul,forehand,clay,0"));
        contestants.add(new Player("15,Karen Khachanov,serve,hard,0"));
        contestants.add(new Player("16,Frances Tiafoe,serve,clay,0"));
        
        
        SeasonTournament turnir1 = new SeasonTournament("Monte-Carlo Masters,clay,Masters1000", contestants);
        turnir1.play();
    }
    
}