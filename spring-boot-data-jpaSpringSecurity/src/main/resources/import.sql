INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(1, 'Adrian', 'Rodriguez', 'adrian14@email.com', '2020-03-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(2, 'Tommaso', 'Paradiso', 'paradiso@email.com', '2019-09-17', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(3, 'Luca', 'Carboni', 'carboni@email.com', '2016-03-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(4, 'Alejandra', 'Rodriguez', 'malej8@email.com', '2008-08-05', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(5, 'Ernesto', 'Fantilli', 'erni@email.com', '2006-09-08', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(6, 'Riccardo', 'Fantilli', 'ricdo16@email.com', '2008-01-16', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(7, 'Nicholas', 'Fantilli', 'nick@email.com', '2011-04-13', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(8, 'Jesse', 'Pinkman', 'jesse@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(9, 'Walter', 'White', 'heisenber@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(10, 'Vince', 'Gilligan', 'vince@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(11, 'Mak', 'JP', 'mak@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(12, 'Saul', 'Goodman', 'saul@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(13, 'Combo', 'Ortega', 'combo@email.com', '2020-04-12', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(14, 'Jane', 'Margolis', 'jane@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(15, 'Apologize', 'Girl', 'apogirl@email.com', '2020-04-09', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(16, 'Skin', 'Ny', 'skinny@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(17, 'Brendon', 'Mageor', 'Budger@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(18, 'Gus', 'Fris', 'pollos@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(19, 'Hank', 'Schreider', 'hank@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(20, 'Mike', 'heunger', 'mike@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(21, 'Tuco', 'Salamanca', 'tuco@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(22, 'Krazy', 'eight', 'krazy-8@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(23, 'Bryan', 'Cranston', 'lee@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(24, 'Aaron', 'Paul', 'aaron@email.com', '2020-04-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(25, 'Skyler', 'White', 'skyler@email.com', '2020-04-15', '');

/* Populate table productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 190, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 204, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 51, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 299, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 34, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 211, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 124, NOW());

/* Facturas de ejemplo */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);


INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);