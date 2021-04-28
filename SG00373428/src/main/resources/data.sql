INSERT INTO salesperson (spid, name) VALUES ("S1", "Tom O'Hara");
INSERT INTO salesperson (spid, name) VALUES ("S2", "Mary Fitzgerald");
INSERT INTO salesperson (spid, name) VALUES ("S3", "Bill Flynn");
INSERT INTO salesperson (spid, name) VALUES ("S4", "Alan Keenan");
INSERT INTO salesperson (spid, name) VALUES ("S5", "Alice O'Rourke");

INSERT INTO product (pid, product, quantity, orderable) VALUES ("TV", "TV", 4, "true");
INSERT INTO product (pid, product, quantity, orderable) VALUES ("LAP", "Laptop", 3, "true");
INSERT INTO product (pid, product, quantity, orderable) VALUES ("USB", "4GB USB", 0, "true");
INSERT INTO product (pid, product, quantity, orderable) VALUES ("iP8", "iPhone 8", 2, "false");
INSERT INTO product (pid, product, quantity, orderable) VALUES ("iP12", "iPhone 12", 1, "false");

INSERT INTO ordertable (oid, order_date, pid_fk, spid_FK, order_quantity) VALUES ("O200", "2019-12-01", 1, 1, 2);
INSERT INTO ordertable (oid, order_date, pid_fk, spid_FK, order_quantity) VALUES ("O201", "2020-01-10", 1, 3, 1);
INSERT INTO ordertable (oid, order_date, pid_fk, spid_FK, order_quantity) VALUES ("O202", "2020-05-16", 3, 3, 4);
INSERT INTO ordertable (oid, order_date, pid_fk, spid_FK, order_quantity) VALUES ("O203", "2020-05-27", 4, 2, 33);
INSERT INTO ordertable (oid, order_date, pid_fk, spid_FK, order_quantity) VALUES ("O204", "2020-09-30", 2, 1, 1);
INSERT INTO ordertable (oid, order_date, pid_fk, spid_FK, order_quantity) VALUES ("O205", "2021-02-19", 3, 2, 12);
