public class Word implements Comparable<Word>{
    private String word;
    private String meaning;
    public int count = 0;

    public Word(String word, String meaning){
        this.word = word;
        this.meaning = meaning;
    }
    public String getWord(){
        return this.word;
    }
    public String getMeaning(){
        return this.meaning;
    }

    @Override
    public int compareTo(Word o) {
        if(this.count < o.getCount()){
            return -1;
        }else if(this.count > o.getCount()){
            return 1;
        }else{
            return 0;
        }
    }
    public void countUp(){
        this.count++;
    }
    public void countDown(){
        this.count--;
    }
    public int getCount() {
        return this.count;
    }
}
