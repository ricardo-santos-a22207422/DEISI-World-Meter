package pt.ulusofona.aed.deisiworldmeter;

public class Cidade {
    String alfa2;
    String cidade;
    int regiao;
    float populacao;
    float latitude;
    float longitude;


    public Cidade(float longitude, float latitude, float populacao, int regiao, String cidade, String alfa2) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.populacao = populacao;
        this.regiao = regiao;
        this.cidade = cidade;
        this.alfa2 = alfa2;
    }

    //If city is from a country that doesn't exist in city file then is to be ignored on getObjects()
    @Override
    public String toString() {
        String commonSep = " " + "|" + " ";
        return cidade + commonSep + alfa2.toUpperCase() + commonSep + regiao + commonSep + (int)populacao + commonSep + latitude + "," + longitude;
    }
}
