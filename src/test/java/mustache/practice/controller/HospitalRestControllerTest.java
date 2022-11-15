package mustache.practice.controller;

import mustache.practice.domain.dto.HospitalResponse;
import mustache.practice.service.HospitalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HospitalService hospitalService;
//{"id":111111,"roadNameAddress":"서울특별시 영등포구 영중로3길 3 (영등포동4가)","hospitalName":"텐텐참사랑한의원"
// ,"patientRoomCount":0,"totalNumberOfBeds":0,"businessTypeName":"한의원","totalAreaSize":154.4,"businessStatusName":"폐업"}
    @Test
    void json() throws Exception {
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                .id(111111L).roadNameAddress("서울특별시 영등포구 영중로3길 3 (영등포동4가)").hospitalName("텐텐참사랑한의원")
                .patientRoomCount(0).totalNumberOfBeds(0).businessTypeName("한의원").totalAreaSize(154.4f).businessStatusName("폐업").build();
        given(hospitalService.getHospital(111111L))
                .willReturn(hospitalResponse);

        Long hospitalId = 111111L;
        mockMvc.perform(get(String.format("/api/v1/hospitals/%d", hospitalId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalName").exists())
                .andExpect(jsonPath("$.hospitalName").value("텐텐참사랑한의원"))
                .andDo(print());  //

        verify(hospitalService).getHospital(hospitalId);
    }

}