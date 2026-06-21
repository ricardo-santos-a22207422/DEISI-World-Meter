package pt.ulusofona.aed.deisiworldmeter;

public class InvalidInput {
    String nome;
    int linhasOK;
    int linhasNOK;

    public InvalidInput(String nome, int linhasOK, int linhasNOK) {
        this.nome = nome;
        this.linhasOK = linhasOK;
        this.linhasNOK = linhasNOK;
    }

    @Override
    public String toString() {
        return nome + " | " + linhasOK + " | " + linhasNOK;
    }
}
