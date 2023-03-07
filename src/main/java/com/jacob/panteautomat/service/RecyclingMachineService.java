package com.jacob.panteautomat.service;

import com.jacob.panteautomat.model.Can;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecyclingMachineService {

    private ArrayList<Object> recycledObjects = new ArrayList<>();

    public List insertCan() {
        recycledObjects.add(new Can());
        return recycledObjects;
    }



//    public List insertBottle() {
//
//    }
}
