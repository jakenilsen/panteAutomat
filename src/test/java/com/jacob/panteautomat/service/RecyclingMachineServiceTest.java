package com.jacob.panteautomat.service;

import com.jacob.panteautomat.utils.RecyclableObjects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class RecyclingMachineServiceTest {

    RecyclingMachineService recyclingMachineService = new RecyclingMachineService();

    @Test
    public void testInsertCan() {
        int cans = 0;

        recyclingMachineService.insertCan();
        for (Map.Entry<String, Integer> recycledObjects: recyclingMachineService.getRecycledAmount().entrySet()) {
            if (recycledObjects.getKey().equals(RecyclableObjects.CAN.getString())) {
                cans++;
            }
        }
        Assertions.assertEquals(1, cans);
    }

    @Test
    public void testInsertBottle() {
        int bottles = 0;

        recyclingMachineService.insertBottle();
        for (Map.Entry<String, Integer> recycledObjects: recyclingMachineService.getRecycledAmount().entrySet()) {
            if (recycledObjects.getKey().equals(RecyclableObjects.BOTTLE.getString())) {
                bottles++;
            }
        }
        Assertions.assertEquals(1, bottles);
    }

    @Test
    public void testRecyclingMachineShouldGetCorrectValue() {
        int expected = 7;

        recyclingMachineService.insertCan(); // 2kr
        recyclingMachineService.insertBottle(); // 3kr
        recyclingMachineService.insertCan(); // 2kr

        Assertions.assertEquals(expected, recyclingMachineService.calculateRecycledValue());
    }



}
