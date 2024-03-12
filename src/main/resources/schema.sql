DROP TABLE IF EXISTS configurations;
CREATE TABLE configurations (
    id          INTEGER AUTO_INCREMENT PRIMARY KEY,
    application VARCHAR(255),
    profile     VARCHAR(255),
    label       VARCHAR(255),
    prop_key    VARCHAR(255),
    prop_value  VARCHAR(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP(6),
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);