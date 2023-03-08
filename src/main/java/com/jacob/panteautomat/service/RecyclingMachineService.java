package com.jacob.panteautomat.service;

import com.jacob.panteautomat.model.Bottle;
import com.jacob.panteautomat.model.Can;
import com.jacob.panteautomat.model.VoucherHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        return String.format("%d can(s) and %d bottle(s) have been added to the recycling machine.", numberOfCans, numberOfBottles);
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
        if (calculateRecycledValue() == 0) {
            logger.warn("User has not added any recycleable objects to the machine!");
            return "You have not added any recycleable objects to the machine!";
        }
        String voucherText = String.format("%s You will receive a voucher of %d kr!", getRecycledAmount(), calculateRecycledValue());
        logger.info("Printed voucher: " + voucherText);

        voucherHistory.add(new VoucherHistory(voucherText, new Date()));
        logger.info("Added voucher to history");

        recycledObjects.clear();
        logger.info("Recycling Machine is cleared: " + getRecycledAmount());

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
