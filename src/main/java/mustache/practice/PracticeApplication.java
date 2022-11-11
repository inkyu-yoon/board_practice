package mustache.practice;

import mustache.practice.service.HospitalService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
		//데이터 입력
//		HospitalService hospitalService = new HospitalService("hospital");
//		hospitalService.insertLargeVolumeHospitalData("hospitalData.txt");
	}

}
