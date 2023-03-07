package com.jacob.panteautomat.service;

import com.jacob.panteautomat.model.Can;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RecyclingMachineServiceTest {


    @Test
    public void testInsertCan() {
        RecyclingMachineService recyclingMachineService = new RecyclingMachineService();
        List<Object> list = recyclingMachineService.insertCan();
        for (Object s: list) {
            if (s instanceof Can) {
                System.out.println(((Can) s).getValue());
            }
            System.out.println(s);
        }

        Assertions.assertEquals("", "");
    }

//    @Test
//    public void testInsertBottle() {
//        RecyclingMachineService recyclingMachineService = new RecyclingMachineService();
//        recyclingMachineService.insertBottle();
//        recyclingMachineService.insertBottle();
//
//        Assertions.assertEquals("", "");
//    }

}
