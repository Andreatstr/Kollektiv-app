package ui;

public class Vare {
    private String vareNavn; // Use camelCase
    private int antallAvVare;

    public Vare(String vareNavn, int antallAvVare) {
        this.vareNavn = vareNavn;
        this.antallAvVare = antallAvVare;
    }

    public String getVareNavn() {
        return vareNavn;
    }

    public void setVareNavn(String vareNavn) {
        this.vareNavn = vareNavn;
    }

    public int getAntallAvVare() {
        return antallAvVare;
    }

    public void setAntallAvVare(int antallAvVare) {
        this.antallAvVare = antallAvVare;
    }
}
