import java.util.*;

public class Menu {
    private ArrayList <Word> wordArrayList;

    public Menu(ArrayList<Word> wordArrayList){
        this.wordArrayList = wordArrayList;
    }

    public String getMeaning(String meaning){
        String mean = null;

        for(int i = 0;  i < this.wordArrayList.size(); i++){
            if(wordArrayList.get(i).getWord().equals(meaning)){
                 mean = wordArrayList.get(i).getMeaning();
            }
        }
        return mean;
    }

    public void quiz(){
        int count = 0;
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        long start = System.currentTimeMillis();
        Word [] meanList = new Word[4];

        for(int i = 0; i < 10; i++){
            int num;
            int correctNum;
            int tmp = meanList.length - 1;

            Collections.shuffle(this.wordArrayList, rand);
            for(int n = 0; n < meanList.length; n++){
                meanList[n] = this.wordArrayList.get(n);
            }
            for(int k = 0; k < meanList.length; k++){
                for(int l = k+1;  l < meanList.length; l++){
                    if(meanList[k].getMeaning().equals(meanList[l].getMeaning())){
                        tmp++;
                        meanList[l] = this.wordArrayList.get(tmp);
                        break;
                    }
                }
            }
            System.out.println("======== 객관식 퀴즈" + (i+1) + "번 ========");
            correctNum = rand.nextInt(4);
            System.out.println(meanList[correctNum].getWord() + "의 뜻은 무엇일까요?");

            for(int m = 0;  m < meanList.length; m++){
                System.out.println(m+1 + ") " + meanList[m].getMeaning());
            }
            System.out.print("답을 입력하세요 : ");
            try{
                num = sc.nextInt();
            }catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("틀렸습니다. 정답은 " + (correctNum+1) + "입니다.");
                continue;
            }
            if(num == correctNum + 1){
                System.out.println("정답입니다.");
                count++;
            }else{
                System.out.println("틀렸습니다. 정답은 " + (correctNum+1) + "입니다.");
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("이광해님 10문제 중 " + count + "개를 맞추셨고, 총 " + (end - start) / 1000 + "초 소요되었습니다.");
    }
}
