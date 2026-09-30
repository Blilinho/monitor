-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- insert into Monitor (id, name, brand, price, screensize) values (nextval('monitor_seq'), 'UltraSharp U2720Q', 'Dell', 3500.0, 27.0);
-- insert into ProfessionalMonitor (id, coloraccuracy, heightadjustment, paneltype) values (currval('monitor_seq'), '99% sRGB', true, 1);

insert into Monitor (id, name, brand, price, screensize) values (nextval('monitor_seq'), 'UltraGear 27GL850', 'LG', 2800.0, 27.0);
insert into GamingMonitor (id, refreshrate, responsetime, gsyncsupport) values (currval('monitor_seq'), 144, 1.0, true);

insert into Monitor (id, name, brand, price, screensize) values (nextval('monitor_seq'), 'Odyssey G7', 'Samsung', 4200.0, 27.0);
insert into GamingMonitor (id, refreshrate, responsetime, gsyncsupport) values (currval('monitor_seq'), 240, 1.0, true);

insert into Monitor (id, name, brand, price, screensize) values (nextval('monitor_seq'), 'ROG Swift PG259QN', 'ASUS', 5100.0, 24.5);
insert into GamingMonitor (id, refreshrate, responsetime, gsyncsupport) values (currval('monitor_seq'), 360, 1.0, true);