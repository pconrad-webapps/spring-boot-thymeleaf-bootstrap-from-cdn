package hello;

// import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getGreeting_ContentType() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }
    
    @Test
    public void getHomePage_BootstrapLoaded() throws Exception {
       BootstrapTestHelper.bootstrapIsLoaded(mvc,"/greeting");
    }

    @Test
    public void getGreeting_hasCorrectTitle() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath("//title").exists())
                .andExpect(xpath("//title").string("Getting Started: Serving Web Content"));
    }

    @Test
    public void getGreeting_hasNavBar() throws Exception {
        NavigationTestHelper.hasNavBar(mvc, "/greeting");
    }

    @Test
    public void getGreeting_hasFooter() throws Exception {
        NavigationTestHelper.hasFooter(mvc, "/greeting");
    }
}