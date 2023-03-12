package com.jacob.panteautomat.controller;

import com.jacob.panteautomat.service.RecyclingMachineService;
import com.jacob.panteautomat.utils.RecyclableObjectsConstants;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(RecyclingMachineController.class)
public class RecyclingMachineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecyclingMachineService service;

    @Test
    @Order(1)
    public void insertOneCanShouldInsertCanSuccessfully() throws Exception {
        this.mockMvc.perform(post("/cans").header("objectType", RecyclableObjectsConstants.CAN))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Can inserted.")));
    }

    @Test
    @Order(2)
    public void insertOneBottleShouldInsertBottleSuccessfully() throws Exception {
        this.mockMvc.perform(post("/bottles").header("objectType", RecyclableObjectsConstants.BOTTLE))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Bottle inserted.")));
    }

    @Test
    @Order(3)
    public void insertSecondCanTooFastShouldReturnErrorMessage() throws Exception {
        Thread.sleep(500);
        this.mockMvc.perform(post("/cans").header("objectType", RecyclableObjectsConstants.CAN))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Can inserted.")));
        this.mockMvc.perform(post("/cans").header("objectType", RecyclableObjectsConstants.CAN))
                .andExpect(status().isTooManyRequests())
                .andExpect(content().string(containsString("You inserted a can too fast!")));
    }

    @Test
    @Order(4)
    public void insertSecondBottleTooFastShouldReturnErrorMessage() throws Exception {
        Thread.sleep(1000);
        this.mockMvc.perform(post("/bottles").header("objectType", RecyclableObjectsConstants.BOTTLE))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Bottle inserted.")));
        this.mockMvc.perform(post("/bottles").header("objectType", RecyclableObjectsConstants.BOTTLE))
                .andExpect(status().isTooManyRequests())
                .andExpect(content().string(containsString("You inserted a bottle too fast!")));
    }
    @Test
    @Order(5)
    public void insertSecondCanAfterHalfASecondShouldSucceed() throws Exception {
        Thread.sleep(500);
        this.mockMvc.perform(post("/cans").header("objectType", RecyclableObjectsConstants.CAN))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Can inserted.")));
        Thread.sleep(500);
        this.mockMvc.perform(post("/cans").header("objectType", RecyclableObjectsConstants.CAN))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Can inserted.")));
    }

    @Test
    @Order(6)
    public void insertSecondBottleAfterHalfASecondShouldSucceed() throws Exception {
        Thread.sleep(1000);
        this.mockMvc.perform(post("/bottles").header("objectType", RecyclableObjectsConstants.BOTTLE))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Bottle inserted.")));
        Thread.sleep(1000);
        this.mockMvc.perform(post("/bottles").header("objectType", RecyclableObjectsConstants.BOTTLE))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Bottle inserted.")));
    }

    @Test
    @Order(7)
    public void postWithoutHeaderShouldReturnForbidden() throws Exception {
        Thread.sleep(500);
        this.mockMvc.perform(post("/cans"))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(content().string(containsString("Missing requestheader 'objectType'")));
    }
}