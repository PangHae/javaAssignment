import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        FileRead fr = new FileRead();
        fr.readFile();
        ArrayList<Word> wordArrayList = fr.getWordArrayList();
        Menu menu = new Menu(wordArrayList);
        int menuNum;
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("========== 이광해의 단어장 ==========");
            System.out.println("1) 단어 검색    2) 객관식 퀴즈   3) 종료");
            System.out.print("메뉴를 선택하세요 : ");

            try{
                menuNum = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("올바르지 않은 입력입니다. 다시 입력해주세요.");
                System.out.println();
                continue;
            }

            if(menuNum == 1){
                System.out.println("========== 단어 검색 ==========");
                System.out.print("검색할 단어를 입력하세요 : ");
                sc.nextLine();
                String word = sc.nextLine();
                String getMean = menu.getMeaning(word);
                if(getMean == null){
                    System.out.println("단어장에 등록된 단어가 아닙니다.");
                }else{
                    System.out.println("단어의 뜻 : " + getMean);
                }
                System.out.println("=================================");
            }else if(menuNum == 2){
                menu.quiz();
            }else{
                break;
            }
        }
    }
}
