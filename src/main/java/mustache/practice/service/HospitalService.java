package mustache.practice.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mustache.practice.domain.dto.BookResponseDto;
import mustache.practice.domain.dto.HospitalResponse;
import mustache.practice.domain.entity.Book;
import mustache.practice.domain.entity.Hospital;
import mustache.practice.parser.HospitalParser;
import mustache.practice.parser.Parser;
import mustache.practice.parser.ReadLine;
import mustache.practice.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HospitalService{
    private final HospitalRepository hospitalRepository;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospital");
    private EntityManager em;
    private EntityTransaction ts;


@Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.em = this.emf.createEntityManager();
        this.ts = this.em.getTransaction();
        this.hospitalRepository = hospitalRepository;

    }

    public HospitalResponse getHospital(Long id) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id); // Entity
        Hospital hospital = optHospital.get();
        HospitalResponse hospitalResponse = Hospital.of(hospital); // DTO
        if (hospital.getBusinessStatusCode() == 13) {
            hospitalResponse.setBusinessStatusName("영업중");
        } else if (hospital.getBusinessStatusCode() == 3) {
            hospitalResponse.setBusinessStatusName("폐업");
        } else {
            hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        }
        return hospitalResponse;
    }

    public List<HospitalResponse> findHospitals(Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        List<HospitalResponse> hospitalResponses = hospitals.stream()
                .map(hospital ->
                        HospitalResponse.of(hospital)
                ).collect(Collectors.toList());
        return hospitalResponses;
    }

    @Transactional
    public int insertOne(String filename) {

        Parser parser = new HospitalParser();
        ReadLine<Hospital> rc = new ReadLine<>(parser);

        ts.begin();

        List<Hospital> hospitalList;
        try {
            hospitalList = rc.readByLine(filename);
            Hospital hospital = hospitalList.get(1);
            em.persist(hospital);

            ts.commit();
        } catch (IOException e) {
            ts.rollback();
            throw new RuntimeException(e);
        }
        finally {
            em.close();
        }
        emf.close();
        if (!Optional.of(hospitalList).isEmpty()) {
            return hospitalList.size();
        } else {
            return 0;
        }
    }
    @Transactional
    public int insertLargeVolumeHospitalData(String filename) {

        Parser parser = new HospitalParser();
        ReadLine<Hospital> rc = new ReadLine<>(parser);

        ts.begin();

        List<Hospital> hospitalList;
        try {
            hospitalList = rc.readByLine(filename);
            hospitalList.stream()
                    .forEach(hospital -> {
                        try {
                            em.persist(hospital); // db에 insert하는 구간
                        } catch (Exception e) {
                            System.out.printf("id:%d 레코드에 문제가 있습니다.\n",hospital.getId());
                            throw new RuntimeException(e);
                        }
                    });

            ts.commit();
        } catch (IOException e) {
            ts.rollback();
            throw new RuntimeException(e);
        }
        finally {
            em.close();
        }
        emf.close();
        if (!Optional.of(hospitalList).isEmpty()) {
            return hospitalList.size();
        } else {
            return 0;
        }
    }

}
