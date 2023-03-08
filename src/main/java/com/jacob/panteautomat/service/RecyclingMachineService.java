package com.jacob.panteautomat.service;

import com.jacob.panteautomat.model.Bottle;
import com.jacob.panteautomat.model.Can;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecyclingMachineService {

    private ArrayList<Object> recycledObjects = new ArrayList<>();

    public void insertCan() {
        recycledObjects.add(new Can());
    }
    public void insertBottle() {
        recycledObjects.add(new Bottle());
    }

    public String getRecycledAmount() {
        int numberOfCans = 0;
        int numberOfBottles = 0;

        for (Object o: recycledObjects) {
            if (o instanceof Can) {
                numberOfCans++;
            }
            if (o instanceof Bottle) {
                numberOfBottles++;
            }
        }
        return String.format("You have recycled %d can(s) and %d bottle(s).", numberOfCans, numberOfBottles);
    }

     private int calculateRecycledValue() {
        int totalValueOfCans = 0;
        int totalValueOfBottles = 0;

        for (Object o: recycledObjects) {
            if (o instanceof Can) {
                totalValueOfCans += ((Can) o).getValue();
            }
            if (o instanceof Bottle) {
                totalValueOfBottles += ((Bottle) o).getValue();
            }
        }
        return totalValueOfCans + totalValueOfBottles;
    }

    public String printVoucher() {
        return String.format("%s You will receive a voucher of %d kr!", getRecycledAmount(), calculateRecycledValue());
    }



//    public List insertBottle() {
//
//    }
}
