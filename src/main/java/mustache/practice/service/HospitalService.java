package mustache.practice.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mustache.practice.domain.entity.Hospital;
import mustache.practice.parser.HospitalParser;
import mustache.practice.parser.Parser;
import mustache.practice.parser.ReadLine;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class HospitalService{
    EntityManagerFactory emf ;
    EntityManager em;
    EntityTransaction ts;

    public HospitalService(String unitName) {
        this.emf = Persistence.createEntityManagerFactory(unitName);
        this.em = this.emf.createEntityManager();
        this.ts = this.em.getTransaction();
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
