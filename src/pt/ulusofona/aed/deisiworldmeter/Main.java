package pt.ulusofona.aed.deisiworldmeter;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    // Storage for data
    private static HashMap<Integer, Pais> paises = new HashMap<>();
    private static HashMap<String, Integer> paisesAlfa2Map = new HashMap<>();
    private static ArrayList<Cidade> cidades = new ArrayList<>();
    private static ArrayList<Populacao> populacoes = new ArrayList<>();
    private static ArrayList<InvalidInput> invalidInputs = new ArrayList<>();



    /**
     * This method will read all the files and convert them into local data
     *
     * @Musts Ignore cities that are from countries not in country file same goes for population AND Ignore duplicated countries
     * @param file File type, receives the path name for the file we want the parser to read out
     * @return TRUE if read files with no errors otherwise FALSE
     */
    public static Boolean parseFiles(File file) {
        try {
            if (!file.isDirectory()) {
                return false;
            }

            // Read countries file first
            File paisesFile = new File(file, "paises.csv");
            if (!paisesFile.exists()) {
                return false;
            }
            if (!readPaisesFile(paisesFile)) {
                return false;
            }

            // Read cities file
            File cidadesFile = new File(file, "cidades.csv");
            if (!cidadesFile.exists()) {
                return false;
            }
            if (!readCidadesFile(cidadesFile)) {
                return false;
            }

            // Read population file
            File populacaoFile = new File(file, "populacao.csv");
            if (!populacaoFile.exists()) {
                return false;
            }
            if (!readPopulacaoFile(populacaoFile)) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Reads the paises.csv file and populates the paises HashMap
     */
    private static Boolean readPaisesFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip header
                    continue;
                }

                //Would add error count on these lines!!!
                String[] fields = line.split(",");
                if (fields.length < 4) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(fields[0].trim());
                    String alfa2 = fields[1].trim();
                    String alfa3 = fields[2].trim();
                    String nome = fields[3].trim();

                    // Ignore duplicated countries
                    if (!paises.containsKey(id)) {
                        paises.put(id, new Pais(id, alfa2, alfa3, nome));
                        paisesAlfa2Map.put(alfa2.toLowerCase(), id);
                    }
                } catch (NumberFormatException e) {
                    // Skip lines with invalid format
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Reads the cidades.csv file and populates the cidades ArrayList
     * Ignores cities from countries not in the paises file
     */
    private static Boolean readCidadesFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip header
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length < 6) {
                    continue;
                }

                try {
                    String alfa2 = fields[0].trim();
                    String cidade = fields[1].trim();
                    int regiao = Integer.parseInt(fields[2].trim());
                    float populacao = Float.parseFloat(fields[3].trim());
                    float latitude = Float.parseFloat(fields[4].trim());
                    float longitude = Float.parseFloat(fields[5].trim());

                    // Check if country exists in paises file
                    Integer paisID = paisesAlfa2Map.get(alfa2.toLowerCase());
                    if(paisID != null && paises.containsKey(paisID)) {
                        cidades.add(new Cidade(longitude, latitude, populacao, regiao, cidade, alfa2));
                    }
                } catch (NumberFormatException e) {
                    // Skip lines with invalid format
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Reads the populacao.csv file and populates the populacoes ArrayList
     * Ignores population data for countries not in the paises file
     */
    private static Boolean readPopulacaoFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip header
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length < 5) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(fields[0].trim());
                    int ano = Integer.parseInt(fields[1].trim());
                    int popMasc = Integer.parseInt(fields[2].trim());
                    int popFem = Integer.parseInt(fields[3].trim());
                    float densidade = Float.parseFloat(fields[4].trim());

                    // Check if country exists in paises file
                    if (paises.containsKey(id)) {
                        populacoes.add(new Populacao(id, ano, popMasc, popFem, densidade));
                    }
                } catch (NumberFormatException e) {
                    // Skip lines with invalid format
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     *
     * @param tipo TipoEntidade type variable, to define what type of Objects we want listed in output
     * @return A toString() combination of all the Objects found in database of inputed Type of Entity
     */
    public static ArrayList getObjects(TipoEntidade tipo) {
        return switch (tipo) {
            case PAIS -> new ArrayList<>(paises.values());
            case CIDADE -> cidades;
            //For this case it must return the invalid inputs found in the following format: nome | linhas OK | linhas NOK | primeira linha NOK
            case INPUT_INVALIDO -> invalidInputs;
        };

    }

    public static void main(String[] args) {
        System.out.println("Welcome to DEISI World Meter");
        System.out.println("Testing the country file parser:");

        File file = new File("Test Files/paises.csv");
        if(readPaisesFile(file)) {
            for(Pais pais : paises.values()) {
                System.out.println(pais.toString());
            }
        } else {
            System.out.println("Error reading files");
        }
    }
}