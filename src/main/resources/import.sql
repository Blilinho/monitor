-- Development data for validating monitor CRUD endpoints.
-- The CTE inserts the parent row and reuses its generated ID in the JOINED subclass table.

WITH new_monitor AS (
    INSERT INTO monitor (name, brand, price, screensize, idpaneltype)
    VALUES ('UltraGear 27GL850', 'LG', 2800.00, 27.0, 1)
    RETURNING id
)
INSERT INTO gamingmonitor (id, refreshrate, responsetime, gsyncsupport)
SELECT id, 144, 1.0, true FROM new_monitor;

WITH new_monitor AS (
    INSERT INTO monitor (name, brand, price, screensize, idpaneltype)
    VALUES ('Odyssey G7', 'Samsung', 4200.00, 27.0, 2)
    RETURNING id
)
INSERT INTO gamingmonitor (id, refreshrate, responsetime, gsyncsupport)
SELECT id, 240, 1.0, true FROM new_monitor;

WITH new_monitor AS (
    INSERT INTO monitor (name, brand, price, screensize, idpaneltype)
    VALUES ('UltraSharp U2723QE', 'Dell', 5100.00, 27.0, 1)
    RETURNING id
)
INSERT INTO professionalmonitor (id, coloraccuracy, heightadjustment)
SELECT id, '100% sRGB', true FROM new_monitor;

WITH new_monitor AS (
    INSERT INTO monitor (name, brand, price, screensize, idpaneltype)
    VALUES ('ProArt PA OLED', 'ASUS', 7200.00, 26.9, 4)
    RETURNING id
)
INSERT INTO professionalmonitor (id, coloraccuracy, heightadjustment)
SELECT id, '99% DCI-P3', true FROM new_monitor;
