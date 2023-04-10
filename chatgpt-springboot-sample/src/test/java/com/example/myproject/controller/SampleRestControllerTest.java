package com.example.myproject.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SampleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetSample() throws Exception {
        Long id = 1L;
        MvcResult result = mockMvc.perform(get("/api/sample/{id}", id))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Sample Data #" + id);
    }

    @Test
    public void testCreateSample() throws Exception {
        String data = "New Sample Data";
        MvcResult result = mockMvc.perform(post("/api/sample")
                        .contentType("text/plain")
                        .content(data))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Created Sample Data: " + data);
    }

    @Test
    public void testUpdateSample() throws Exception {
        Long id = 1L;
        String data = "Updated Sample Data";
        MvcResult result = mockMvc.perform(put("/api/sample/{id}", id)
                        .contentType("text/plain")
                        .content(data))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Updated Sample Data #" + id + ": " + data);
    }

    @Test
    public void testDeleteSample() throws Exception {
        Long id = 1L;
        MvcResult result = mockMvc.perform(delete("/api/sample/{id}", id))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Deleted Sample Data #" + id);
    }
}
