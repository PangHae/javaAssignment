import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileRead {

    private ArrayList<Word> wordArrayList = new ArrayList<>();

    public void readFile(){
        String fileRoute = "./src/quiz.txt";
        try{
            File file  = new File(fileRoute);
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String [] splitLine = line.split("\t");
                Word word = new Word(splitLine[0], splitLine[1]);
                this.wordArrayList.add(word);
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Word> getWordArrayList() {
        return this.wordArrayList;
    }
}
