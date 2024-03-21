package paket1;
import java.util.Random;

public class Match {

    private Player p1;
    private Player p2;
    private String matchSurface;
    private int winSetNum;
    private int p1Sets, p1Gems, p2Sets, p2Gems;
    private int p1ScorePerSet[], p2ScorePerSet[];
    private Random rng;
            
    public Match(Player p1, Player p2, String matchSurface, int winSetNum) {
      this.p1 = p1;
      this.p2 = p2;
      this.matchSurface = matchSurface;
      this.winSetNum = winSetNum;
    }

   
    private boolean chanceEvent(int probability) {
        Random rng = new Random();
        int randomPointChance = rng.nextInt(100-0+1);
        return (probability <= randomPointChance) ? true : false;
    }
    
    private void playGame() {
        int p1Points = 0;
        int p2Points = 0;
    
        while(p1Points < 5 && p2Points < 5) {
            if(chanceEvent(this.p1.servePointChance(this.p2, this.matchSurface)))
                p1Points++;
            else 
                p2Points++; 
            
            
            if(p1Points == 4 && p2Points < 3) {
                this.p1Gems++;
                break;
            } else if(p2Points == 4 && p1Points < 3) {
                this.p2Gems++;
                break;
            } else if(p1Points == 4 && p2Points == 4) {
                p1Points = 3;
                p2Points = 3;
            } else if(p1Points == 5){
                this.p1Gems++;
            } else if(p2Points == 5) {
                this.p2Gems++;
            }
        }
    }
        
    private void playTieBreak() {
        int p1Points = 0;
        int p2Points = 0;
        while(true) {
            if(chanceEvent(this.p1.servePointChance(this.p2, this.matchSurface)))
               p1Points++;
            else 
               p2Points++; 

            if(p1Points == 7 && p2Points < 6){
                this.p1Sets++;
                break;
            } else if(p2Points == 7 && p1Points < 6){
                this.p2Sets++;
                break;
            } else if(p1Points - p2Points == 2){
                this.p1Sets++;
                break;
            } else if(p2Points - p1Points == 2){
                this.p2Sets++;
                break;
            }
        }
    }
    
    private void playSet() {
        this.p1Gems = 0;   
        this.p2Gems = 0;
        
        while(true) {
            playGame();
            if((this.p1Gems == 6 && this.p2Gems < 5)) {
                p1Sets++;
                break;
            }
            else if((this.p2Gems == 6 && this.p1Gems < 5)) {
                p2Sets++;
                break;
            } 
            else if((this.p1Gems == 7 && this.p2Gems == 5)) {
                p1Sets++;
                break;
            }
            else if((this.p2Gems == 7 && this.p1Gems == 5)) {
                p2Sets++;
                break;
            }
            else if(this.p1Gems == 6 && this.p2Gems == 6) {
                playTieBreak();
                break;
            }
        }
    }
    
    public Player playMatch() {
        this.p1Sets = 0;
        this.p2Sets = 0;   

        while(true) {
            playSet();
            if(p1Sets == winSetNum) {
                return p1;
            }
            else if(p2Sets == winSetNum) {
                return p2;
            }
        }
    }    
        
    public void printMatchResult() {
        
    }
    
    public static void main(String args[]) {
        Player p1 = new Player("1,Novak Djokovic,mentality,hard,0");
        Player p2 = new Player("4,Rafael Nadal,servis,grass,2");
        Match turnir1 = new Match(p1, p2, "hard", 2);
        turnir1.playMatch();
    }
        
}

