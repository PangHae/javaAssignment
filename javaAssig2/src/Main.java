import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    static final int ROW = 4;
    static final int COL = 3;

    public static void printMap(String [][] parkingSpace){

        for(int i = 0; i <= ROW; i++){
            for(int j = 0; j <= COL; j++){
                if(i == 0 && j == 0){
                    System.out.print("   ");
                }else if(i == 0){
                    System.out.print(j + "  ");
                }else if(j == 0){
                    System.out.print(i + "  ");
                }else{
                    if(parkingSpace[i-1][j-1].equals("")){
                        System.out.print("♡  ");
                    }else{
                        System.out.print("♥  ");
                    }
                }
            }
            System.out.println();
        }
    }

    public static String [][] menu(String [][] parkingSpace){
        int menuNum;

        System.out.println("주차 관리(201711409 이광해)");
        System.out.println();
        printMap(parkingSpace);
        System.out.println();
        System.out.println("1) 주차하기 2) 출차하기 3) 종료");
        System.out.print("메뉴를 선택하세요 : ");
        menuNum = sc.nextInt();

        if(menuNum > 3 || menuNum < 1){
            System.out.println("메뉴 번호를 확인 후 다시 입력해주세요.");
        }else{
            if(menuNum == 1){
                parkingSpace = menuOne(parkingSpace);
            }else if(menuNum == 2){
                parkingSpace = menuTwo(parkingSpace);
            }else if(menuNum == 3){
                System.out.println("시스템을 종료합니다.");
                parkingSpace = null;
            }else{
                System.out.println("메뉴 번호를 확인 후 다시 입력해주세요.");
            }
        }
        return parkingSpace;
    }

    private static String [][] menuOne(String [][] parkingSpace) {
        int row;
        int col;
        String carNum;
        String correct;

        System.out.print("주차할 위치를 선택해주세요 (입력 예 : 2 1) : ");
        row = sc.nextInt();
        col = sc.nextInt();
        if(row > ROW || col > COL || row <= 0 || col <= 0){
            System.out.println("위치 번호를 확인해 주세요. 처음부터 다시 진행해 주세요.");
            return parkingSpace;
        }
        if(!parkingSpace[row - 1][col -1].equals("")){
            System.out.println("다른 차량이 주차되어 있습니다. 처음부터 다시 시작해 주세요.");
            return parkingSpace;
        }
        System.out.print("차량 번호를 입력해주세요 (입력 예: 20가1234) : ");
        sc.nextLine();
        carNum = sc.nextLine();
        System.out.print("차량 번호 " + carNum + "가 맞습니까?(y/n)? ");
        correct = sc.nextLine();
        if(correct.equals("n")){
            System.out.println("처음부터 다시 진행해 주세요.");
            return parkingSpace;
        }else if(correct.equals("y")){
            parkingSpace[row - 1][col - 1] = carNum;
        }else{
            System.out.println("잘못된 입력입니다. 처음부터 다시 진행해주세요.");
            return parkingSpace;
        }

        return parkingSpace;
    }

    private static String [][] menuTwo(String [][] parkingSpace) {
        String carNum;
        System.out.print("차량 번호를 입력해주세요 : ");
        sc.nextLine();
        carNum = sc.nextLine();

        for(int i = 0; i < ROW; i++){
            for(int j = 0;  j < COL; j++){
                if(carNum.equals(parkingSpace[i][j])){
                    System.out.println(parkingSpace[i][j] + " 차량이 출차되었습니다. 안녕히가세요.");
                    parkingSpace[i][j] = "";
                    return parkingSpace;
                }
            }
        }
        System.out.println("차량이 존재하지 않습니다. 차량번호 확인 후 처음부터 다시 진행해 주세요.");

        return parkingSpace;
    }

    public static void main(String [] args){
        String [][] parkingSpace = new String[ROW][COL];

        for(int i = 0;  i < ROW; i++){
            for(int j = 0;  j < COL; j++){
                parkingSpace[i][j] = "";
            }
        }

        while(true){
            parkingSpace = menu(parkingSpace);
            if (parkingSpace == null){
                break;
            }
        }
    }
}
