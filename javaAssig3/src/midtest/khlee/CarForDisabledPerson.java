package midtest.khlee;

public class CarForDisabledPerson extends Car {

    private String disNum;
    private String disableStat;

    public CarForDisabledPerson(String name, String carNum, String disNum, String disableStat) {
        super(name, carNum);
        this.disNum = disNum;
        this.disableStat = disableStat;
    }

    public String getDisableStat() {
        return disableStat;
    }

    @Override
    public String toString() {
        return super.toString() + ", 장애인등록번호 : " + this.disNum + ", 장애정도 : " + this.disableStat;
    }
}
