import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = ru.company.Main.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class StringControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFrequencyInString() throws Exception {
        String input = "aaaaabcccc";

        mockMvc.perform(MockMvcRequestBuilders.get("/frequency")
                        .param("str", input))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.a").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.c").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.b").value(1));
    }
    @Test
    public void testFrequencyInStringWithoutNum() throws Exception {
        String input = "1a2a3a4a5a6b7c8c9c0c";

        mockMvc.perform(MockMvcRequestBuilders.get("/frequency")
                        .param("str", input))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.a").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.c").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.b").value(1));
    }
    @Test
    public void testFrequencyInStringNull() throws Exception {
        String input = "";

        mockMvc.perform(MockMvcRequestBuilders.get("/frequency")
                        .param("str", input))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{}"));
    }
    @Test
    public void testFrequencyInStringSymbol() throws Exception {
        String input = "!@&*_+=-";

        mockMvc.perform(MockMvcRequestBuilders.get("/frequency")
                        .param("str", input))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.!").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.@").value(1));
    }
    @Test
    public void testFrequencyInStringNum() throws Exception {
        String input = "111222333";

        mockMvc.perform(MockMvcRequestBuilders.get("/frequency")
                        .param("str", input))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{}"));
    }

}
