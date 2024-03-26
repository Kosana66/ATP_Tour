package paket1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Championship {
 
    private ArrayList<Player> players;
    private ArrayList<Tournament> tournaments;
    
    public Championship() {
        players = new ArrayList<Player>();
        tournaments = new ArrayList<Tournament>();
    }
    
    public void updateAtpRanks() {
        // sortiranje po poenima
        Collections.sort(players, players.get(0));
        // izmena pozicije na listi
        for(int position = 1 ; position <= players.size(); position++)
            players.get(position-1).setAtpRank(position);  
    }
    public void recoverPlayers() {
        for(Player p : players) {
            if(p.getInjured() == true)
                p.setInjured(false);
        }
    }
    public void loadFiles() {
        String sP = System.getProperty("file.separator");
	File fajl = new File("."+ sP +"src" + sP + "paket1" + sP + "players.txt");
        String tmpLine;
	if(fajl.exists()){
            try {
                BufferedReader in = new BufferedReader(new FileReader(fajl));
                while((tmpLine = in.readLine()) != null)
                    players.add(new Player(tmpLine));
            } catch (FileNotFoundException ex) {
                System.out.println("Fajl players.txt - FileNotFoundException");
                System.exit(0);
            } catch (IOException ex) {
                System.out.println("IO izuzetak pri citanju fajla players.txt");
                System.exit(0);
            }
        } else {
            System.out.println("Ne postoji fajl player.txt!");
            System.exit(0);
	}
        fajl = new File("."+ sP +"src" + sP + "paket1" + sP + "tournaments.txt");
	if(fajl.exists()){
            try {
                BufferedReader in = new BufferedReader(new FileReader(fajl));
                while((tmpLine = in.readLine()) != null)
                    tournaments.add(new SeasonTournament(tmpLine, players));
            } catch (FileNotFoundException ex) {
                System.out.println("Fajl tournament.txt - FileNotFoundException");
                System.exit(0);
            } catch (IOException ex) {
                System.out.println("IO izuzetak pri citanju fajla tournament,");
                System.exit(0);
            }
        } else {
            System.out.println("Ne postoji fajl tournament.txt!");
            System.exit(0);
        }
    }
    
    public ArrayList<Tournament> getTournaments() {
        return tournaments;
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    /*
    public static void main(String args[]) {
        Championship champ = new Championship();
        champ.loadFiles();

        tournaments.get(0).play();
        champ.updateAtpRanks();
       
    }
*/
}