package com.eshore;

import com.eshore.springboot.config.controller.ConfigController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by Blaze on 2018/6/27.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ConfigController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class SpringRestdocTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api-doc")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello,Spring Restdocs")))
                .andDo(document("RestDoc"));
    }
}
