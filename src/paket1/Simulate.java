package paket1;

import java.io.IOException;
import java.util.Scanner;

public class Simulate {
    
    public static void main(String args[]) throws IOException {
        Championship champ = new Championship();
        champ.loadFiles();
        Scanner sc = new Scanner(System.in);
        int numOfTour = 0;
        String input; 
        
        while (true) {
            System.out.print("Unesite broj turnira koji ce se igrati na sampionatu: ");
            input = sc.nextLine();
            
            try {
                numOfTour = Integer.parseInt(input);
                if (numOfTour < 4 || numOfTour > 13) 
                    System.out.println("Uneseni broj mora biti izmedu 4 i 13.");
                else 
                    break; 
            } catch (NumberFormatException e) { 
                System.out.println("Niste uneli broj. Molimo vas unesite broj.");
            }
        }
        
        for(int i = 0; i < numOfTour; i++) {
            System.out.print("Unesite sledeci turnir u formatu ime turnira,podloga,tip turnira: ");
            input = sc.nextLine();
            boolean tourExist = false;
            
            for(Tournament tmpTournament : champ.getTournaments()) {
                if(tmpTournament.getName().equals(input.split(",")[0])) {
                    tourExist = true;
                    if(tmpTournament.getPlayable()) {
                        System.out.println("Ovaj turnir je vec igran. Molimo Vas, unesite drugi.");
                        i--; 
                        break;
                    } else {
                        tmpTournament.setPlayable(true);
                        SeasonTournament st = new SeasonTournament(input, champ.getPlayers());
                        st.play();
                        champ.updateAtpRanks();
                        champ.recoverPlayers();
                    }
                } 
            }
            if(!tourExist) {
                i--;
                System.out.println("Ne postoji takav turnir. Molimo Vas, unesite drugi.");
            }
        }
    
        for(Player tmpPlayer : champ.getPlayers())    
                System.out.println(tmpPlayer.toString()); 
        
        System.out.print("\nPritisni bilo sta za pocetak ATP finala: ");
        sc.nextLine();
        sc.close();
        
        System.out.println("\n ATP final.");
        AtpFinals atp = new AtpFinals(champ.getPlayers());
        atp.play();
        System.out.println();
        
        champ.updateAtpRanks();
        for(Player tmpPlayer : champ.getPlayers())    
                System.out.println(tmpPlayer.toString()); 
        System.out.println("\nOVE GODINE JE " + champ.getPlayers().get(0).getAtpRank() + " MESTO OSVOJIO " + champ.getPlayers().get(0).getName());
        
    }

}