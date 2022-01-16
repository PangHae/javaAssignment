package midtest.khlee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParkingLot {

    private int cost = 0;
    private String [][] parkStatus;
    private Car [][] parkingLot;
    private int totalCost = 0;

    public ParkingLot(String fileName, int cost) throws FileNotFoundException {
        this.cost = cost;

        File file = new File("./src/midtest/khlee/" + fileName);
        Scanner sc = new Scanner(file);

        String [] splitLine;
        String line;
        int count = 0;

        while(sc.hasNextLine()){
            line = sc.nextLine();
            splitLine = line.split(" ");
            if(count == 0){
                parkStatus = new String[Integer.parseInt(splitLine[0])][Integer.parseInt(splitLine[1])];
                parkingLot = new Car[Integer.parseInt(splitLine[0])][Integer.parseInt(splitLine[1])];
                count++;
            }else{
                for(int i = 0; i < splitLine.length; i++){
                    parkStatus[count - 1][i] = splitLine[i];
                }
                count++;
            }
        }

        for(int i = 0; i < parkingLot.length; i++){
            for(int j = 0; j < parkingLot[0].length; j++){
                parkingLot[i][j] = null;
            }
        }
    }

    public void Parking(Car car1) {
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        this.printMap();
        while(true){
            System.out.println(car1);
            System.out.print("주차할 자리를 선택하세요(예, 1 1) ==>");
            row = sc.nextInt();
            col = sc.nextInt();

            if(parkStatus[row-1][col-1].equals("0")){
                if(car1 instanceof CarForDisabledPerson){
                    parkingLot[row-1][col-1] = car1;
                    System.out.println("주차가 완료되었습니다.");
                    this.printMap();
                    break;
                }else{
                    System.out.println("장애인 주차구역입니다. 다른 자리를 선택해주세요.");
                }
            }else if(parkStatus[row-1][col-1].equals("1")){
                if(car1 instanceof SmallCar){
                    parkingLot[row-1][col-1] = car1;
                    System.out.println("주차가 완료되었습니다.");
                    this.printMap();
                    break;
                }else{
                    System.out.println("소형차 전용 주차 구역입니다. 다른 자리를 선택해 주세요.");
                }
            }else{
                parkingLot[row-1][col-1] = car1;
                System.out.println("주차가 완료되었습니다.");
                this.printMap();
                break;
            }
            this.printMap();
        }
    }

    public void printMap(){
        System.out.println("=================================");
        for(int i = 0; i < parkStatus.length + 1; i++){
            for(int j = 0; j < parkStatus[0].length + 1; j++){
                if(i == 0 && j == 0){
                    System.out.print("   ");
                }else if(i == 0 && j != 0){
                    System.out.print(" " + j + " ");
                }else if(i != 0 && j == 0){
                    System.out.print(" " + i + " ");
                }else{
                    if(parkStatus[i-1][j-1].equals("0")){
                        if(parkingLot[i-1][j-1] == null){
                            System.out.print(" ☆ ");
                        }else{
                            System.out.print(" ★ ");
                        }
                    }else if(parkStatus[i-1][j-1].equals("1")){
                        if(parkingLot[i-1][j-1] == null){
                            System.out.print(" ○ ");
                        }else{
                            System.out.print(" ● ");
                        }
                    }else{
                        if(parkingLot[i-1][j-1] == null){
                            System.out.print(" ◇ ");
                        }else{
                            System.out.print(" ◆ ");
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println("=================================");
    }

    public void PullOut() {
        String carNum;
        Scanner sc = new Scanner(System.in);
        boolean flag = true;


            System.out.print("출차할 차량번호를 입력해주세요 :");
            carNum = sc.nextLine();
            for(int i = 0; i < parkingLot.length; i++){
                for(int j = 0; j < parkingLot[0].length; j++){
                    if(parkingLot[i][j] != null && parkingLot[i][j].getCarNum().equals(carNum)){
                        if(parkingLot[i][j] instanceof CarForDisabledPerson){
                            CarForDisabledPerson c = (CarForDisabledPerson)parkingLot[i][j];
                            if(c.getDisableStat().equals("중증")) {
                                System.out.println("주차 가격은 " + this.cost * 0.7 * 100 / 100.0 + "입니다. 안녕히 가세요.");
                                totalCost += this.cost * 0.7 * 100 / 100.0;
                                parkingLot[i][j] = null;
                                flag = false;
                                break;
                            }else{
                                System.out.println("주차 가격은 " + this.cost * 0.6 * 100 / 100.0 + "입니다. 안녕히 가세요.");
                                totalCost += this.cost * 0.6 * 100 / 100.0;
                                parkingLot[i][j] = null;
                                flag = false;
                                break;
                            }
                        }else if(parkingLot[i][j] instanceof SmallCar){
                            System.out.println("주차 가격은 " + this.cost * 0.5 * 100 / 100.0 + "입니다. 안녕히 가세요.");
                            totalCost += this.cost * 0.5 * 100 / 100.0;
                            parkingLot[i][j] = null;
                            flag = false;
                            break;
                        }else{
                            System.out.println("주차 가격은 " + this.cost * 100 / 100.0  + "입니다. 안녕히 가세요.");
                            totalCost += this.cost * 100 / 100.0;
                            parkingLot[i][j] = null;
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if(flag == true){
                System.out.println("차량번호를 확인해주세요.");
            }
    }

    @Override
    public String toString() {
        String str;
        str = "기본 주차료 : " + this.cost * 100 / 100.0 +"\n";
        str += "전체 수입 : " + this.totalCost * 100 / 100.0 + "\n";

        return str;
    }
}
