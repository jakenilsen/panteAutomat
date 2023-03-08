package com.jacob.panteautomat.controller;

import com.jacob.panteautomat.service.RecyclingMachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600)
public class RecyclingMachineController {

    Logger logger = LoggerFactory.getLogger(RecyclingMachineController.class);

    private final RecyclingMachineService recyclingMachineService = new RecyclingMachineService();


    @PostMapping("/cans")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String insertCan() {
        String canMessage = "Can inserted. ";

        recyclingMachineService.insertCan();

        logger.info(canMessage + recyclingMachineService.getRecycledAmount());
        return canMessage;
    }
    @PostMapping("/bottles")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String insertBottle() {
        String bottleMessage = "Bottle inserted. ";

        recyclingMachineService.insertBottle();

        logger.info(bottleMessage + recyclingMachineService.getRecycledAmount());
        return bottleMessage;
    }

    @GetMapping("/recycledamount")
    @ResponseStatus(code = HttpStatus.OK)
    public String getRecycledAmount() {
        return recyclingMachineService.getRecycledAmount();
    }

    @GetMapping("/printvoucher")
    @ResponseStatus(code = HttpStatus.OK)
    public String printVoucher() {
        String voucherText = recyclingMachineService.printVoucher();

        return voucherText;
    }

    @GetMapping("/voucherhistory")
    @ResponseStatus(code = HttpStatus.OK)
    public String getVoucherHistory() {
        return recyclingMachineService.getVoucherHistory();
    }
}
