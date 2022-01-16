import java.util.ArrayList;

public class Search {

    private ArrayList<Word> wordList;
    private String word;
    public Search(ArrayList<Word> wordList, String word){
        this.wordList = wordList;
        this.word = word;
    }

    public String searchInWordList(){
        String foundMeaning = null;

        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i) == null){
                break;
            }
            if(this.word.equals(this.wordList.get(i).getWord())){
                foundMeaning = this.wordList.get(i).getMeaning();
            }
        }

        return foundMeaning;
    }
}
