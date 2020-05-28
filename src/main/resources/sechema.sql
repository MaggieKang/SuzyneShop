CREATE TABLE oauth_access_token (
    authentication_id varchar(256) NOT NULL,
    token_id varchar(256) NULL,
    token varbinary(max) NULL,
    user_name varchar(256) NULL,
    client_id varchar(256) NULL,
    authentication varbinary(max) NULL,
    refresh_token varchar(256) NULL,
    PRIMARY KEY (authentication_id)
);

CREATE TABLE oauth_approvals (
    userId varchar(256) NULL,
    clientId varchar(256) NULL,
    [scope] varchar(256) NULL,
    status varchar(10) NULL,
    expiresAt datetime NULL,
    lastModifiedAt datetime NULL
);

CREATE TABLE oauth_client_details (
    client_id varchar(256) NOT NULL,
    resource_ids varchar(256) NULL,
    client_secret varchar(256) NULL,
    [scope] varchar(256) NULL,
    authorized_grant_types varchar(256) NULL,
    web_server_redirect_uri varchar(256) NULL,
    authorities varchar(256) NULL,
    access_token_validity int NULL,
    refresh_token_validity int NULL,
    additional_information varchar(4096) NULL,
    autoapprove varchar(256) NULL,
    PRIMARY KEY (client_id)
);

CREATE TABLE oauth_client_token (
    token_id varchar(256) NULL,
    token varbinary(max) NULL,
    authentication_id varchar(256) NOT NULL,
    user_name varchar(256) NULL,
    client_id varchar(256) NULL,
    PRIMARY KEY (authentication_id)
);

CREATE TABLE oauth_code (
    code varchar(256) NULL,
    authentication varbinary(max) NULL
);

CREATE TABLE oauth_refresh_token (
    token_id varchar(256) NULL,
    token varbinary(max) NULL,
    authentication varbinary(max) NULL
);

-- used in tests that use MSSQL
CREATE TABLE EVENT (
    id INTEGER NOT NULL,
    name VARCHAR(256),
    description VARCHAR(4096),
    beginEnrollmentDateTime DATETIME,
    closeEnrollmentDateTime DATETIME,
    beginEventDateTime DATETIME,
    endEventDateTime DATETIME,
    location VARCHAR(256),
    basePrice DECIMAL(10,2),
    maxPrice DECIMAL(10,2),
    limitOfEnrollment VARCHAR(256),
    offline VARCHAR(1),
    free VARCHAR(1),
    eventStatus VARCHAR(256),
    regDate DATETIME,
    regPerson VARCHAR(256),
    lastModDate DATETIME,
    lastModPerson VARCHAR(256),
    PRIMARY KEY (id)
);