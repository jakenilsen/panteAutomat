package com.jacob.panteautomat.service;

import com.jacob.panteautomat.model.BasicObject;
import com.jacob.panteautomat.model.Bottle;
import com.jacob.panteautomat.model.Can;
import com.jacob.panteautomat.model.VoucherHistory;
import com.jacob.panteautomat.utils.RecyclableObjectsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecyclingMachineService {

    Logger logger = LoggerFactory.getLogger(RecyclingMachineService.class);
    private ArrayList<Object> recycledObjects = new ArrayList<>();

    private ArrayList<VoucherHistory> voucherHistory = new ArrayList<>();

    public void insertCan() {
        recycledObjects.add(new Can());
    }
    public void insertBottle() {
        recycledObjects.add(new Bottle());
    }
    public void insertBasicObject() { recycledObjects.add(new BasicObject()); }

    public Map<String, Integer> getRecycledAmount() {
        Map<String, Integer> numberOfRecycledObjects = new HashMap();

        for (Object o: recycledObjects) {
            if (o instanceof Can) {
                numberOfRecycledObjects.put(RecyclableObjectsConstants.CAN, numberOfRecycledObjects.getOrDefault(RecyclableObjectsConstants.CAN, 0) + 1);
            }
            if (o instanceof Bottle) {
                numberOfRecycledObjects.put(RecyclableObjectsConstants.BOTTLE, numberOfRecycledObjects.getOrDefault(RecyclableObjectsConstants.BOTTLE, 0) + 1);
            }
            if (o instanceof BasicObject) {
                numberOfRecycledObjects.put(RecyclableObjectsConstants.BASICOBJECT, numberOfRecycledObjects.getOrDefault(RecyclableObjectsConstants.BASICOBJECT, 0) + 1);
            }
        }
        return numberOfRecycledObjects;
    }

    public String getRecycledAmountString() {
        Map<String, Integer> numberOfRecycledObjects = getRecycledAmount();
        String basicObjectAmountString = "";
        int basicObjectAmount = numberOfRecycledObjects.getOrDefault(RecyclableObjectsConstants.BASICOBJECT, 0);

        if(basicObjectAmount != 0) {
            basicObjectAmountString = String.format("Basic recyclable object(s) inserted: %d. ", basicObjectAmount);
        }

        String recycledAmountString = String.format("%d can(s) and %d bottle(s) have been added to the recycling machine. ",
                numberOfRecycledObjects.getOrDefault(RecyclableObjectsConstants.CAN, 0),
                numberOfRecycledObjects.getOrDefault(RecyclableObjectsConstants.BOTTLE, 0));

        return recycledAmountString + basicObjectAmountString;
    }


     public int calculateRecycledValue() {
        int totalValueOfCans = 0;
        int totalValueOfBottles = 0;
        int totalValueOfBasicObjects = 0;

        for (Object o: recycledObjects) {
            if (o instanceof Can) {
                totalValueOfCans += ((Can) o).getValue();
            }
            if (o instanceof Bottle) {
                totalValueOfBottles += ((Bottle) o).getValue();
            }
            if (o instanceof BasicObject) {
                totalValueOfBasicObjects += ((BasicObject) o).getValue();
            }
        }
        return totalValueOfCans + totalValueOfBottles + totalValueOfBasicObjects;
    }

    public String printVoucher() {
        if (calculateRecycledValue() == 0) {
            logger.warn("User has not added any recycleable objects to the machine!");
            return "You have not added any recycleable objects to the machine!";
        }
        String voucherText = String.format("%sYou will receive a voucher of %d kr!", getRecycledAmountString(), calculateRecycledValue());
        logger.info("Printed voucher: " + voucherText);

        voucherHistory.add(new VoucherHistory(voucherText, new Date()));
        logger.info("Added voucher to history");

        recycledObjects.clear();
        logger.info("Recycling Machine is cleared: " + getRecycledAmountString());

        return voucherText;
    }

    public String getVoucherHistory() {
        String voucherHistoryString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        for (VoucherHistory vh: voucherHistory) {
            voucherHistoryString += String.format("%s: %s\n", dateFormat.format(vh.getDate()), vh.getVoucherText());
        }

        return voucherHistoryString;
    }
}
