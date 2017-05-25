CREATE OR REPLACE FUNCTION initdb() RETURNS INTEGER AS $body$ DECLARE BEGIN IF NOT EXISTS(SELECT * FROM users WHERE permission = 'ROLE_ADMIN') THEN INSERT INTO users (email, PASSWORD, permission, username) VALUES ('admin@siorven.eus', '$e0801$yhehDpqtvwsSUeNtshuVFC/nDnnZGBS/dPWQyng8socOEV7XZWVBRbDmpVcZRmdu0BKp6LAwJ2RL19fV421C4Q==$K8qV4AA8FO/HTILbH3mxl2FlrnkTtM2yQHvLJT5EbVU=', 'ROLE_ADMIN', 'admin'); RETURN 1; ELSE RETURN 0; END IF; END; $body$ LANGUAGE plpgsql;
SELECT initdb();
DROP FUNCTION initdb();
