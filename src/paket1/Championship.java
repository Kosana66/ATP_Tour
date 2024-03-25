package paket1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Championship {

    private ArrayList<Player> players;
    private ArrayList<Tournament> tournaments;
    
    public Championship() {
        
    }
    
    public void updateAtpRanks() {
        
    }
    public void recoverPlayers() {
        for(Player p : players) {
            if(p.getInjured() == true)
                p.setInjured(false);
        }
    }
    public void loadFiles() {
	File fajl = new File("."+"players.txt");
        String tmpLine;
	if(fajl.exists()){
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(fajl));
            } catch (FileNotFoundException ex) {
                System.out.println("Fajl player.txt ne postoji na datoj lokaciji.");
                System.exit(0);
            }
            try {
                while((tmpLine = in.readLine()) != null)
                    players.add(new Player(tmpLine));
            } catch (IOException ex) {
                Logger.getLogger(Championship.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        fajl = new File("."+"tournaments.txt");
	if(fajl.exists()){
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(fajl));
            } catch (FileNotFoundException ex) {
                System.out.println("Fajl tournament.txt ne postoji na datoj lokaciji.");
                System.exit(0);
            }
            try {
                while((tmpLine = in.readLine()) != null)
                    tournaments.add(new SeasonTournament(tmpLine, players));
            } catch (IOException ex) {
                Logger.getLogger(Championship.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void main(String args[]) {
       
    }
}