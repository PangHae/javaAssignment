import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        SwingUI menu = new SwingUI();
        menu.addButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();

                if(obj == menu.makeWordBook){
                    menu.setMakeWordBookPanel();
                    menu.showWordList.setEnabled(true);
                    menu.search.setEnabled(true);
                    menu.quiz.setEnabled(true);
                    menu.wrongWord.setEnabled(true);
                    menu.addWord.setEnabled(true);
                }else if(obj == menu.showWordList){
                    menu.setShowWordPanel();
                    menu.cardLayout.show(menu.container, "ShowWord");
                }else if(obj == menu.search){
                    menu.setSearchPanel();
                    menu.cardLayout.show(menu.container, "Search");
                }else if(obj == menu.quiz){
                    menu.start = System.currentTimeMillis();
                    menu.setQuizPanel();
                    menu.cardLayout.show(menu.container, "Quiz");
                }else if(obj == menu.wrongWord){
                    menu.setWrongWordPanel();
                    menu.cardLayout.show(menu.container, "WrongWord");
                }else if(obj == menu.addWord){
                    menu.setAddWordPanel();
                    menu.cardLayout.show(menu.container, "AddWord");
                }else if(obj == menu.returnToMenu){
                    menu.cardLayout.show(menu.container, "Menu");
                }else if(obj == menu.next){
                    if(menu.pageNum != 10){
                        for(int i = 0; i < menu.quesList.length; i++){
                            if(menu.rbs[i].isSelected() && menu.rbs[i].getText().equals(menu.quesList[menu.correctNum].getMeaning())){
                                menu.correct++;
                                menu.flag = 1;
                                break;
                            }
                        }
                        if(menu.flag != 1){
                            int tmp = 0;
                            for(int i = 0; i < menu.wrongWordList.size(); i++){
                                if(menu.wrongWordList.get(i).equals(menu.quesList[menu.correctNum])){
                                    menu.wrongWordList.get(i).count++;
                                    tmp = 1;
                                }
                            }
                            if(tmp != 1){
                                menu.wrongWordList.add(menu.quesList[menu.correctNum]);
                            }
                        }
                        menu.makePage();
                    }else{
                        menu.endPage();
                        menu.cardLayout.show(menu.container, "LastPage");
                    }
                }else if(obj == menu.add){
                    if(menu.wordField.getText() != null && menu.meaningField.getText() != null){
                        menu.fc.addNewWord(menu.wordField.getText(), menu.meaningField.getText());
                        menu.wordField.setText("");
                        menu.meaningField.setText("");
                    }
                }
            }
        });
    }
}
