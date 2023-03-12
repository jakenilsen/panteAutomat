package com.jacob.panteautomat.controller;

import com.jacob.panteautomat.service.RecyclingMachineService;
import com.jacob.panteautomat.service.RequestLimitService;
import io.github.bucket4j.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600)
public class RecyclingMachineController {

    Logger logger = LoggerFactory.getLogger(RecyclingMachineController.class);

    private final RecyclingMachineService recyclingMachineService = new RecyclingMachineService();

    private final RequestLimitService requestLimitService = new RequestLimitService();

    @PostMapping("/cans")
    public ResponseEntity insertCan(@RequestHeader(value = "objectType") String objectType) {
        Bucket bucket = requestLimitService.resolveBucket(objectType);

        if(bucket.tryConsume(1)) {
            String canMessage = "Can inserted. ";

            recyclingMachineService.insertCan();

            logger.info(canMessage + recyclingMachineService.getRecycledAmountString());
            return ResponseEntity.accepted().body(canMessage);
        }
        logger.warn("Can inserted too fast!");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("You inserted a can too fast!");
    }
    @PostMapping("/bottles")
    public ResponseEntity insertBottle(@RequestHeader(value = "objectType") String objectType) {
        Bucket bucket = requestLimitService.resolveBucket(objectType);
        if (bucket.tryConsume(1)) {
            String bottleMessage = "Bottle inserted. ";

            recyclingMachineService.insertBottle();

            logger.info(bottleMessage + recyclingMachineService.getRecycledAmountString());
            return ResponseEntity.accepted().body(bottleMessage);
        }
        logger.warn("Bottle inserted too fast");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("You inserted a bottle too fast!");
    }

    @PostMapping("/basicobject")
    public ResponseEntity insertBasicObject(@RequestHeader(value = "objectType") String objectType) {
        Bucket bucket = requestLimitService.resolveBucket(objectType);
        if (bucket.tryConsume(1)) {
            String basicObjectMessage = "Basic recyclable object inserted. ";

            recyclingMachineService.insertBasicObject();

            logger.info(basicObjectMessage + recyclingMachineService.getRecycledAmountString());
            return ResponseEntity.accepted().body(basicObjectMessage);
        }
        logger.warn("Recyclable object inserted too fast");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("You inserted the recyclable object too fast!");
    }

    @GetMapping("/recycledamount")
    @ResponseStatus(code = HttpStatus.OK)
    public String getRecycledAmount() {
        return recyclingMachineService.getRecycledAmountString();
    }

    @GetMapping("/printvoucher")
    @ResponseStatus(code = HttpStatus.OK)
    public String printVoucher() {
        logger.info("printvoucher");
        return recyclingMachineService.printVoucher();
    }

    @GetMapping("/lotterychance")
    @ResponseStatus(code = HttpStatus.OK)
    public String lotteryChance() {
        logger.info("lotterychance");
        return recyclingMachineService.getLotteryTicketString();
    }

    @GetMapping("/voucherhistory")
    @ResponseStatus(code = HttpStatus.OK)
    public String getVoucherHistory() {
        logger.info("voucherhistory");
        return recyclingMachineService.getVoucherHistory();
    }
}
