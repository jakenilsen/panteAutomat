package com.jacob.panteautomat.controller;

import com.jacob.panteautomat.service.RecyclingMachineService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@CrossOrigin(maxAge = 3600)
public class RecyclingMachineController {

    Logger logger = LoggerFactory.getLogger(RecyclingMachineController.class);

    private final Bucket canBucket;
    private final Bucket bottleBucket;

    private final RecyclingMachineService recyclingMachineService = new RecyclingMachineService();

    public RecyclingMachineController() {
        Bandwidth canRequestLimit = Bandwidth.classic(1, Refill.intervally(1, Duration.ofMillis(500)));
        this.canBucket = Bucket.builder()
                .addLimit(canRequestLimit)
                .build();

        Bandwidth bottleRequestLimit = Bandwidth.classic(1, Refill.intervally(1, Duration.ofSeconds(1)));
        this.bottleBucket = Bucket.builder()
                .addLimit(bottleRequestLimit)
                .build();
    }


    @PostMapping("/cans")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String insertCan() {
        if(canBucket.tryConsume(1)) {
            String canMessage = "Can inserted. ";

            recyclingMachineService.insertCan();

            logger.info(canMessage + recyclingMachineService.getRecycledAmount());
            return canMessage;
        }
        logger.warn("Can inserted too fast!");
        return "You inserted a can too fast!";
    }
    @PostMapping("/bottles")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String insertBottle() {
        if (bottleBucket.tryConsume(1)) {
            String bottleMessage = "Bottle inserted. ";

            recyclingMachineService.insertBottle();

            logger.info(bottleMessage + recyclingMachineService.getRecycledAmount());
            return bottleMessage;
        }
        logger.warn("Bottle inserted too fast");
        return "You inserted a bottle too fast!";
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
