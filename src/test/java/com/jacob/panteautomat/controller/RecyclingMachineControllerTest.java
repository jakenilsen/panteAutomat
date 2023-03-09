package com.jacob.panteautomat.controller;

import com.jacob.panteautomat.service.RecyclingMachineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecyclingMachineController.class)
public class RecyclingMachineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecyclingMachineService service;

    @Test
    public void insertOneCanShouldInsertCanSuccessfully() throws Exception {
        Thread.sleep(500);
        this.mockMvc.perform(post("/cans"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Can inserted.")));
    }

    @Test
    public void insertOneBottleShouldInsertBottleSuccessfully() throws Exception {
        Thread.sleep(1000);
        this.mockMvc.perform(post("/bottles"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Bottle inserted.")));
    }

    @Test
    public void insertSecondCanTooFastShouldReturnErrorMessage() throws Exception {
        Thread.sleep(500);
        this.mockMvc.perform(post("/cans"))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Can inserted.")));
        this.mockMvc.perform(post("/cans"))
                .andExpect(status().isTooManyRequests())
                .andExpect(content().string(containsString("You inserted a can too fast!")));
    }

    @Test
    public void insertSecondBottleTooFastShouldReturnErrorMessage() throws Exception {
        Thread.sleep(1000);
        this.mockMvc.perform(post("/bottles"))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Bottle inserted.")));
        this.mockMvc.perform(post("/bottles"))
                .andExpect(status().isTooManyRequests())
                .andExpect(content().string(containsString("You inserted a bottle too fast!")));
    }
    @Test
    public void insertSecondCanAfterHalfASecondShouldSucceed() throws Exception {
        Thread.sleep(500);
        this.mockMvc.perform(post("/cans"))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Can inserted.")));
        Thread.sleep(500);
        this.mockMvc.perform(post("/cans"))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Can inserted.")));
    }

    @Test
    public void insertSecondBottleAfterHalfASecondShouldSucceed() throws Exception {
        Thread.sleep(1000);
        this.mockMvc.perform(post("/bottles"))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Bottle inserted.")));
        Thread.sleep(1000);
        this.mockMvc.perform(post("/bottles"))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Bottle inserted.")));
    }
}