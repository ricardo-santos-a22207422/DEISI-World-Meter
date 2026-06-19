package pt.ulusofona.aed.deisiworldmeter;

import java.io.File;
import java.util.ArrayList;

public class Main {

    /**
     * This method will read all the files and convert them into local data
     *
     * @Musts Ignore cities that are from countries not in country file same goes for population AND Ignore duplicated countries
     * @param file File type, receives the path name for the file we want the parser to read out
     * @return TRUE if read files with no errors otherwise FALSE
     */
    //TODO: IMPLEMENT PARSER FUNCTION
    public static Boolean parseFiles(File file) {
        //PARSER FOR FILE READING INPUTED FILES
        return true;
    }

    /**
     *
     * @param tipo TipoEntidade type variable, to define what type of Objects we want listed in output
     * @return A toString() combination os all the Objects found in database of inputed Type of Entity
     */
    public static ArrayList getObjects(TipoEntidade tipo) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to DEISI World Meter");
    }
}