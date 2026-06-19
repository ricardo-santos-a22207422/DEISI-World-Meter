package pt.ulusofona.aed.deisiworldmeter;

public class Populacao {
    int id;
    int ano;
    int popMasc;
    int popFem;
    float densidade;


    public Populacao(int id, int ano, int popMasc, int popFem, float densidade) {
        this.id = id;
        this.ano = ano;
        this.popMasc = popMasc;
        this.popFem = popFem;
        this.densidade = densidade;
    }
}
