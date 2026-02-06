package com.example.demo.Service;

import com.example.demo.Entity.VehicleModel;
import java.util.List;

public interface VehicleModelService {
    List<VehicleModel> getModelsByBrandId(Long brandId);
} 