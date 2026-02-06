package com.example.demo.Controller;

import com.example.demo.Entity.VehicleBrand;
import com.example.demo.Entity.VehicleModel;
import com.example.demo.Service.VehicleBrandService;
import com.example.demo.Service.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicle-test")
public class VehicleTestController {

    @Autowired
    private VehicleBrandService vehicleBrandService;
    
    @Autowired
    private VehicleModelService vehicleModelService;

    @GetMapping("/brands")
    public ResponseEntity<List<VehicleBrand>> getAllBrands() {
        try {
            List<VehicleBrand> brands = vehicleBrandService.getAllBrands();
            return ResponseEntity.ok(brands);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/models")
    public ResponseEntity<List<VehicleModel>> getModelsByBrand(@RequestParam Long brandId) {
        try {
            List<VehicleModel> models = vehicleModelService.getModelsByBrandId(brandId);
            return ResponseEntity.ok(models);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> testConnection() {
        return ResponseEntity.ok("Vehicle test controller is working!");
    }
} 