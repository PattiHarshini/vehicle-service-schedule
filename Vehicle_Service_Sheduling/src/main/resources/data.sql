-- Insert services if they don't exist
INSERT INTO service_type (name, description, price, duration, available) 
SELECT 'Basic Oil Change', 'Complete engine oil change with filter replacement', 999.00, '30 mins', true
WHERE NOT EXISTS (SELECT 1 FROM service_type WHERE name = 'Basic Oil Change');

INSERT INTO service_type (name, description, price, duration, available) 
SELECT 'Full Service', 'Complete vehicle inspection and maintenance', 2499.00, '2 hours', true
WHERE NOT EXISTS (SELECT 1 FROM service_type WHERE name = 'Full Service');

INSERT INTO service_type (name, description, price, duration, available) 
SELECT 'Brake Service', 'Brake pad replacement and system check', 1999.00, '1 hour', true
WHERE NOT EXISTS (SELECT 1 FROM service_type WHERE name = 'Brake Service');

INSERT INTO service_type (name, description, price, duration, available) 
SELECT 'AC Service', 'AC system cleaning and gas refill', 1499.00, '1 hour', true
WHERE NOT EXISTS (SELECT 1 FROM service_type WHERE name = 'AC Service');

INSERT INTO service_type (name, description, price, duration, available) 
SELECT 'Battery Replacement', 'Battery check and replacement if needed', 2499.00, '30 mins', true
WHERE NOT EXISTS (SELECT 1 FROM service_type WHERE name = 'Battery Replacement');

INSERT INTO service_type (name, description, price, duration, available) 
SELECT 'Tire Rotation', 'Tire rotation and balancing', 799.00, '45 mins', true
WHERE NOT EXISTS (SELECT 1 FROM service_type WHERE name = 'Tire Rotation');

INSERT INTO service_type (name, description, price, duration, available) 
SELECT 'Wheel Alignment', 'Four-wheel alignment check and adjustment', 1299.00, '1 hour', true
WHERE NOT EXISTS (SELECT 1 FROM service_type WHERE name = 'Wheel Alignment');

INSERT INTO service_type (name, description, price, duration, available) 
SELECT 'Transmission Service', 'Transmission fluid change and system check', 3499.00, '2 hours', true
WHERE NOT EXISTS (SELECT 1 FROM service_type WHERE name = 'Transmission Service');

INSERT INTO service_type (name, description, price, duration, available) 
SELECT 'Engine Tune-up', 'Spark plug replacement and engine optimization', 1999.00, '1.5 hours', true
WHERE NOT EXISTS (SELECT 1 FROM service_type WHERE name = 'Engine Tune-up');

INSERT INTO service_type (name, description, price, duration, available) 
SELECT 'Suspension Check', 'Complete suspension system inspection', 1499.00, '1 hour', true
WHERE NOT EXISTS (SELECT 1 FROM service_type WHERE name = 'Suspension Check');

-- Create tables if they don't exist
CREATE TABLE IF NOT EXISTS vehicle_brand (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS vehicle_model (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand_id BIGINT,
    FOREIGN KEY (brand_id) REFERENCES vehicle_brand(id)
);

-- Clear existing data to avoid duplicates on restart
SET FOREIGN_KEY_CHECKS=0; 
TRUNCATE TABLE vehicle_model;
TRUNCATE TABLE vehicle_brand;
SET FOREIGN_KEY_CHECKS=1;

-- Insert Vehicle Brands (30 brands)
INSERT INTO vehicle_brand (name) VALUES
('Maruti Suzuki'), ('Hyundai'), ('Tata'), ('Mahindra'), ('Kia'), ('Toyota'), ('Honda'), ('Volkswagen'), ('Skoda'), ('Renault'), ('Ford'), ('Nissan'), ('BMW'), ('Mercedes-Benz'), ('Audi'), ('MG'), ('Volvo'), ('Jaguar'), ('Land Rover'), ('Jeep'), ('Mini'), ('Lexus'), ('Porsche'), ('Ferrari'), ('Lamborghini'), ('Aston Martin'), ('Bentley'), ('Rolls-Royce'), ('Maserati'), ('McLaren');

-- Insert Models for each Brand (Comprehensive List)

-- Maruti Suzuki (16 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Swift', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Baleno', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Dzire', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Wagon R', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Alto 800', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Alto K10', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Vitara Brezza', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Ertiga', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Celerio', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'S-Presso', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'XL6', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Ciaz', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Ignis', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Jimny', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Fronx', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Grand Vitara', id FROM vehicle_brand WHERE name = 'Maruti Suzuki';

-- Hyundai (14 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'i20', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Creta', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Verna', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Venue', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Grand i10 Nios', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Aura', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Santro', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Tucson', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Alcazar', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Kona Electric', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'i10', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Xcent', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Elantra', id FROM vehicle_brand WHERE name = 'Hyundai';
INSERT INTO vehicle_model (name, brand_id) SELECT 'i30', id FROM vehicle_brand WHERE name = 'Hyundai';

-- Tata (13 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Nexon', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Altroz', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Harrier', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Safari', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Tiago', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Tigor', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Punch', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Nexon EV', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Tigor EV', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Indica', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Indigo', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Sumo', id FROM vehicle_brand WHERE name = 'Tata';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Nano', id FROM vehicle_brand WHERE name = 'Tata';

-- Mahindra (13 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Thar', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'XUV700', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Scorpio-N', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Scorpio Classic', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Bolero', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'XUV300', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Marazzo', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'XUV500', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'TUV300', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'KUV100', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Verito', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Xylo', id FROM vehicle_brand WHERE name = 'Mahindra';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Quanto', id FROM vehicle_brand WHERE name = 'Mahindra';

-- Kia (7 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Seltos', id FROM vehicle_brand WHERE name = 'Kia';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Sonet', id FROM vehicle_brand WHERE name = 'Kia';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Carnival', id FROM vehicle_brand WHERE name = 'Kia';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Carens', id FROM vehicle_brand WHERE name = 'Kia';
INSERT INTO vehicle_model (name, brand_id) SELECT 'EV6', id FROM vehicle_brand WHERE name = 'Kia';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Rio', id FROM vehicle_brand WHERE name = 'Kia';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Picanto', id FROM vehicle_brand WHERE name = 'Kia';

-- Toyota (11 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Innova Crysta', id FROM vehicle_brand WHERE name = 'Toyota';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Fortuner', id FROM vehicle_brand WHERE name = 'Toyota';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Glanza', id FROM vehicle_brand WHERE name = 'Toyota';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Urban Cruiser Hyryder', id FROM vehicle_brand WHERE name = 'Toyota';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Vellfire', id FROM vehicle_brand WHERE name = 'Toyota';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Camry', id FROM vehicle_brand WHERE name = 'Toyota';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Hilux', id FROM vehicle_brand WHERE name = 'Toyota';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Corolla', id FROM vehicle_brand WHERE name = 'Toyota';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Yaris', id FROM vehicle_brand WHERE name = 'Toyota';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Etios', id FROM vehicle_brand WHERE name = 'Toyota';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Liva', id FROM vehicle_brand WHERE name = 'Toyota';

-- Honda (8 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'City', id FROM vehicle_brand WHERE name = 'Honda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Amaze', id FROM vehicle_brand WHERE name = 'Honda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'WR-V', id FROM vehicle_brand WHERE name = 'Honda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Jazz', id FROM vehicle_brand WHERE name = 'Honda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Civic', id FROM vehicle_brand WHERE name = 'Honda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'CR-V', id FROM vehicle_brand WHERE name = 'Honda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Brio', id FROM vehicle_brand WHERE name = 'Honda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Mobilio', id FROM vehicle_brand WHERE name = 'Honda';

-- Volkswagen (8 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Polo', id FROM vehicle_brand WHERE name = 'Volkswagen';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Vento', id FROM vehicle_brand WHERE name = 'Volkswagen';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Taigun', id FROM vehicle_brand WHERE name = 'Volkswagen';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Virtus', id FROM vehicle_brand WHERE name = 'Volkswagen';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Tiguan', id FROM vehicle_brand WHERE name = 'Volkswagen';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Passat', id FROM vehicle_brand WHERE name = 'Volkswagen';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Jetta', id FROM vehicle_brand WHERE name = 'Volkswagen';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Golf', id FROM vehicle_brand WHERE name = 'Volkswagen';

-- Skoda (8 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Kushaq', id FROM vehicle_brand WHERE name = 'Skoda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Slavia', id FROM vehicle_brand WHERE name = 'Skoda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Octavia', id FROM vehicle_brand WHERE name = 'Skoda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Superb', id FROM vehicle_brand WHERE name = 'Skoda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Kodiaq', id FROM vehicle_brand WHERE name = 'Skoda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Rapid', id FROM vehicle_brand WHERE name = 'Skoda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Fabia', id FROM vehicle_brand WHERE name = 'Skoda';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Yeti', id FROM vehicle_brand WHERE name = 'Skoda';

-- Renault (7 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Kwid', id FROM vehicle_brand WHERE name = 'Renault';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Triber', id FROM vehicle_brand WHERE name = 'Renault';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Kiger', id FROM vehicle_brand WHERE name = 'Renault';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Duster', id FROM vehicle_brand WHERE name = 'Renault';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Captur', id FROM vehicle_brand WHERE name = 'Renault';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Lodgy', id FROM vehicle_brand WHERE name = 'Renault';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Scala', id FROM vehicle_brand WHERE name = 'Renault';

-- Ford (8 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'EcoSport', id FROM vehicle_brand WHERE name = 'Ford';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Figo', id FROM vehicle_brand WHERE name = 'Ford';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Aspire', id FROM vehicle_brand WHERE name = 'Ford';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Freestyle', id FROM vehicle_brand WHERE name = 'Ford';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Endeavour', id FROM vehicle_brand WHERE name = 'Ford';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Fiesta', id FROM vehicle_brand WHERE name = 'Ford';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Classic', id FROM vehicle_brand WHERE name = 'Ford';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Ikon', id FROM vehicle_brand WHERE name = 'Ford';

-- Nissan (6 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Magnite', id FROM vehicle_brand WHERE name = 'Nissan';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Kicks', id FROM vehicle_brand WHERE name = 'Nissan';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Micra', id FROM vehicle_brand WHERE name = 'Nissan';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Sunny', id FROM vehicle_brand WHERE name = 'Nissan';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Terrano', id FROM vehicle_brand WHERE name = 'Nissan';
INSERT INTO vehicle_model (name, brand_id) SELECT 'X-Trail', id FROM vehicle_brand WHERE name = 'Nissan';

-- BMW (10 models)
INSERT INTO vehicle_model (name, brand_id) SELECT '3 Series', id FROM vehicle_brand WHERE name = 'BMW';
INSERT INTO vehicle_model (name, brand_id) SELECT '5 Series', id FROM vehicle_brand WHERE name = 'BMW';
INSERT INTO vehicle_model (name, brand_id) SELECT 'X1', id FROM vehicle_brand WHERE name = 'BMW';
INSERT INTO vehicle_model (name, brand_id) SELECT 'X3', id FROM vehicle_brand WHERE name = 'BMW';
INSERT INTO vehicle_model (name, brand_id) SELECT 'X5', id FROM vehicle_brand WHERE name = 'BMW';
INSERT INTO vehicle_model (name, brand_id) SELECT '7 Series', id FROM vehicle_brand WHERE name = 'BMW';
INSERT INTO vehicle_model (name, brand_id) SELECT 'X7', id FROM vehicle_brand WHERE name = 'BMW';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Z4', id FROM vehicle_brand WHERE name = 'BMW';
INSERT INTO vehicle_model (name, brand_id) SELECT 'iX', id FROM vehicle_brand WHERE name = 'BMW';
INSERT INTO vehicle_model (name, brand_id) SELECT 'i4', id FROM vehicle_brand WHERE name = 'BMW';

-- Mercedes-Benz (10 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'C-Class', id FROM vehicle_brand WHERE name = 'Mercedes-Benz';
INSERT INTO vehicle_model (name, brand_id) SELECT 'E-Class', id FROM vehicle_brand WHERE name = 'Mercedes-Benz';
INSERT INTO vehicle_model (name, brand_id) SELECT 'GLA', id FROM vehicle_brand WHERE name = 'Mercedes-Benz';
INSERT INTO vehicle_model (name, brand_id) SELECT 'GLC', id FROM vehicle_brand WHERE name = 'Mercedes-Benz';
INSERT INTO vehicle_model (name, brand_id) SELECT 'S-Class', id FROM vehicle_brand WHERE name = 'Mercedes-Benz';
INSERT INTO vehicle_model (name, brand_id) SELECT 'A-Class', id FROM vehicle_brand WHERE name = 'Mercedes-Benz';
INSERT INTO vehicle_model (name, brand_id) SELECT 'B-Class', id FROM vehicle_brand WHERE name = 'Mercedes-Benz';
INSERT INTO vehicle_model (name, brand_id) SELECT 'GLE', id FROM vehicle_brand WHERE name = 'Mercedes-Benz';
INSERT INTO vehicle_model (name, brand_id) SELECT 'GLS', id FROM vehicle_brand WHERE name = 'Mercedes-Benz';
INSERT INTO vehicle_model (name, brand_id) SELECT 'AMG GT', id FROM vehicle_brand WHERE name = 'Mercedes-Benz';

-- Audi (10 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'A4', id FROM vehicle_brand WHERE name = 'Audi';
INSERT INTO vehicle_model (name, brand_id) SELECT 'A6', id FROM vehicle_brand WHERE name = 'Audi';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Q3', id FROM vehicle_brand WHERE name = 'Audi';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Q5', id FROM vehicle_brand WHERE name = 'Audi';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Q7', id FROM vehicle_brand WHERE name = 'Audi';
INSERT INTO vehicle_model (name, brand_id) SELECT 'A3', id FROM vehicle_brand WHERE name = 'Audi';
INSERT INTO vehicle_model (name, brand_id) SELECT 'A8', id FROM vehicle_brand WHERE name = 'Audi';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Q8', id FROM vehicle_brand WHERE name = 'Audi';
INSERT INTO vehicle_model (name, brand_id) SELECT 'RS5', id FROM vehicle_brand WHERE name = 'Audi';
INSERT INTO vehicle_model (name, brand_id) SELECT 'e-tron', id FROM vehicle_brand WHERE name = 'Audi';

-- MG (5 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Hector', id FROM vehicle_brand WHERE name = 'MG';
INSERT INTO vehicle_model (name, brand_id) SELECT 'ZS EV', id FROM vehicle_brand WHERE name = 'MG';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Gloster', id FROM vehicle_brand WHERE name = 'MG';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Astor', id FROM vehicle_brand WHERE name = 'MG';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Comet EV', id FROM vehicle_brand WHERE name = 'MG';

-- Volvo (6 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'XC40', id FROM vehicle_brand WHERE name = 'Volvo';
INSERT INTO vehicle_model (name, brand_id) SELECT 'XC60', id FROM vehicle_brand WHERE name = 'Volvo';
INSERT INTO vehicle_model (name, brand_id) SELECT 'XC90', id FROM vehicle_brand WHERE name = 'Volvo';
INSERT INTO vehicle_model (name, brand_id) SELECT 'S60', id FROM vehicle_brand WHERE name = 'Volvo';
INSERT INTO vehicle_model (name, brand_id) SELECT 'S90', id FROM vehicle_brand WHERE name = 'Volvo';
INSERT INTO vehicle_model (name, brand_id) SELECT 'V60', id FROM vehicle_brand WHERE name = 'Volvo';

-- Jaguar (6 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'F-Pace', id FROM vehicle_brand WHERE name = 'Jaguar';
INSERT INTO vehicle_model (name, brand_id) SELECT 'E-Pace', id FROM vehicle_brand WHERE name = 'Jaguar';
INSERT INTO vehicle_model (name, brand_id) SELECT 'I-Pace', id FROM vehicle_brand WHERE name = 'Jaguar';
INSERT INTO vehicle_model (name, brand_id) SELECT 'XF', id FROM vehicle_brand WHERE name = 'Jaguar';
INSERT INTO vehicle_model (name, brand_id) SELECT 'XJ', id FROM vehicle_brand WHERE name = 'Jaguar';
INSERT INTO vehicle_model (name, brand_id) SELECT 'F-Type', id FROM vehicle_brand WHERE name = 'Jaguar';

-- Land Rover (6 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Range Rover', id FROM vehicle_brand WHERE name = 'Land Rover';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Range Rover Sport', id FROM vehicle_brand WHERE name = 'Land Rover';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Range Rover Evoque', id FROM vehicle_brand WHERE name = 'Land Rover';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Range Rover Velar', id FROM vehicle_brand WHERE name = 'Land Rover';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Discovery', id FROM vehicle_brand WHERE name = 'Land Rover';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Defender', id FROM vehicle_brand WHERE name = 'Land Rover';

-- Jeep (5 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Compass', id FROM vehicle_brand WHERE name = 'Jeep';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Meridian', id FROM vehicle_brand WHERE name = 'Jeep';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Wrangler', id FROM vehicle_brand WHERE name = 'Jeep';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Cherokee', id FROM vehicle_brand WHERE name = 'Jeep';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Grand Cherokee', id FROM vehicle_brand WHERE name = 'Jeep';

-- Mini (4 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Cooper', id FROM vehicle_brand WHERE name = 'Mini';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Countryman', id FROM vehicle_brand WHERE name = 'Mini';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Clubman', id FROM vehicle_brand WHERE name = 'Mini';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Convertible', id FROM vehicle_brand WHERE name = 'Mini';

-- Lexus (6 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'ES', id FROM vehicle_brand WHERE name = 'Lexus';
INSERT INTO vehicle_model (name, brand_id) SELECT 'LS', id FROM vehicle_brand WHERE name = 'Lexus';
INSERT INTO vehicle_model (name, brand_id) SELECT 'NX', id FROM vehicle_brand WHERE name = 'Lexus';
INSERT INTO vehicle_model (name, brand_id) SELECT 'RX', id FROM vehicle_brand WHERE name = 'Lexus';
INSERT INTO vehicle_model (name, brand_id) SELECT 'LC', id FROM vehicle_brand WHERE name = 'Lexus';
INSERT INTO vehicle_model (name, brand_id) SELECT 'RC', id FROM vehicle_brand WHERE name = 'Lexus';

-- Porsche (7 models)
INSERT INTO vehicle_model (name, brand_id) SELECT '911', id FROM vehicle_brand WHERE name = 'Porsche';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Cayenne', id FROM vehicle_brand WHERE name = 'Porsche';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Macan', id FROM vehicle_brand WHERE name = 'Porsche';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Panamera', id FROM vehicle_brand WHERE name = 'Porsche';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Cayman', id FROM vehicle_brand WHERE name = 'Porsche';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Boxster', id FROM vehicle_brand WHERE name = 'Porsche';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Taycan', id FROM vehicle_brand WHERE name = 'Porsche';

-- Ferrari (5 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'F8 Tributo', id FROM vehicle_brand WHERE name = 'Ferrari';
INSERT INTO vehicle_model (name, brand_id) SELECT 'SF90 Stradale', id FROM vehicle_brand WHERE name = 'Ferrari';
INSERT INTO vehicle_model (name, brand_id) SELECT '812 Superfast', id FROM vehicle_brand WHERE name = 'Ferrari';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Roma', id FROM vehicle_brand WHERE name = 'Ferrari';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Portofino', id FROM vehicle_brand WHERE name = 'Ferrari';

-- Lamborghini (4 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Huracan', id FROM vehicle_brand WHERE name = 'Lamborghini';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Aventador', id FROM vehicle_brand WHERE name = 'Lamborghini';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Urus', id FROM vehicle_brand WHERE name = 'Lamborghini';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Revuelto', id FROM vehicle_brand WHERE name = 'Lamborghini';

-- Aston Martin (4 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'DB11', id FROM vehicle_brand WHERE name = 'Aston Martin';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Vantage', id FROM vehicle_brand WHERE name = 'Aston Martin';
INSERT INTO vehicle_model (name, brand_id) SELECT 'DBS', id FROM vehicle_brand WHERE name = 'Aston Martin';
INSERT INTO vehicle_model (name, brand_id) SELECT 'DBX', id FROM vehicle_brand WHERE name = 'Aston Martin';

-- Bentley (4 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Continental GT', id FROM vehicle_brand WHERE name = 'Bentley';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Flying Spur', id FROM vehicle_brand WHERE name = 'Bentley';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Bentayga', id FROM vehicle_brand WHERE name = 'Bentley';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Mulliner', id FROM vehicle_brand WHERE name = 'Bentley';

-- Rolls-Royce (5 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Phantom', id FROM vehicle_brand WHERE name = 'Rolls-Royce';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Ghost', id FROM vehicle_brand WHERE name = 'Rolls-Royce';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Wraith', id FROM vehicle_brand WHERE name = 'Rolls-Royce';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Dawn', id FROM vehicle_brand WHERE name = 'Rolls-Royce';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Cullinan', id FROM vehicle_brand WHERE name = 'Rolls-Royce';

-- Maserati (5 models)
INSERT INTO vehicle_model (name, brand_id) SELECT 'Ghibli', id FROM vehicle_brand WHERE name = 'Maserati';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Quattroporte', id FROM vehicle_brand WHERE name = 'Maserati';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Levante', id FROM vehicle_brand WHERE name = 'Maserati';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Grecale', id FROM vehicle_brand WHERE name = 'Maserati';
INSERT INTO vehicle_model (name, brand_id) SELECT 'MC20', id FROM vehicle_brand WHERE name = 'Maserati';

-- McLaren (4 models)
INSERT INTO vehicle_model (name, brand_id) SELECT '720S', id FROM vehicle_brand WHERE name = 'McLaren';
INSERT INTO vehicle_model (name, brand_id) SELECT '570S', id FROM vehicle_brand WHERE name = 'McLaren';
INSERT INTO vehicle_model (name, brand_id) SELECT 'GT', id FROM vehicle_brand WHERE name = 'McLaren';
INSERT INTO vehicle_model (name, brand_id) SELECT 'Artura', id FROM vehicle_brand WHERE name = 'McLaren';

-- Make vehicle_id nullable in bookings table to support new booking approach
ALTER TABLE bookings MODIFY COLUMN vehicle_id BIGINT NULL; 