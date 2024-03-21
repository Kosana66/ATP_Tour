package paket1;
import java.util.ArrayList;

public class Tournament {

    protected String tourName;
    protected String tourType;
    protected String tourSurface;
    protected boolean playable;
    protected int numOfSets;
    protected ArrayList<Player> contestants;
            
    public Tournament (){
        
    }
    
    abstract public void play();
    
    public static void main(String args[]) {
     
    }
    
}