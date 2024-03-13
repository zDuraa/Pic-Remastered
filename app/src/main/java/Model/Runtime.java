package Model;

public class Runtime {

    private double quarzfrequenz = 4;
    private int runtime = 0;
    private Pic pic;
    private Command command;

    //Beim PIC benötigen alle, bis auf wenige Ausnahmen, vier Quarztakte für einen Befehlstakt.
    //So wird bei einem 4 MHz Quarz ein Befehl in 1 μs abgearbeitet.
    //Bei 1 MHz Quarzen benötigt der gleiche Befehl dagegen 4 μs. Man unterscheidet
    //zwischen Quarztakt und Befehlstakt. Beim PIC benötigen alle, bis auf
    //wenige Ausnahmen, vier Quarztakte für einen Befehlstakt.

    public Runtime(Pic pic) {

        this.pic = pic;

    }

    public int getRuntime(){

        return runtime;
    }

    public void inc(int zyc){
        if(zyc == 2){
            runtime += berechneAusfuehrungsZeit();
        }
        runtime += berechneAusfuehrungsZeit();
    }


    public double getQuarzfrequenz(){
        return quarzfrequenz;
    }

    public void setQuarzfrequenz(int val){
        this.quarzfrequenz = val;
    }

    //So wird bei einem 4 MHz Quarz ein Befehl in 1 μs abgearbeitet.
    private double berechneAusfuehrungsZeit() {
        double cyclesPerCommand = 4;

        runtime = (int)(cyclesPerCommand / quarzfrequenz);
        return runtime;
    }

}
