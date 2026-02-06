package com.example.demo.Service;

import com.example.demo.Entity.VehicleModel;
import com.example.demo.Repository.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {

    @Autowired
    private VehicleModelRepository modelRepository;

    @Override
    public List<VehicleModel> getModelsByBrandId(Long brandId) {
        return modelRepository.findByBrandId(brandId);
    }
} 