package engine.process.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameRepository {
    private ArrayList<String> names = new ArrayList<>();
    private static NameRepository instance;
    private int nameIndex = 0;

    private NameRepository() {
        try(BufferedReader br = new BufferedReader(new FileReader("src/data/noms.txt"))){
            String ligne;
            while((ligne = br.readLine()) != null){
                names.add(ligne.trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static NameRepository getInstance() {
        if(instance == null) {
            instance = new NameRepository();
        }
        return instance;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }
}
