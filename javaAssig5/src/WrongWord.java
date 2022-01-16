import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WrongWord {

    public File file;
    public String filePath = "./src/file/wrongWord.txt";
    ArrayList <Word> words = new ArrayList();

    public WrongWord(){
        file = new File(filePath);
        if(file.exists()){
            try(Scanner sc = new Scanner(file)){
                while(sc.hasNextLine()){
                    String line = sc.nextLine();
                    String [] lineSplit = line.split("\t");
                    if(lineSplit[0].contains("\t") || lineSplit[1].contains("\t")){
                        System.out.println("파일의 형식이 올바르지 않습니다.");
                        break;
                    }else{
                        this.words.add(new Word(lineSplit[0], lineSplit[1]));
                    }
                }
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }else{
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));){
                bw.write("");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void add2File(ArrayList words){
        this.words.addAll(words);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));){
            for(int i = 0; i < this.words.size(); i++){
                bw.write( this.words.get(i).getWord() + "\t"+ this.words.get(i).getMeaning()+"\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
