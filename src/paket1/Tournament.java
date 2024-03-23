package paket1;
import java.util.ArrayList;

abstract public class Tournament {

    protected String tourName;
    protected String tourType;
    protected String tourSurface;
    protected boolean playable;
    protected int numOfSets;
    protected ArrayList<Player> contestants;
            
    public Tournament (String tekst, ArrayList<Player> contestants){
        String [] tokeni = tekst.split(",");
        if(tokeni.length != 3){
            System.out.println("Greska pri ocitavanju turnira");
            System.exit(0);
        }  
        this.tourName = tokeni[0];
        this.tourSurface = tokeni[1];
        this.tourType = tokeni[2];
        this.playable = false;
        this.contestants = contestants;
        if(this.tourType.equals("Grand Slam"))
            numOfSets = 3;
        else if(this.tourType.equals("Masters1000"))
            numOfSets = 2;
    }
    
    public boolean getPlayable(){
        return playable;
    }
    
    public ArrayList<Player> getContestants(){
        return contestants;
    }
    
    public void setPlayable(boolean isPlayable){
        playable = isPlayable;
    }
    
    public void setContestants(ArrayList<Player> contestans){
        this.contestants = contestans;
    }
    
    abstract public void play();
    
}