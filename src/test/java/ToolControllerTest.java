import com.factory.Application;
import com.factory.controllers.RoomController;
import com.factory.controllers.ToolController;
import com.factory.entities.Room;
import com.factory.entities.Tool;
import com.factory.entities.Workshop;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@DataJpaTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ToolControllerTest {

    private ToolController toolContoller;

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

        toolContoller = new ToolController(toolRepo, roomRepo, entityFields);
        mockMvc = MockMvcBuilders.standaloneSetup(toolContoller)
                .setHandlerExceptionResolvers(new ExceptionHandlerExceptionResolver()).build();

        testDataSet = new TestDataSet(workshopRepo, roomRepo, toolRepo, sensorRepo);
        testDataSet.generateTestDataSet();

    }

    @Test
    @DisplayName("method GET /tools?roomId=3")
    public void firstTest() throws Exception {

        assertFalse(toolRepo.findAll().isEmpty());

        mockMvc.perform(MockMvcRequestBuilders.get("/tools").param("roomId", "3").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "\"spec\":\"" + testDataSet.spec1 + "\",\"numberOfSensors\":" + testDataSet.numberOfSensors1)));
    }

    @Test
    @DisplayName("method GET /tool/full/2")
    public void secondTest() throws Exception {

        List<Tool> listToolsBySpec = toolRepo.findBySpec(testDataSet.spec1);
        assertFalse(listToolsBySpec.isEmpty());

        for (Tool tool : listToolsBySpec) {
            mockMvc.perform(MockMvcRequestBuilders.get("/tool/full/" + tool.getId()).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andDo(print())
                    .andExpect(jsonPath("spec").value(tool.getSpec()));
        }
    }

    @Test
    @DisplayName("method POST tool/")
    public void thirdTest() throws Exception {

        Room room = roomRepo.findById(3L).orElse(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/tool?roomId=3").contentType(MediaType.APPLICATION_JSON)
                .param("spec","SPEC4").param("numberOfSensors", "11").param("workshop", room.toString()))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("method PUT tool/")
    public void forthTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/tool/5").contentType(MediaType.APPLICATION_JSON)
                .param("spec","SPEC4").param("numberOfSensors", "18"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("method DELETE tool/")
    public void fifthTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/tool/5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
