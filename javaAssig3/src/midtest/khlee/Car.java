package midtest.khlee;

public class Car {

    protected String name;
    protected String carNum;

    public Car(String name, String carNum){
        this.name = name;
        this.carNum = carNum;
    }

    public String getCarNum() {
        return carNum;
    }

    @Override
    public String toString() {
        String str;

        str = "차량소유주 : " + this.name + ", 차량번호 : " + this.carNum ;
        return str;
    }
}

//                        if(parkStatus[i][j].equals("0")){
//                            CarForDisabledPerson c = (CarForDisabledPerson)parkingLot[i][j];
//                            if(c.getDisableStat().equals("중증")) {
//                                System.out.println("주차 가격은 " + this.cost * 0.7 * 100 / 100.0 + "입니다. 안녕히 가세요.");
//                                totalCost += this.cost * 0.7 * 100 / 100.0;
//                                parkingLot[i][j] = null;
//                                flag = false;
//                                break;
//                            }else{
//                                System.out.println("주차 가격은 " + this.cost * 0.6 * 100 / 100.0 + "입니다. 안녕히 가세요.");
//                                totalCost += this.cost * 0.6 * 100 / 100.0;
//                                parkingLot[i][j] = null;
//                                flag = false;
//                                break;
//                            }
//                        }else if(parkStatus[i][j].equals("1")){
//                            System.out.println("주차 가격은 " + this.cost * 0.5 * 100 / 100.0 + "입니다. 안녕히 가세요.");
//                            totalCost += this.cost * 0.5 * 100 / 100.0;
//                            parkingLot[i][j] = null;
//                            flag = false;
//                            break;
//                        }else{
//                            if(parkingLot[i][j] instanceof CarForDisabledPerson){
//
//                            }
//                            System.out.println("주차 가격은 " + this.cost * 100 / 100.0  + "입니다. 안녕히 가세요.");
//                            totalCost += this.cost * 100 / 100.0;
//                            parkingLot[i][j] = null;
//                            flag = false;
//                            break;
//                        }
