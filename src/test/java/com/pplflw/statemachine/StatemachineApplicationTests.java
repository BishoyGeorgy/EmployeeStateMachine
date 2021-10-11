package com.pplflw.statemachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplflw.statemachine.dto.EmployeeDTO;
import com.pplflw.statemachine.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class StatemachineApplicationTests {

	private static final ObjectMapper om = new ObjectMapper();
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setup() {
		employeeRepository.deleteAll();
	}

	@Test
	public void testEmployeeEndpointWithPOST() throws Exception {
		EmployeeDTO expectedRecord = getTestData().get("Bishoy");
		EmployeeDTO actualRecord = om.readValue(mockMvc.perform(post("/employee")
				.contentType("application/json")
				.content(om.writeValueAsString(getTestData().get("Bishoy"))))
				.andDo(print())
				.andExpect(jsonPath("$.id", greaterThan(0)))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), EmployeeDTO.class);

		Assert.assertTrue(new ReflectionEquals(expectedRecord, "id", "state").matches(actualRecord));
		assertEquals(true, employeeRepository.findById(actualRecord.getId()).isPresent());
	}

	@Test
	public void testEmployeeEndpointWithPUT() throws Exception {
		EmployeeDTO bishoy = getTestData().get("Bishoy");
		EmployeeDTO expectedRecord = om.readValue(mockMvc.perform(post("/employee")
				.contentType("application/json")
				.content(om.writeValueAsString(bishoy)))
				.andDo(print())
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), EmployeeDTO.class);

		bishoy.setState("inCheck");
		bishoy.setId(expectedRecord.getId());
		expectedRecord.setState("inCheck");
		EmployeeDTO actualRecord = om.readValue(mockMvc.perform(put("/employee/" + expectedRecord.getId())
				.contentType("application/json")
				.content(om.writeValueAsString(bishoy)))
				.andDo(print())
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), EmployeeDTO.class);

		Assert.assertTrue(new ReflectionEquals(expectedRecord).matches(actualRecord));
	}

	private Map<String, EmployeeDTO> getTestData() throws ParseException {
		Map<String, EmployeeDTO> data = new LinkedHashMap<>();

		EmployeeDTO ahmed = new EmployeeDTO(0, "Ahmed", 20, "Full_Time", "hi");
		data.put("Ahmed", ahmed);

		EmployeeDTO mohammed = new EmployeeDTO(0, "Mohammed", 30, "Full_Time", "");
		data.put("Mohammed", mohammed);

		EmployeeDTO bishoy = new EmployeeDTO(0, "Bishoy", 40, "Full_Time", "");
		data.put("Bishoy", bishoy);

		EmployeeDTO omar = new EmployeeDTO(0, "Omar", 50, "Full_Time", "");
		data.put("Omar", omar);

		return data;
	}

}
