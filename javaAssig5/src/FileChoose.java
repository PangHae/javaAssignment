import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileChoose {

    private String filePath;
    public ArrayList<Word> wordList = null;

    public FileChoose(String file) {
        this.filePath = file;
        readFile();
    }

    public void addNewWord(String word, String meaning){
        wordList.add(new Word(word, meaning));
        File file = new File(filePath);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));){
            bw.write( "\n"+ word + "\t"+ meaning);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(){
        this.wordList = new ArrayList<>();
        if(!this.filePath.contains(".txt")){
            System.out.println("영어 단어장 파일의 확장자가 올바르지 않습니다.");
        }else{
            try(Scanner sc = new Scanner(new File(filePath))){
                while(sc.hasNextLine()){
                    String line = sc.nextLine();
                    String [] lineSplit = line.split("\t");
                    if(lineSplit[0].contains("\t") || lineSplit[1].contains("\t")){
                        System.out.println("파일의 형식이 올바르지 않습니다.");
                        break;
                    }else{
                        this.wordList.add(new Word(lineSplit[0], lineSplit[1]));
                    }
                }
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
