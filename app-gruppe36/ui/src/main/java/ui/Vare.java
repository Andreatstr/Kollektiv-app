package ui;

public class Vare {
    String VareNavn;
    int AntallAvVare;

    
    public Vare(String VareNavn, int AntallAvVare) {
        this.VareNavn = VareNavn;
        this.AntallAvVare = AntallAvVare;
    }

    public String getVareNavn() {
        return VareNavn;
    }

    public void setVareNavn(String VareNavn) {
        this.VareNavn = VareNavn;
    }

    public int getAntallAvVare() {
        return AntallAvVare;
    }

    public void setAntallAvVare(int AntallAvVare) {
        this.AntallAvVare = AntallAvVare;

    }

}
