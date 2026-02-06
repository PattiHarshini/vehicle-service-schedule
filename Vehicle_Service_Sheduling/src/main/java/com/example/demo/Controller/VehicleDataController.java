package com.example.demo.Controller;

import com.example.demo.Entity.VehicleModel;
import com.example.demo.Service.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleDataController {

    @Autowired
    private VehicleModelService vehicleModelService;

    @GetMapping("/models")
    public List<VehicleModel> getModelsByBrand(@RequestParam Long brandId) {
        return vehicleModelService.getModelsByBrandId(brandId);
    }
} 