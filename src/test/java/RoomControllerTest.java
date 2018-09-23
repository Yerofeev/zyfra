import com.factory.Application;
import com.factory.controllers.RoomController;
import com.factory.entities.Room;
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
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@DataJpaTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RoomControllerTest {

    private RoomController roomContoller;

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

        roomContoller = new RoomController(roomRepo, workshopRepo, entityFields);
        mockMvc = MockMvcBuilders.standaloneSetup(roomContoller)
                .setHandlerExceptionResolvers(new ExceptionHandlerExceptionResolver()).build();

        testDataSet = new TestDataSet(workshopRepo, roomRepo, toolRepo, sensorRepo);
        testDataSet.generateTestDataSet();

    }

    @Test
    @DisplayName("method GET /rooms?workshopId=1")
    public void firstTest() throws Exception {

        assertFalse(roomRepo.findAll().isEmpty());

        mockMvc.perform(MockMvcRequestBuilders.get("/rooms").param("workshopId", "1").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "\"title\":\"" + testDataSet.title1 + "\",\"square\":" + testDataSet.square1)))
                .andExpect(content().string(
                        containsString("\"title\":\"" + testDataSet.title2 + "\",\"square\":" +  testDataSet.square2)));
    }

    @Test
    @DisplayName("method GET /room/full/2")
    public void secondTest() throws Exception {

        List<Room> listRoomsByTitle = roomRepo.findByTitle(testDataSet.title1);
        assertFalse(listRoomsByTitle.isEmpty());

        for (Room room : listRoomsByTitle) {
            mockMvc.perform(MockMvcRequestBuilders.get("/room/full/" + room.getId()).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andDo(print())
                    .andExpect(jsonPath("title").value(room.getTitle()));
        }
    }

    @Test
    @DisplayName("method POST room/")
    public void thirdTest() throws Exception {

        Workshop workshop = workshopRepo.findById(1L).orElse(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/room?workshopId=1").contentType(MediaType.APPLICATION_JSON)
                .param("title","Carbon").param("square", "700").param("workshop", workshop.toString()))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("method PUT room/")
    public void forthTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/room/3").contentType(MediaType.APPLICATION_JSON)
                .param("title","Omega").param("square", "777"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("method DELETE room/")
    public void fifthTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/room/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
