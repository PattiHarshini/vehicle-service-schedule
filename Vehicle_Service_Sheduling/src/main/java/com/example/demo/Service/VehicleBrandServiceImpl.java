package com.example.demo.Service;

import com.example.demo.Entity.VehicleBrand;
import com.example.demo.Repository.VehicleBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleBrandServiceImpl implements VehicleBrandService {

    @Autowired
    private VehicleBrandRepository brandRepository;

    @Override
    public List<VehicleBrand> getAllBrands() {
        return brandRepository.findAll();
    }
} 