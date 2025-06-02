# noinspection SqlResolveForFile @ any/"treasy"

DROP USER IF EXISTS 'treasy'@'%';
DROP USER IF EXISTS 'treasy'@'localhost';

CREATE USER 'treasy'@'%' IDENTIFIED BY 'treasy';
CREATE USER 'treasy'@'localhost' IDENTIFIED BY 'treasy';

GRANT ALL PRIVILEGES ON treasy.* TO 'treasy'@'%';
GRANT ALL PRIVILEGES ON treasy.* TO 'treasy'@'localhost';

FLUSH PRIVILEGES;
