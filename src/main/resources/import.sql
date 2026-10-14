-- Seed development data for Monitor E-Commerce Admin API

-- 1. Brands (Associação 1:N com Monitor)
INSERT INTO brand (name, country, website) VALUES ('LG', 'South Korea', 'https://www.lg.com');
INSERT INTO brand (name, country, website) VALUES ('Samsung', 'South Korea', 'https://www.samsung.com');
INSERT INTO brand (name, country, website) VALUES ('Dell', 'United States', 'https://www.dell.com');
INSERT INTO brand (name, country, website) VALUES ('ASUS', 'Taiwan', 'https://www.asus.com');

-- 2. Features / Technologies (Muitos para Muitos N:N com Monitor)
INSERT INTO feature (name, description) VALUES ('NVIDIA G-Sync', 'Adaptive synchronization technology for tear-free gaming');
INSERT INTO feature (name, description) VALUES ('AMD FreeSync Premium', 'Fluid, artifact-free performance at virtually any framerate');
INSERT INTO feature (name, description) VALUES ('HDR10', 'High Dynamic Range for realistic brightness and contrast');
INSERT INTO feature (name, description) VALUES ('Flicker-Safe', 'Protects eyes from fatigue by reducing flicker levels');
INSERT INTO feature (name, description) VALUES ('Hardware Color Calibration', 'Dedicated 14-bit 3D LUT for color critical workflows');

-- 3. Accessories (Agregação com Monitor)
INSERT INTO accessory (name, category, price, description) VALUES ('Gas Spring Single Monitor Arm', 'Mounts', 249.90, 'Full motion ergonomic monitor arm VESA 75/100');
INSERT INTO accessory (name, category, price, description) VALUES ('Braided DisplayPort 1.4 Cable 2m', 'Cables', 89.90, 'Supports 8K@60Hz and 4K@144Hz video transfer');
INSERT INTO accessory (name, category, price, description) VALUES ('Monitor ScreenBar LED Lamp', 'Lighting', 199.00, 'Auto-dimming e-reading eye-care desk lamp');
INSERT INTO accessory (name, category, price, description) VALUES ('Anti-Glare Privacy Screen Filter 27"', 'Filters', 149.00, 'Polarizing filter protecting screen and privacy');

-- 4. Hardware Specifications (Composição 1:1 com Monitor)
INSERT INTO hardwarespec (weight, dimensions, powerconsumption) VALUES (6.1, '614 x 460 x 274 mm', 45);
INSERT INTO hardwarespec (weight, dimensions, powerconsumption) VALUES (7.2, '614 x 576 x 305 mm', 60);
INSERT INTO hardwarespec (weight, dimensions, powerconsumption) VALUES (5.8, '611 x 385 x 185 mm', 38);
INSERT INTO hardwarespec (weight, dimensions, powerconsumption) VALUES (6.5, '612 x 500 x 215 mm', 50);

-- 5. Monitors & GamingMonitors (Herança JOINED)
WITH m1 AS (
    INSERT INTO monitor (name, price, screensize, id_panel_type, id_brand, id_hardware_spec)
    VALUES ('UltraGear 27GL850', 2800.00, 27.0, 1, 1, 1)
    RETURNING id
)
INSERT INTO gamingmonitor (id, refreshrate, responsetime, gsyncsupport)
SELECT id, 144, 1.0, true FROM m1;

WITH m2 AS (
    INSERT INTO monitor (name, price, screensize, id_panel_type, id_brand, id_hardware_spec)
    VALUES ('Odyssey G7', 4200.00, 27.0, 2, 2, 2)
    RETURNING id
)
INSERT INTO gamingmonitor (id, refreshrate, responsetime, gsyncsupport)
SELECT id, 240, 1.0, true FROM m2;

-- 6. Monitors & ProfessionalMonitors (Herança JOINED)
WITH m3 AS (
    INSERT INTO monitor (name, price, screensize, id_panel_type, id_brand, id_hardware_spec)
    VALUES ('UltraSharp U2723QE', 5100.00, 27.0, 1, 3, 3)
    RETURNING id
)
INSERT INTO professionalmonitor (id, coloraccuracy, heightadjustment)
SELECT id, '100% sRGB, 98% DCI-P3', true FROM m3;

WITH m4 AS (
    INSERT INTO monitor (name, price, screensize, id_panel_type, id_brand, id_hardware_spec)
    VALUES ('ProArt PA279CV', 4800.00, 27.0, 1, 4, 4)
    RETURNING id
)
INSERT INTO professionalmonitor (id, coloraccuracy, heightadjustment)
SELECT id, '100% sRGB, Calman Verified Delta E < 2', true FROM m4;

-- 7. Relacionamentos Muitos para Muitos (Monitor <-> Feature)
INSERT INTO monitor_feature (id_monitor, id_feature) VALUES (1, 1);
INSERT INTO monitor_feature (id_monitor, id_feature) VALUES (1, 2);
INSERT INTO monitor_feature (id_monitor, id_feature) VALUES (1, 3);
INSERT INTO monitor_feature (id_monitor, id_feature) VALUES (2, 1);
INSERT INTO monitor_feature (id_monitor, id_feature) VALUES (2, 2);
INSERT INTO monitor_feature (id_monitor, id_feature) VALUES (3, 3);
INSERT INTO monitor_feature (id_monitor, id_feature) VALUES (3, 4);
INSERT INTO monitor_feature (id_monitor, id_feature) VALUES (4, 4);
INSERT INTO monitor_feature (id_monitor, id_feature) VALUES (4, 5);

-- 8. Relacionamentos de Agregação (Monitor <-> Accessory)
INSERT INTO monitor_accessory (id_monitor, id_accessory) VALUES (1, 1);
INSERT INTO monitor_accessory (id_monitor, id_accessory) VALUES (1, 2);
INSERT INTO monitor_accessory (id_monitor, id_accessory) VALUES (2, 1);
INSERT INTO monitor_accessory (id_monitor, id_accessory) VALUES (3, 1);
INSERT INTO monitor_accessory (id_monitor, id_accessory) VALUES (3, 3);
INSERT INTO monitor_accessory (id_monitor, id_accessory) VALUES (4, 3);
