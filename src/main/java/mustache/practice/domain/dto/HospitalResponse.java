package mustache.practice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HospitalResponse {
    private Long id;
    private String roadNameAddress;
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;

    public HospitalResponse(Long id, String roadNameAddress, String hospitalName, Integer patientRoomCount, Integer totalNumberOfBeds, String businessTypeName, Float totalAreaSize) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.businessTypeName = businessTypeName;
        this.totalAreaSize = totalAreaSize;
    }

    private String businessStatusName;

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }

}