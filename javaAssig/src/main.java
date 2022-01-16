import java.util.Scanner;

public class main {
    public static void menu(){
        int menuNum;
        while(true) {
            System.out.print("1) 복리 예금 계산기 ");
            System.out.print("2) 구구단 출력하기 ");
            System.out.println("3) 종료");
            System.out.print("메뉴를 선택하세요 : ");

            Scanner sc = new Scanner(System.in);
            menuNum = sc.nextInt();
            if(menuNum == 1){
                menuOne();
            }else if(menuNum == 2){
                menuTwo();
            }else if(menuNum == 3){
                System.out.println("프로그램이 종료되었습니다.");
                break;
            }else{
                System.out.println("잘못된 입력입니다. 다시 입력해주시 바랍니다.\n");
            }
        }
    }
    private static void menuOne() {
        double money;
        double get;
        double beforeMoney;
        double afterMoney = 0;
        int count = 0;
        
        System.out.println("========= 복리 예금 계산기 =========");
        Scanner sc = new Scanner(System.in);
        System.out.print("원금을 입력하세요 :");
        money = sc.nextInt();
        System.out.print("연이율을 입력하세요 : ");
        get = sc.nextDouble();

        beforeMoney = money;

        System.out.println("----------------------------------------------");
        System.out.print("연이율   ");
        System.out.println("원리금           ");
        System.out.println("----------------------------------------------");
        while(afterMoney < money * 2){
            count++;
            afterMoney = beforeMoney * (100.0 + get) / 100.0;
            beforeMoney = afterMoney;
            System.out.print(count + "  ");
            System.out.format("%.2f\n", afterMoney);
        }
        System.out.println("----------------------------------------------");
        System.out.println(count + "년 걸림");
        System.out.println();
    }

    private static void menuTwo() {
        int num;
        int count = 2;

        System.out.println("========= 구구단 출력하기 =========");
        Scanner sc = new Scanner(System.in);
        System.out.print("출력 단수 : ");
        num = sc.nextInt();
        while(num < 1 || num > 8){
            System.out.println("출력 단 수 입력이 잘 못되었습니다.");
            num = sc.nextInt();
        }
        while(count < 9){
            for(int i = 1; i < 10; i++){
                for(int j = count; j < count + num; j++){
                    if(j == 10){
                        break;
                    }
                    System.out.print(j + " * " +  i + " = ");
                    System.out.format("%2d", i * j);
                    System.out.print("      ");
                }
                System.out.println();
                if(i == 9){
                    count += (num -1);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        menu();
    }
}
