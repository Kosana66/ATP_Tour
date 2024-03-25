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
      if(winSetNum == 2) {
          p1ScorePerSet = new int[3]; 
          p2ScorePerSet = new int[3]; 
      } else if (winSetNum == 3) {
          p1ScorePerSet = new int[5]; 
          p2ScorePerSet = new int[5]; 
      }
    }

   
    private boolean chanceEvent(int probability) {
        Random rng = new Random();
        int randomPointChance = rng.nextInt(100-0+1);
        //System.out.println("Random verovatnoca: " + randomPointChance);
        //System.out.println("Verovatnoca tenisera: " + probability);
        return (randomPointChance <= probability) ? true : false;
    }
    
    private void playGame() {
        int p1Points = 0;
        int p2Points = 0;
        /*if(((p1Gems + p2Gems) % 2) == 0) {
            System.out.println("Prvi servira");
        } else {
            System.out.println("Drugi servira");
        }*/
        
    
        while(p1Points < 5 && p2Points < 5) {
            if(((p1Gems + p2Gems) % 2) == 0) {
                if(chanceEvent(p1.servePointChance(p2, matchSurface)))
                    p1Points++;
                else 
                    p2Points++; 
            } else {
                if(chanceEvent(p2.servePointChance(p1, matchSurface)))
                    p1Points++;
                else 
                    p2Points++; 
            }
            
            if(p1Points == 4 && p2Points < 3) {
                p1Gems++;
                break;
            } else if(p2Points == 4 && p1Points < 3) {
                p2Gems++;
                break;
            } else if(p1Points == 4 && p2Points == 4) {
                p1Points = 3;
                p2Points = 3;
            } else if(p1Points == 5){
                p1Gems++;
            } else if(p2Points == 5) {
                p2Gems++;
            }
        }
    }
        
    private void playTieBreak() {
        int p1Points = 0;
        int p2Points = 0;
        //System.out.println("TIE_BREAK");
        
        while(true) {
            if((p1Points + p2Points) % 2 == 0) {
                //System.out.println("Prvi servira");
                if(chanceEvent(p1.servePointChance(p2, matchSurface)))
                   p1Points++;
                else 
                   p2Points++; 
            } else {
                //System.out.println("Drugi servira");
                if(chanceEvent(p2.servePointChance(p1, matchSurface)))
                   p1Points++;
                else 
                   p2Points++; 
            }

            if(p1Points == 7 && p2Points < 6){
                p1Gems++;
                p1Sets++;
                break;
            } else if(p2Points == 7 && p1Points < 6){
                p2Gems++;
                p2Sets++;
                break;
            } else if(p1Points >= 6 && p2Points >= 6 && p1Points - p2Points == 2){
                p1Gems++;
                p1Sets++;
                break;
            } else if(p1Points >= 6 && p2Points >= 6 && p2Points - p1Points == 2){
                p2Gems++;
                p2Sets++;
                break;
            }
        }
        //System.out.println("Poeni u tie break: ");
        //System.out.println("P1: " + p1Points);
        //System.out.println("P2: " + p2Points);
    }
    
    private void playSet() {
        p1Gems = 0;   
        p2Gems = 0;
        
        while(true) {
            playGame();
            if((p1Gems == 6 && p2Gems < 5)) {
                p1Sets++;
                break;
            }
            else if((p2Gems == 6 && p1Gems < 5)) {
                p2Sets++;
                break;
            } 
            else if((p1Gems == 7 && p2Gems == 5)) {
                p1Sets++;
                break;
            }
            else if((p2Gems == 7 && p1Gems == 5)) {
                p2Sets++;
                break;
            }
            else if(p1Gems == 6 && p2Gems == 6) {
                playTieBreak();
                break;
            }
        }
    }
    
    public Player playMatch() {
        p1Sets = 0;
        p2Sets = 0;   
        int cnt = 0;
        Random rndNum = new Random();
        
        if(rndNum.nextInt(100-0+1) == 1) {
            if(rndNum.nextInt(1-0+1)+1 == 1) {
                p1.setInjured(true);
                System.out.println("Teniser " + p1.getName() + " se povredio. ");
                return p2;
            } else {
                p2.setInjured(true);
                System.out.println("Teniser " + p2.getName() + " se povredio. ");
                return p1;
            }
        } else {
            while(true) {
                playSet();
                p1ScorePerSet[cnt] = p1Gems;
                p2ScorePerSet[cnt] = p2Gems;
                cnt++;
                if(p1Sets == winSetNum) {
                    return p1;
                }
                else if(p2Sets == winSetNum) {
                    return p2;
                }
            }
        }
    }    
    
    public void printMatchResult() {
        System.out.printf("%-20s", p1.getName());
        for(int i = 0 ; i < p1ScorePerSet.length; i++){
            if(p1ScorePerSet[i] != 0 || p2ScorePerSet[i] != 0)
                System.out.printf("%d ", p1ScorePerSet[i]);
        }
        System.out.println(" " + p1Sets);
        
        System.out.printf("%-20s", p2.getName());
        for(int i = 0 ; i < p2ScorePerSet.length; i++){
            if(p1ScorePerSet[i] != 0 || p2ScorePerSet[i] != 0)
                System.out.printf("%d ", p2ScorePerSet[i]);
        }
        System.out.println(" " + p2Sets);
        System.out.println();
    }
    
   /* public static void main(String args[]) {
        Player p1 = new Player("1,Novak Djokovic,mentality,hard,0");
        Player p2 = new Player("3,Jannik Sinner,backhand,hard,0");
        Match turnir1 = new Match(p1, p2, "hard", 3);
        Player p = turnir1.playMatch();
        turnir1.printMatchResult();
        Match turnir2 = new Match(p2, p1, "hard", 3);
        Player pp = turnir2.playMatch();
        turnir2.printMatchResult();
    }*/
        
}

