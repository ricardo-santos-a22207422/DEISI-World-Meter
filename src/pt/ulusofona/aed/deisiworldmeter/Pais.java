package pt.ulusofona.aed.deisiworldmeter;

public class Pais {
    int id;
    String alfa2;
    String alfa3;
    String nome;

    public Pais(int id, String alfa2, String alfa3, String nome) {
        this.id = id;
        this.alfa2 = alfa2;
        this.alfa3 = alfa3;
        this.nome = nome;
    }


    @Override
    public String toString() {
        String commonSep = " " + "|" + " ";
        if(id < 700) {
            return nome + commonSep + id + commonSep + alfa2.toUpperCase() + commonSep + alfa3.toUpperCase();
        } else {
            //TODO Change toString so that in this case it returns also the number of population file line that give statistics about said country
            return nome + commonSep + id + commonSep + alfa2.toUpperCase() + commonSep + alfa3.toUpperCase() + commonSep;
        }
    }
}
