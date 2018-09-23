import com.factory.Application;
import com.factory.controllers.WorkshopController;
import com.factory.repos.RoomRepo;
import com.factory.repos.SensorRepo;
import com.factory.repos.ToolRepo;
import com.factory.repos.WorkshopRepo;
import com.factory.services.EntityFields;
import com.factory.services.TestDataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@DataJpaTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WorkshopControllerTest {

    private WorkshopController workshopController;

    private MockMvc mockMvc;

    private TestDataSet testDataSet;

    @Autowired
    private WorkshopRepo workshopRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private ToolRepo toolRepo;

    @Autowired
    private SensorRepo sensorRepo;

    private EntityFields entityFields;

    @BeforeEach
    void setUp() throws Exception {

        workshopController = new WorkshopController(workshopRepo, entityFields);
        mockMvc = MockMvcBuilders.standaloneSetup(workshopController)
                .setHandlerExceptionResolvers(new ExceptionHandlerExceptionResolver()).build();

        testDataSet = new TestDataSet(workshopRepo, roomRepo, toolRepo, sensorRepo);
        testDataSet.generateTestDataSet();

    }

    @Test
    @DisplayName("method GET /workshops")
    public void firstTest() throws Exception {

        assertTrue(workshopRepo.findAll().size() == 2);

        mockMvc.perform(MockMvcRequestBuilders.get("/workshops").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "\"name\":\"" + testDataSet.name1 + "\",\"employeeCount\":" + testDataSet.employeeCount1)))
                .andExpect(content().string(
                        containsString("\"name\":\"" + testDataSet.name2 + "\",\"employeeCount\":" +  testDataSet.employeeCount2 + ",\"rooms\":null")));

    }

    @Test
    @DisplayName("method GET /workshop/full/2")
    public void secondTest() throws Exception  {

        assertFalse(workshopRepo.findByName(testDataSet.name1).isEmpty());

        mockMvc.perform(MockMvcRequestBuilders.get("/workshop/full/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("name").value(testDataSet.name2));
    }

    @Test
    @DisplayName("method POST workshop/")
    public void thirdTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/workshop").contentType(MediaType.APPLICATION_JSON)
                .param("name","Omega").param("count", "111"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("method PUT workshop/")
    public void forthTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/workshop/1").contentType(MediaType.APPLICATION_JSON)
                .param("name","Omega").param("count", "111"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("method DELETE workshop/")
    public void fifthTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/workshop/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
