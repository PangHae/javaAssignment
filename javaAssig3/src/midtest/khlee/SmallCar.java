package midtest.khlee;

public class SmallCar extends Car {

    private int displacement;

    public SmallCar(String name, String carNum, int displacement) {
        super(name, carNum);
        this.displacement = displacement;
    }

    @Override
    public String toString() {
        return super.toString() + ", 배기량 : " + this.displacement + "cc";
    }
}
