package com.jacob.panteautomat.controller;

import com.jacob.panteautomat.service.RecyclingMachineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600)
public class RecyclingMachineController {

    private final RecyclingMachineService recyclingMachineService = new RecyclingMachineService();


    @PostMapping("/cans")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String insertCan() {
        System.out.println("can");
        recyclingMachineService.insertCan();
        return "Can inserted";
    }
    @PostMapping("/bottles")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String insertBottle() {
        System.out.println("bottle");
        recyclingMachineService.insertBottle();
        return "Bottle inserted";
    }

    @GetMapping("/recycledamount")
    public String getRecycledAmount() {
        return recyclingMachineService.getRecycledAmount();
    }

    @GetMapping("/printvoucher")
    public String printVoucher() {
        return recyclingMachineService.printVoucher();
    }
}
