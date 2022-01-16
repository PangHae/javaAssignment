import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class SwingUI extends JFrame {

    //메인 패널
    JPanel mainMenu;
    JPanel wordBookPanel;
    JPanel showWordPanel;
    JPanel searchPanel;
    JPanel quizPanel;
    JPanel wrongWordPanel;
    JPanel addWordPanel;
    //버튼들이 들어갈 패널 및 버튼들
    JPanel buttonSide;
    JButton makeWordBook;
    JButton showWordList;
    JButton search;
    JButton quiz;
    JButton wrongWord;
    JButton addWord;
    JButton returnToMenu;
    //자기소개 패널과 구성요소
    JPanel introduceSide;
    JPanel myPicture;
    JLabel introduce;
    //단어장 패널 구성하기
    FileChoose fc = null;
    JFileChooser fileChooser;
    JTextArea wordArea;
    //검색패널 구성
    JTextField inputWord;
    JPanel searchInputPanel;
    JButton searchButton;
    JTextArea outputMeaning;
    //객관식 문제 구성
    JPanel quizBtnSide;
    JPanel radioBtnSide;
    JLabel quesNum;
    JLabel question;
    ButtonGroup btnGroup;
    JRadioButton [] rbs;
    JRadioButton rb1;
    JRadioButton rb2;
    JRadioButton rb3;
    JRadioButton rb4;
    JRadioButton fakeBtn;
    JButton next;
    Word [] quesList;
    Quiz _quiz;
    int flag = 0;
    int correctNum;
    int correct = 0;
    int pageNum = 0;
    long start;
    long end;
    ArrayList<Word> wrongWordList;
    JPanel lastPage;
    //오답노트 구성
    JTextArea wrongWordTextArea;
    WrongWord ww;
    //단어 추가 구성
    JButton add;
    JLabel word;
    JLabel meaning;
    JTextField wordField;
    JTextField meaningField;
    JPanel innerPanel;
    JPanel btnPanel;
    //메인 컨테이너
    Container container;
    CardLayout cardLayout;

    public SwingUI(){
        //메인 컨테이너 레이아웃 설정
        container = this.getContentPane();
        cardLayout = new CardLayout(0, 0);
        this.setTitle("201711409 이광해 영어 단어장");
        // 패널들 생성자 생성
        setPanels();
        this.mainMenu.setLayout(new GridLayout(1, 2, 10, 10));
        this.cardLayout = new CardLayout();
        container.setLayout(cardLayout);
        // 메인 화면 구성
        setIntroduceSide();
        setButtonSide();
        menuReturnButton();
        mainMenu.add(introduceSide);
        mainMenu.add(buttonSide);
        //객관식 문제 구성
        next = new JButton("다음");
        wrongWordList = new ArrayList<>();
        lastPage = new JPanel();
        //오답노트 구성
        ww = new WrongWord();
        wrongWordList.addAll(ww.words);
        //단어 추가 구성
        add = new JButton("추가");

        //메인 컨테이너에 패널 추가
        container.add(mainMenu, "Menu");
        container.add(wordBookPanel, "WordBook");
        container.add(showWordPanel, "ShowWord");
        container.add(searchPanel, "Search");
        container.add(quizPanel, "Quiz");
        container.add(wrongWordPanel, "WrongWord");
        container.add(addWordPanel, "AddWord");
        container.add(lastPage, "LastPage");
        //초기화면 설정
        cardLayout.show(container, "Menu");
        //크기
        this.setBounds(200, 400, 550, 550);
        this.setResizable(false);
        //창 종료 버튼 누를 시, 프로그램 종료
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        //보이게 설정
        setVisible(true);
    }

    public void menuReturnButton(){
        this.returnToMenu = new JButton("메뉴로 돌아가기");
    }

    public void setAddWordPanel(){
        innerPanel = new JPanel();
        btnPanel = new JPanel();
        addWordPanel.setLayout(new BorderLayout());
        innerPanel.setLayout(new GridLayout(2,2,10,10));
        btnPanel.setLayout(new FlowLayout());
        word = new JLabel("단어");
        meaning = new JLabel("의미");
        wordField = new JTextField(15);
        meaningField = new JTextField(15);
        innerPanel.add(word);
        innerPanel.add(wordField);
        innerPanel.add(meaning);
        innerPanel.add(meaningField);
        btnPanel.add(returnToMenu);
        btnPanel.add(add);
        addWordPanel.add(innerPanel, BorderLayout.CENTER);
        addWordPanel.add(btnPanel, BorderLayout.SOUTH);
    }
    public void setWrongWordPanel(){
        wrongWordPanel.setLayout(new BorderLayout());
        wrongWordTextArea = new JTextArea();
        wrongWordTextArea.setEditable(false);
        wrongWordTextArea.setText("단어\t\t의미\t\t빈도\n");
        wrongWordTextArea.setFont(new Font("Times", Font.PLAIN, 15));
        JScrollPane scrollPane;
        scrollPane = new JScrollPane(wrongWordTextArea);
        Collections.sort(wrongWordList, Collections.reverseOrder());
        for(int i = 0; i < wrongWordList.size(); i++){
            if(i == 20){
                break;
            }
            wrongWordTextArea.append(wrongWordList.get(i).getWord()+"\t"+wrongWordList.get(i).getMeaning()+"\t\t"+wrongWordList.get(i).getCount()+"\n\n");
            wrongWordTextArea.setCaretPosition(this.wrongWordTextArea.getDocument().getLength());
        }
        wrongWordPanel.add(scrollPane, BorderLayout.CENTER);
        wrongWordPanel.add(returnToMenu, BorderLayout.SOUTH);
    }
    public void setSearchPanel(){
        this.searchPanel.setLayout(new BorderLayout());
        this.searchInputPanel.setLayout(new FlowLayout());
        this.inputWord = new JTextField(30);
        this.searchButton = new JButton("검색");
        this.outputMeaning = new JTextArea();
        this.outputMeaning.setFont(new Font("Times", Font.PLAIN, 20));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputWord.getText().equals("")){
                    outputMeaning.setText("");
                    outputMeaning.append("입력어가 존재하지 않습니다. 다시 입력해주세요.");
                }else{
                    outputMeaning.setText("");
                    Search s = new Search(fc.wordList, inputWord.getText());
                    String result = s.searchInWordList();
                    if(result == null){
                        outputMeaning.append("검색한 단어가 단어장에 존재하지 않습니다.");
                    }else{
                        outputMeaning.append(inputWord.getText() + " : " + result);
                    }
                }
            }
        });
        this.searchInputPanel.add(inputWord);
        this.searchInputPanel.add(searchButton);
        this.searchPanel.add(searchInputPanel, BorderLayout.NORTH);
        this.searchPanel.add(outputMeaning, BorderLayout.CENTER);
        this.searchPanel.add(returnToMenu,BorderLayout.SOUTH);
    }

    public void makePage(){
        System.out.println(wrongWordList.size());
        System.out.println(correct);
        flag = 0;
        fakeBtn.setSelected(true);
        quizPanel.add(next, BorderLayout.SOUTH);
        pageNum++;
        _quiz.makeQuiz();
        this.quesList = _quiz.meanList;
        Random rand = new Random();
        correctNum = rand.nextInt(4);
        quesNum.setText("객관식 "+ pageNum + "번");
        question.setText(quesList[correctNum].getWord() + "의 의미는 무엇일까요?");
        for(int i = 0; i < quesList.length; i++){
            rbs[i].setText(quesList[i].getMeaning());
        }
    }

    public void endPage(){
        lastPage.setLayout(new BorderLayout());
        end = System.currentTimeMillis();
        lastPage.add(new JLabel("이광해님 10문제 중 " + correct + "개를 맞추셨고, 총 " + (end - start) / 1000 + "초 소요되었습니다."), BorderLayout.CENTER);
        lastPage.add(returnToMenu, BorderLayout.SOUTH);
        ww.add2File(wrongWordList);
        correct = 0;
    }

    public void setQuizPanel() {
        _quiz = new Quiz(fc.wordList);
        quizPanel.setLayout(new BorderLayout());

        rb1 = new JRadioButton();
        rb2 = new JRadioButton();
        rb3 = new JRadioButton();
        rb4 = new JRadioButton();
        fakeBtn = new JRadioButton();
        rbs = new JRadioButton[]{rb1, rb2, rb3, rb4};
       quizBtnSide = new JPanel();
        radioBtnSide = new JPanel();
        radioBtnSide.setLayout(new GridLayout(4, 1,10,10));
        quesNum = new JLabel();
        question = new JLabel();
        quizBtnSide.add(quesNum);
        quizBtnSide.add(question);
        btnGroup = new ButtonGroup();
        for(int i = 0; i < rbs.length; i++){
            btnGroup.add(rbs[i]);
            radioBtnSide.add(rbs[i]);
        }
        btnGroup.add(fakeBtn);
        quizPanel.add(quizBtnSide,BorderLayout.NORTH);
        quizPanel.add(radioBtnSide, BorderLayout.CENTER);
        makePage();
    }

    public void setMakeWordBookPanel(){
        this.wordBookPanel.setLayout(new BorderLayout());
        this.fileChooser = new JFileChooser();
        int result = this.fileChooser.showOpenDialog(this.container);
        if(result == JFileChooser.APPROVE_OPTION){
            String file = this.fileChooser.getSelectedFile().getPath();
            this.fc = new FileChoose(file);
        }
        this.cardLayout.show(this.container, "Menu");
    }

    public void setShowWordPanel(){
        this.showWordPanel.setLayout(new BorderLayout());
        this.wordArea = new JTextArea(30, 30);
        wordArea.setEditable(false);
        wordArea.setFont(new Font("Times", Font.PLAIN, 15));
        JScrollPane scrollPane = null;
        wordArea.setText("");
        if(fc != null && wordArea != null){
            scrollPane = new JScrollPane(wordArea);
            for(int i = 0; i < fc.wordList.size(); i++){
                if(this.fc.wordList.get(i) == null){
                    break;
                }
                this.wordArea.append(fc.wordList.get(i).getWord() + "  :  " + fc.wordList.get(i).getMeaning() + "\n\n");
                this.wordArea.setCaretPosition(this.wordArea.getDocument().getLength());
            }
        }else{
            wordArea.setText("등록된 단어가 없습니다.");
        }
        this.showWordPanel.add(scrollPane, BorderLayout.CENTER);
        this.showWordPanel.add(this.returnToMenu, BorderLayout.SOUTH, (int)LEFT_ALIGNMENT);
    }

    public void setPanels(){
        this.mainMenu = new JPanel();
        this.wordBookPanel = new JPanel();
        this.showWordPanel = new JPanel();
        this.searchPanel = new JPanel();
        this.quizPanel = new JPanel();
        this.wrongWordPanel = new JPanel();
        this.addWordPanel = new JPanel();
        this.searchInputPanel = new JPanel();
    }

    public void setButtonSide(){
        //패널 생성
        this.buttonSide = new JPanel();
        this.buttonSide.setLayout(new GridLayout(6, 1, 20, 10));
        // 버튼 생성
        this.makeWordBook = new JButton("단어장 만들기");
        this.showWordList = new JButton("단어 목록 보기");
        showWordList.setEnabled(false);
        this.search = new JButton("검색");
        search.setEnabled(false);
        this.quiz = new JButton("객관식 퀴즈");
        quiz.setEnabled(false);
        this.wrongWord = new JButton("오답노트");
        wrongWord.setEnabled(false);
        this.addWord = new JButton("단어 추가 하기");
        addWord.setEnabled(false);

        this.returnToMenu = new JButton();
        //패널에 버튼 추가
        this.buttonSide.add(makeWordBook);
        this.buttonSide.add(showWordList);
        this.buttonSide.add(search);
        this.buttonSide.add(quiz);
        this.buttonSide.add(wrongWord);
        this.buttonSide.add(addWord);
    }

    public void setIntroduceSide(){
        introduceSide = new JPanel();
        //사진 생성
        this.myPicture = new JPanel(){
            ImageIcon icon = new ImageIcon("./src/img/myPic.jpg");
            Image img = icon.getImage();
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(img,10 ,10,250, 325, this);
            }
        };
        // 학번 이름 생성
        this.introduce = new JLabel("201711409 이광해");
        this.introduce.setHorizontalAlignment(JLabel.CENTER);
        //폰트 구성 및 추가
        Font font  = new Font("Times", Font.BOLD, 15);
        this.introduce.setFont(font);
        //패널 레이아웃 설정 및 추가
        introduceSide.setLayout(new GridLayout(2, 1, 10, 10));
        introduceSide.setBounds(100, 100, 300, 500);
        introduceSide.add(myPicture);
        introduceSide.add(introduce);
    }

    public void addButtonActionListener(ActionListener listener){
        //버튼 클릭 시 작동 추가
       this.makeWordBook.addActionListener(listener);
        this.showWordList.addActionListener(listener);
        this.search.addActionListener(listener);
        this.quiz.addActionListener(listener);
        this.wrongWord.addActionListener(listener);
        this.addWord.addActionListener(listener);
        this.returnToMenu.addActionListener(listener);
        this.next.addActionListener(listener);
        this.add.addActionListener(listener);
    }
}