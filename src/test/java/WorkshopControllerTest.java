import com.factory.Application;
import com.factory.controllers.WorkshopController;
import com.factory.entities.Workshop;
import com.factory.repos.RoomRepo;
import com.factory.repos.WorkshopRepo;
import com.factory.services.EntityFields;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@ExtendWith(SpringExtension.class)
//@WebMvcTest(WorkshopController.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@DataJpaTest
public class WorkshopControllerTest {

    private WorkshopController workshopController;

    private MockMvc mockMvc;

    @Autowired
    private WorkshopRepo workshopRepo;

    private RoomRepo roomRepo;

    private EntityFields entityFields;

    @BeforeEach
    void setUp() throws Exception {
        //MockitoAnnotations.initMocks(WorkshopControllerTest.this);

        workshopController = new WorkshopController(workshopRepo, roomRepo, entityFields);
        mockMvc = MockMvcBuilders.standaloneSetup(workshopController).setHandlerExceptionResolvers(new ExceptionHandlerExceptionResolver()).build();
    }

    @Test
    @DisplayName("ALFA")
    public void firstTest() throws Exception {

        Workshop workshop1 = new Workshop("ALFA", 100);
        workshopRepo.save(workshop1);

        List<Workshop> found = workshopRepo.findByName(workshop1.getName());
        assertFalse(found.isEmpty());

        mockMvc.perform(MockMvcRequestBuilders.get("/workshops").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$[0].name").value(workshop1.getName()));
    }
}
