import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Quiz {

    public ArrayList<Word> wordList;
    public Word [] meanList;

    public Quiz(ArrayList<Word> wordList){
        this.meanList = new Word[4];
        this.wordList = wordList;
    }

    public Word[] makeQuiz(){
        Random rand = new Random();

        int tmp = meanList.length - 1;
        Collections.shuffle(this.wordList, rand);
        for(int j = 0; j < meanList.length; j++){
            meanList[j] = this.wordList.get(j);
            this.wordList.get(j).countUp();
        }
        for(int k = 0; k < meanList.length; k++){
            for(int l = k+1; l < meanList.length; l++){
                if(meanList[k].getMeaning().equals(meanList[l].getMeaning())){
                    this.wordList.get(l).countDown();
                    tmp++;
                    meanList[l] = this.wordList.get(tmp);
                    this.wordList.get(tmp).countUp();
                    break;
                }
            }
        }
        //}
        return this.meanList;
    }
}

