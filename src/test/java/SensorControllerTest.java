import com.factory.Application;
import com.factory.controllers.SensorController;
import com.factory.controllers.ToolController;
import com.factory.entities.Room;
import com.factory.entities.Sensor;
import com.factory.entities.Tool;
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

import java.util.List;

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
public class SensorControllerTest {

    private SensorController sensorController;

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

        sensorController = new SensorController(sensorRepo, toolRepo, entityFields);
        mockMvc = MockMvcBuilders.standaloneSetup(sensorController)
                .setHandlerExceptionResolvers(new ExceptionHandlerExceptionResolver()).build();

        testDataSet = new TestDataSet(workshopRepo, roomRepo, toolRepo, sensorRepo);
        testDataSet.generateTestDataSet();

    }

    @Test
    @DisplayName("method GET /sensor?toolId=5")
    public void firstTest() throws Exception {

        assertFalse(sensorRepo.findAll().isEmpty());

        mockMvc.perform(MockMvcRequestBuilders.get("/sensor").param("toolId", "5").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "\"docs\":\"" + testDataSet.doc1 + "\",\"units\":\"" + testDataSet.units1 + "\",\"price\":" + testDataSet.price1)));
    }


    @Test
    @DisplayName("method POST sensor/")
    public void thirdTest() throws Exception {

        Tool tool = toolRepo.findById(5L).orElse(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/sensor?toolId=5").contentType(MediaType.APPLICATION_JSON)
                .param("docs","PDF").param("units", "meters")
                .param("price", "555").param("tool", tool.toString()))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("method PUT sensor/")
    public void forthTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/sensor/7").contentType(MediaType.APPLICATION_JSON)
                .param("docs","PDF").param("units", "meters")
                .param("price", "456"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("method DELETE sensor/")
    public void fifthTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/sensor/7").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
