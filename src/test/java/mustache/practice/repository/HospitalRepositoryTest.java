package mustache.practice.repository;

import mustache.practice.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {
    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    @DisplayName("리스트 메서드 테스트")
    void test1(){
        List<String> list = new ArrayList<>();
        list.add("보건소");
        list.add("보건지소");
        list.add("보건진료소");
        List<Hospital> byBusinessTypeName = hospitalRepository.findByBusinessTypeNameIn(list);
        for (Hospital hospital : byBusinessTypeName) {
            System.out.println(hospital.getHospitalName());
        }
    }

}