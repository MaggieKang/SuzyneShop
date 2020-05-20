INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,[scope],authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove) VALUES 
('HNS-SHOP',NULL,'pass','read,write','password,refresh_token',NULL,NULL,3000,6000,NULL,'false')
;

INSERT INTO ACCOUNT (seq,id,name,password,email,isAccountNonExpired,isAccountNonLocked,isCredentialsNonExpired,isEnabled,regDate,regPerson,lastModDate,lastModPerson) VALUES 
 (1,'1006','IT','1234','westviewIT@gmail.com',0,0,0,1,NULL,NULL,NULL,NULL)
,(2,'9000','test9000','1234','TEST',0,0,0,1,'2020-05-14 04:04:28.573','9000','9000-01-01 00:00:00.000','May 14 2020  4:04AM')
,(3,'9001','test9002','1234',NULL,0,0,0,0,'2020-05-19 22:24:59.313','9002','9002-01-01 00:00:00.000','May 19 2020 10:24PM')
;