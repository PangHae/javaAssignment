package midtest.khlee;

import java.io.FileNotFoundException;

public class MidTestMain {

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("1번) 201711409 이광해");
		System.out.println("2번) 차 생성");		
		Car car1 = new Car("홍길동","1234");
		System.out.println(car1);		
		
		System.out.println();
		
		System.out.println("3번) 장애인용차 생성");		
		Car car2 = new CarForDisabledPerson("김길동","2345", "0001","중증");
		System.out.println(car2);		
		System.out.println();
		Car car3 = new CarForDisabledPerson("최길동","3456", "0002","경증");
		System.out.println(car3);
		
		System.out.println();
		
		System.out.println("4번) 소형차 생성");		
		Car car4 = new SmallCar("이길동","4567",800);
		System.out.println(car4);
		
		System.out.println();
		
		System.out.println("5번) 주차장 생성");			
		ParkingLot parking1 = new ParkingLot("parkingmap.txt", 1000);
		
		System.out.println();
		
		System.out.println("6번) 주차하기");			
		parking1.Parking(car1);
		parking1.Parking(car2);
		parking1.Parking(car3);
		parking1.Parking(car4);		
		
		System.out.println();
		
		System.out.println("7번) 출차하기");			
		parking1.PullOut();
		parking1.PullOut();
		parking1.PullOut();
		System.out.println();
		parking1.Parking(car1);
		
		System.out.println();
				
		System.out.println("8번) 주차장 수입 출력");				
		System.out.println(parking1);
		
	}

	
}
