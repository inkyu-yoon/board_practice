package mustache.practice.domain.entity;

import lombok.*;
import mustache.practice.domain.dto.HospitalResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Setter
@Table(name = "hospital")
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    public Hospital(String openServiceName, int openLocalGovernmentCode, String managementNumber, LocalDateTime licenseDate, int businessStatus, int businessStatusCode, String phone, String fullAddress, String roadNameAddress, String hospitalName, String businessTypeName, int healthcareProviderCount, int patientRoomCount, int totalNumberOfBeds, float totalAreaSize) {
        this.openServiceName = openServiceName;
        this.openLocalGovernmentCode = openLocalGovernmentCode;
        this.managementNumber = managementNumber;
        this.licenseDate = licenseDate;
        this.businessStatus = businessStatus;
        this.businessStatusCode = businessStatusCode;
        this.phone = phone;
        this.fullAddress = fullAddress;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
        this.businessTypeName = businessTypeName;
        this.healthcareProviderCount = healthcareProviderCount;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.totalAreaSize = totalAreaSize;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "open_service_name")
    private String openServiceName;

    @Column(name = "open_local_government_code")
    private Integer openLocalGovernmentCode;

    @Column(name = "management_number")
    private String managementNumber;

    @Column(name = "license_date")
    private LocalDateTime licenseDate;

    @Column(name = "business_status")
    private Integer businessStatus;

    @Column(name = "business_status_code")
    private Integer businessStatusCode;

    private String phone;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "business_type_name")
    private String businessTypeName;

    @Column(name = "healthcare_provider_count")
    private Integer healthcareProviderCount;

    @Column(name = "patient_room_count")
    private Integer patientRoomCount;

    @Column(name = "total_number_of_beds")
    private Integer totalNumberOfBeds;

    @Column(name = "total_area_size")
    private Float totalAreaSize;

    // HospitalEntity를 HospitalResponse Dto로 만들어주는 부분
    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(),
                hospital.getRoadNameAddress(), hospital.getHospitalName(),
                hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(), hospital.getBusinessTypeName(),
                hospital.getTotalAreaSize());
    }
}