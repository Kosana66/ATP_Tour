package paket1;

public class Player {

    private String name;
    private String ability;
    private String preferedSurface;
    private int atpRank;
    private int atpPoints;
    private boolean injured;
    
    public Player(String tekst){
        String [] tokeni = tekst.split(",");
        if(tokeni.length!=5){
            System.out.println("Greska pri ocitavanju tenisera");
            System.exit(0);
        }

        this.atpRank = Integer.parseInt(tokeni[0]);
        this.name = tokeni[1];
        this.ability = tokeni[2];
        this.preferedSurface = tokeni[3];
        this.atpPoints = Integer.parseInt(tokeni[4]);
    }
    
//    public static void main(String args[]) {
//        
//        Player br1 = new Player("1,Novak Djokovic,mentality,hard,0");
//        
//
//        br1.setName("Mile Milovic");
//        br1.setAbility("psiha");
//        br1.setPreferedSurface("sljaka");
//        br1.setAtpRank(2);
//        br1.setAtpPoints(13);
//        br1.setInjured(true);
//        System.out.println(br1.toString());
//        
//    }
    
      
        
        
    
    public int servePointChance(Player opponent, String surface) {
        int verovatnoca = 50;
        verovatnoca += (this.preferedSurface.equals(surface)) ? 5 : 0;
        verovatnoca -= this.atpRank-opponent.atpRank;
        
        verovatnoca -= (opponent.ability.equals("backhand")) ? 8 : 0;
        verovatnoca += (this.ability.equals("forehand")) ? 10 : 0;
        
        verovatnoca += (this.ability.equals("servis")) ? 15 : 0;
        verovatnoca += (opponent.ability.equals("servis")) ? 5 : 0;
        
        verovatnoca += (this.ability.equals("mentalitet")) ? 5 : 0;
        verovatnoca -= (opponent.ability.equals("mentalitet")) ? 10 : 0;
            
        return verovatnoca;
    }
    
    @Override
    public String toString() {
        String tmpString = this.name + " ima sposobnost " + this.ability + ", voli da igra na " + this.preferedSurface + 
                ". Na listi je " + this.atpRank + ".mesto sa " + this.atpPoints + " poena. ";
        tmpString += this.injured ? "Povredjen je." : "Nije povredjen.";
        return tmpString;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getAbility() {
        return this.ability;
    }
    
    public String getPreferedSurface() {
        return this.preferedSurface;
    }
    
    public int getAtpRank() {
        return this.atpRank;
    }
    
    public int getAtpPoints() {
        return this.atpPoints;
    }
    
    public boolean getInjured() {
        return this.injured;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAbility(String ability) {
        this.ability = ability;
    }
    
    public void setPreferedSurface(String preferedSurface) {
        this.preferedSurface = preferedSurface;
    }
    
    public void setAtpRank(int atpRank) {
        this.atpRank = atpRank;
    }
    
    public void setAtpPoints(int atpPoints) {
        this.atpPoints = atpPoints;
    }
    
    public void setInjured(boolean injured) {
        this.injured = injured;
    }
    
}
