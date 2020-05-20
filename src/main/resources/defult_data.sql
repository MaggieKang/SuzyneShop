INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,[scope],authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove) VALUES 
('HNS-SHOP',NULL,'pass','read,write','password,refresh_token',NULL,NULL,3000,6000,NULL,'false')
;

INSERT INTO ACCOUNT (seq,id,name,password,email,isAccountNonExpired,isAccountNonLocked,isCredentialsNonExpired,isEnabled,regDate,regPerson,lastModDate,lastModPerson) VALUES 
 (1,'1006','IT','1234','westviewIT@gmail.com',0,0,0,1,getdate(),'9000',getdate(),'9000')
,(2,'9000','test9000','1234','TEST',0,0,0,1,getdate(),'9000',getdate(),'9000')
,(3,'9001','test9002','1234',NULL,0,0,0,0,getdate(),'9001',getdate(),'9001')
;

INSERT INTO AUTHORITY (id,authority_code) VALUES 
('1006','ADMIN')
,('1006','CARD')
,('1006','USER')
,('9000','ADMIN')
,('9000','USER')
;