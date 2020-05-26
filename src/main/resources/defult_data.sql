IF ObJECt_ID('[oauth_client_details]') IS NOT NULL DROP TABLE [oauth_client_details];

CREATE TABLE [oauth_client_details] (
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

INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,[scope],authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove) VALUES
('HNS-SHOP',NULL,'pass','read,write','password,refresh_token',NULL,NULL,3000,6000,NULL,'false');

INSERT INTO account (account_id,password,is_expired,is_locked,is_use,reg_date,reg_person,last_mod_date,last_mod_person) VALUES
 ('1006','1234',0,0,1,getdate(),'sysadmin',getdate(),'sysadmin')
,('9000','1234',0,0,1,getdate(),'sysadmin',getdate(),'sysadmin')
,('9001','1234',0,0,0,getdate(),'sysadmin',getdate(),'sysadmin');

INSERT INTO account_authority (account_id,auth_cd,reg_date,reg_person,last_mod_date,last_mod_person) VALUES
 ('1006','ADMIN',getdate(),'sysadmin',getdate(),'sysadmin')
,('1006','USER',getdate(),'sysadmin',getdate(),'sysadmin')
,('9000','ADMIN',getdate(),'sysadmin',getdate(),'sysadmin')
,('9000','USER',getdate(),'sysadmin',getdate(),'sysadmin')
,('9001','USER',getdate(),'sysadmin',getdate(),'sysadmin');

INSERT INTO authority (auth_cd,auth_desc,reg_date,reg_person,last_mod_date,last_mod_person) VALUES
 ('ADMIN','ADMIN',getdate(),'sysadmin',getdate(),'sysadmin')
,('USER','USER',getdate(),'sysadmin',getdate(),'sysadmin');

INSERT INTO item (item_id,upc,gal_code,prod_own_code,supp_code,prod_id,item_kr_nm,item_en_nm,item_size,item_type,item_type2,item_in_price,sale_price,regular_price,item_balance,tax_cd,sale_unit,deposit_cd,category_cd,is_use,reg_date,reg_person,last_mod_date,last_mod_person) VALUES
('DK0101004135KR0101001','11121','DK0101004','135','KR0101001','11121','이천쌀 햅쌀','RHEE CHUN RICE NEW CROP','40LB','07','07',33.00,49.99,49.99,-28891.00,'N','EA',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0101114027KR0101001','4134','DK0101114','027','KR0101001','4134','선물용 후지사과 박스','FUJI APPLES GIFT BOX','','19','19',1.27,29.99,29.99,-99593.33,'N','BOX',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0101151015KR0101001','44082','DK0101151','015','KR0101001','44082','신고배 박스','ASIAN PEAR BOX','','19','19',0.00,29.99,29.99,-556.00,'N','BOX',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0101151051KR0101001','444081','DK0101151','051','KR0101001','444081','한국산김제신고배 8과','KOREAN SINGO PEAR 8UNIT','','19','19',22.50,29.99,29.99,-2961.00,'N','BOX',0,NULL,0,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0101208014KR0101001','1189','DK0101208','014','KR0101001','1189','딤채 DOEA-184DNG ','DIMCHAE ','180L','36','36',1250.00,1799.00,1799.00,-2.00,'B','EA',0,NULL,0,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0101208022KR0101001','3199','DK0101208','022','KR0101001','3199','딤채 스탠딩고저스DEPA424TJS(418L)','DIMCHAE STANDING DEPA424TJS','418L','36','36',3350.00,3699.00,3699.00,-4.00,'G','EA',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0101226015KR0101001','78415','DK0101226','015','KR0101001','78415','선물용 고구마 박스','SWEET POTATO BOX','10LB','18','18',23.00,29.99,29.99,-180252.82,'N','BOX',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0101956001KR0101001','031146850105','DK0101956','001','KR0101001','031146850105','농심 육개장 사발면 박스','BOWL NOODLE SOUP HOT & SPICY BOX','86GX12','02','02',13.00,22.99,22.99,-2137.00,'N','BOX',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0101961001KR0101001','031146853113','DK0101961','001','KR0101001','031146853113','농심 신 사발면 박스','SHIN BOWL BOX','86GX12','02','02',13.00,22.99,22.99,-692.00,'N','BOX',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0101963001KR0101001','031146853441','DK0101963','001','KR0101001','031146853441','농심 김치사발면 박스','KIMCHI BOWL NOODLE SOUP BOX','86GX12','02','02',13.00,22.99,22.99,-1880.00,'N','BOX',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0102691090KR0101001','8801039253397','DK0102691','090','KR0101001','8801039253397','해표 고급유 세트 ( 6호)','GIFT SET','500ML * 3','02','02',9.33,18.99,18.99,-937.00,'N','BOX',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0104023001KR0101001','8801037019667','DK0104023','001','KR0101001','8801037019667','동서 맥심모카골드','MAXIM MOCHA GOLD MILD','12GX100','02','02',0.00,26.99,26.99,-9750.00,'N','PK',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0105033018KR0101001','819079011578','DK0105033','018','KR0101001','819079011578','정관장 홍삼원','RED GINSENG DRINK','30*50ML','02','02',25.00,49.99,49.99,-272.00,'G','BOX',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0105190109KR0101001','8807779049030','DK0105190','109','KR0101001','8807779049030','댕기머리 기골드프리미엄세트(L)','DENGIMORI  SET(L)','4*500ML,3*70ML','01','01',52.92,139.99,139.99,-36.00,'B','EA',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0105190131KR0101001','8807779084895','DK0105190','131','KR0101001','8807779084895','댕기머리 기골드프리미엄세트(L)','DENGIMORI  SET(L)','4*500ML,3*70ML','01','01',49.14,129.99,129.99,-36.00,'B','EA',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0105190134KR0101001','8807779087995','DK0105190','134','KR0101001','8807779087995','댕기머리 들애수 탈모방지 샴푸세트','DENGIMORI  SET','3*400ML','01','01',19.00,69.99,69.99,-36.00,'B','EA',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0105771031KR0101001','8809160439932','DK0105771','031','KR0101001','8809160439932','크린터치 비데','CLEAN TOUCH BIDET','ELONGATED','36','36',330.00,699.00,699.00,-4.00,'B','EA',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0106370007KR0101001','2011','DK0106370','007','KR0101001','2011','한국미 현미','BROWN RICE','15LB','07','07',12.50,27.99,27.99,-987.00,'N','PK',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK01081058KR0101001','8806151441363','DK0108','1058','KR0101001','8806151441363','딤채쿡 A060USDSH','IH PRESSURE RICE COOKER','6CUPS','36','36',479.00,599.00,599.00,-67.00,'B','EA',101,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0108108013KR0101001','8807779085137','DK0108108','013','KR0101001','8807779085137','수페온 바디케어 3종세트','BODY CARE 3SET','','01','01',9.00,39.99,39.99,-2.00,'B','EA',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK01081097KR0101001','8809469880770','DK0108','1097','KR0101001','8809469880770','쿠첸 IR명품철정미작 10컵','DIGITAL PRESSURE COOKER','CJH-PH1000RCWUS','36','36',580.00,849.00,849.00,-69.00,'B','EA',101,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK01081098KR0101001','8809469880756','DK0108','1098','KR0101001','8809469880756','쿠첸 IR명품철정미작 6컵','DIGITAL PRESSURE COOKER','CJH-PH0610RCWUS','36','36',550.00,799.00,799.00,-68.00,'B','EA',101,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
,('DK0108977KR0101001','450947','DK0108','977','KR0101001','450947','맥슨 프리미엄 와이드그릴','MAXUN PREMIUM WIDE GRILL ','','13','13',90.00,199.99,199.99,-84.00,'B','EA',0,NULL,1,'2020-05-25 21:09:29.367','sysadmin','2020-05-25 21:09:29.367','sysadmin')
;

INSERT INTO store (store_id, store_nm, address, city, province, postal_cd, store_open_time, telephone, is_use, reg_date, reg_person, last_mod_date, last_mod_person) VALUES('st001', 'HANNAM Supermarket, Burnaby', '106-4501 North Rd', 'Burnaby', 'British Columbia', 'V3N4R7', '8:00 AM - 10:00 PM', '604-420-8856', 1, getDate(), 'sysadmin', getDate(), 'sysadmin');
INSERT INTO store (store_id, store_nm, address, city, province, postal_cd, store_open_time, telephone, is_use, reg_date, reg_person, last_mod_date, last_mod_person) VALUES('st002', 'HANNAM Supermarket, Surrey', '#1-15357 104 Ave.', 'Surrey', 'British Columbia', 'V3R1N5', '8:00 AM - 10:00 PM', '604-580-3433', 1, getDate(), 'sysadmin', getDate(), 'sysadmin');
INSERT INTO store (store_id, store_nm, address, city, province, postal_cd, store_open_time, telephone, is_use, reg_date, reg_person, last_mod_date, last_mod_person) VALUES('st003', 'HANNAM Supermarket, Robson', '#202 1323 Robson St.', 'Vancouver', 'British Columbia', 'V6E2B1', '9:00 AM - 11:00 PM', '604-974-9684', 1, getDate(), 'sysadmin', getDate(), 'sysadmin');
INSERT INTO store (store_id, store_nm, address, city, province, postal_cd, store_open_time, telephone, is_use, reg_date, reg_person, last_mod_date, last_mod_person) VALUES('st004', 'HANNAM Supermarket, Langley', '6350 196 St.', 'Langley', 'British Columbia', 'V2Y1J2', '8:30 AM - 10:00 PM', '604-539-6267', 1, getDate(), 'sysadmin', getDate(), 'sysadmin');

INSERT INTO category (category_cd, category_nm, category_desc, parent_category_cd, reg_date, reg_person, last_mod_date, last_mod_person) VALUES
 ('L000','ROOT','ROOT', '', getDate(),'sysadmin', getDate(),'sysadmin')
,('L101','건식품','건식품', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L102','곡류','곡류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L103','과자류','과자류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L104','김류','김류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L105','김치류','김치류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L106','껌류','껌류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L107','담배','담배', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L108','떡','떡', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L109','라면류','라면류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L110','만두류','만두류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L111','면류','면류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L112','빙과류','빙과류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L113','빵류','빵류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L114','상품권','상품권', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L115','쌀','쌀', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L116','양념소스류','양념소스류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L117','오일류','오일류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L118','유제품','유제품', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L119','음료수','음료수', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L120','일반식품','일반식품', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L121','장류','장류', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L122','전화카드','전화카드', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L123','즉석식품','즉석식품', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L124','커피/차','커피/차', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L125','통조림','통조림', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L126','파우더','파우더', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L127','하드웨어','하드웨어', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L128','한남','한남', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L129','헬스 앤 뷰티','헬스 앤 뷰티', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L130','Dr. Bee','Dr. Bee', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L131','G/M','G/M', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L132','생선','생선', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L133','반찬','반찬', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L134','정육','정육', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L135','야채/과일','야채/과일', 'L000', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20101','마른나물','마른나물','L101', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20102','마른버섯','마른버섯','L101', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20199','건식품 기타','건식품 기타','L101', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20201','잡곡','잡곡','L102', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20299','곡류 기타','곡류 기타','L102', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20301','(한국)과자','(한국)과자','L103', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20302','(일본)과자','(일본)과자','L103', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20303','(중국)과자','(중국)과자','L103', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20399','과자류 기타','과자류 기타','L103', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20401','김밥용 김','김밥용 김','L104', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20402','도시락 김','도시락 김','L104', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20403','전장 구운 조미김','전장 구운 조미김','L104', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20499','김류 기타','김류 기타','L104', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20501','김치','김치','L105', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20599','김치류 기타','김치류 기타','L105', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20601','(한국)껌','(한국)껌','L106', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20602','(서양)껌','(서양)껌','L106', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20603','(일본)껌','(일본)껌','L106', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20699','껌류 기타','껌류 기타','L106', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20701','담배','담배','L107', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20799','담배 기타','담배 기타','L107', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20801','떡국떡','떡국떡','L108', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20802','일반떡','일반떡','L108', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20899','떡 기타','떡 기타','L108', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20901','라면 낱개','라면 낱개','L109', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20902','라면 멀티','라면 멀티','L109', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20903','라면 박스','라면 박스','L109', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20904','컵라면','컵라면','L109', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20905','컵라면 박스','컵라면 박스','L109', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20999','라면류 기타','라면류 기타','L109', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21001','(냉동)만두','(냉동)만두','L110', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21002','JC만두','JC만두','L110', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21099','만두류 기타','만두류 기타','L110', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21101','건면','건면','L111', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21102','(냉동)면','(냉동)면','L111', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21103','(냉장)면','(냉장)면','L111', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21104','(일본상온)면','(일본상온)면','L111', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21105','(냉동)냉면','(냉동)냉면','L111', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21106','건냉면','건냉면','L111', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21107','(냉동)우동면','(냉동)우동면','L111', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21108','(상온)우동면','(상온)우동면','L111', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21109','당면','당면','L111', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21110','소바면','소바면','L111', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21199','면류 기타','면류 기타','L111', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21201','(한국)아이스크림 낱개','(한국)아이스크림 낱개','L112', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21202','(한국)아이스크림 박스','(한국)아이스크림 박스','L112', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21203','(서양)아이스크림','(서양)아이스크림','L112', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21299','빙과류 기타','빙과류 기타','L112', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21301','(냉동)빵','(냉동)빵','L113', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21302','식빵','식빵','L113', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21303','카스텔라','카스텔라','L113', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21399','빵류 기타','빵류 기타','L113', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21401','한남상품권','한남상품권','L114', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21499','상품권 기타','상품권 기타','L114', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21501','쌀 10LB 미만','쌀 10LB 미만','L115', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21502','쌀 일반','쌀 일반','L115', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21503','쌀 기타 20LB 이상','쌀 기타 20LB 이상','L115', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21599','쌀 기타','쌀 기타','L115', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21601','다시다','다시다','L116', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21602','고기소스','고기소스','L116', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21603','양념소스','양념소스','L116', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21604','젓갈소스','젓갈소스','L116', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21605','(한국)식초','(한국)식초','L116', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21606','(일본)식초','(일본)식초','L116', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21607','맛술','맛술','L116', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21608','물엿','물엿','L116', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21699','양념소스류 기타','양념소스류 기타','L116', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21701','식용유','식용유','L117', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21702','참기름','참기름','L117', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21799','오일류 기타','오일류 기타','L117', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21801','우유','우유','L118', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21802','요구르트','요구르트','L118', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21803','두유','두유','L118', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21804','두유 박스','두유 박스','L118', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21899','유제품 기타','유제품 기타','L118', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21901','주스','주스','L119', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21902','탄산음료수','탄산음료수','L119', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21903','드링크','드링크','L119', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21904','생수','생수','L119', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21905','선물용 음료수','선물용 음료수','L119', getDate(),'sysadmin', getDate(),'sysadmin')
,('L21999','음료수 기타','음료수 기타','L119', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22001','고추가루','고추가루','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22002','소금','소금','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22003','설탕','설탕','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22004','단무지','단무지','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22005','두부','두부','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22006','계란','계란','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22007','미역','미역','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22008','다시마','다시마','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22009','(한국)카레','(한국)카레','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22010','(일본)카레','(일본)카레','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22011','(냉동)맛살','(냉동)맛살','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22012','(냉동)쏘세지','(냉동)쏘세지','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22013','(냉동)어묵','(냉동)어묵','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22014','(냉동)유부','(냉동)유부','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22015','(일본)유부','(일본)유부','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22016','죽제품','죽제품','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22017','꿀제품','꿀제품','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22018','깐밤','깐밤','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22019','나또','나또','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22020','(서양)건조식품','(서양)건조식품','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22021','(서양/냉동)그로서리','(서양/냉동)그로서리','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22022','(일본)그로서리','(일본)그로서리','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22023','(일본/냉장)그로서리','(일본/냉장)그로서리','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22099','일반식품 기타','일반식품 기타','L120', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22101','(한국)간장','(한국)간장','L121', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22102','(일본)간장','(일본)간장','L121', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22103','고추장','고추장','L121', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22104','고추장 벌크','고추장 벌크','L121', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22105','된장','된장','L121', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22106','쌈장','쌈장','L121', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22107','미소','미소','L121', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22108','즉석미소','즉석미소','L121', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22199','장류 기타','장류 기타','L121', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22201','전화카드','전화카드','L122', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22299','전화카드 기타','전화카드 기타','L122', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22301','햇반','햇반','L123', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22302','즉석스프','즉석스프','L123', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22303','(냉동)즉석식품','(냉동)즉석식품','L123', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22304','(상온)즉석국','(상온)즉석국','L123', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22399','즉석식품 기타','즉석식품 기타','L123', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22401','인스턴트 커피믹스','인스턴트 커피믹스','L124', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22402','녹차','녹차','L124', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22403','보리차','보리차','L124', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22498','커피 기타','커피 기타','L124', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22499','차 기타','차 기타','L124', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22501','스팸','스팸','L125', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22502','참치캔','참치캔','L125', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22503','(한국)통조림','(한국)통조림','L125', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22504','(서양)통조림','(서양)통조림','L125', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22505','(일본)통조림','(일본)통조림','L125', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22599','통조림 기타','통조림 기타','L125', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22601','밀가루','밀가루','L126', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22699','파우더 기타','파우더 기타','L126', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22701','딤채','딤채','L127', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22702','쿠쿠','쿠쿠','L127', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22703','노비타','노비타','L127', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22704','쿠첸','쿠첸','L127', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22705','후지트로닉','후지트로닉','L127', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22706','보온도시락','보온도시락','L127', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22707','보온병','보온병','L127', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22708','도란스','도란스','L127', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22709','건전지','건전지','L127', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22710','비데','비데','L127', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22799','하드웨어 기타','하드웨어 기타','L127', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22801','Ticket','Ticket','L128', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22802','이벤트','이벤트','L128', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22803','스페샬','스페샬','L128', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22804','스페샬2','스페샬2','L128', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22805','Package','Package','L128', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22806','사은품','사은품','L128', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22896','한남BBY','한남BBY','L128', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22897','한남SRY','한남SRY','L128', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22898','한남HM','한남HM','L128', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22899','한남 기타','한남 기타','L128', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22901','비타민','비타민','L129', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22902','생리대','생리대','L129', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22903','약품','약품','L129', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22904','염색약','염색약','L129', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22905','마스크팩','마스크팩','L129', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22906','홍삼제품','홍삼제품','L129', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22907','건강식품','건강식품','L129', getDate(),'sysadmin', getDate(),'sysadmin')
,('L22999','헬스 앤 뷰티 기타','헬스 앤 뷰티 기타','L129', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23001','Dr. Bee','Dr. Bee','L130', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23101','가스레인지','가스레인지','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23102','고무장갑','고무장갑','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23103','나무젓가락','나무젓가락','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23104','냄비','냄비','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23105','락앤락','락앤락','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23106','반찬통','반찬통','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23107','부탄가스','부탄가스','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23108','불판','불판','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23109','일회용 접시','일회용 접시','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23110','일회용 컵','일회용 컵','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23111','후라이팬','후라이팬','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23112','세제류','세제류','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23113','치약','치약','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23114','칫솔','칫솔','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23115','휴지','휴지','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23116','낫','낫','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23117','문구류','문구류','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23118','슬리퍼','슬리퍼','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23119','우산','우산','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23120','좀약','좀약','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23121','호미','호미','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23122','BBQ용품','BBQ용품','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23123','MAP','MAP','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23199','G/M 기타','G/M 기타','L131', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23201','생선','생선','L132', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23202','어패','어패','L132', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23203','건어물','건어물','L132', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23204','해조','해조','L132', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23205','알','알','L132', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23206','반건조자반','반건조자반','L132', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23207','절단','절단','L132', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23208','토막','토막','L132', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23209','횟감','횟감','L132', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23210','매운탕Set','매운탕Set','L132', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23299','생선 기타','생선 기타','L132', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23301','김치','김치','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23302','전','전','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23303','김밥','김밥','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23304','스시','스시','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23305','양념고기','양념고기','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23306','찌게Set','찌게Set','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23307','전골Set','전골Set','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23308','탕Set','탕Set','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23309','볶음반찬','볶음반찬','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23310','장아치','장아치','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23311','무침','무침','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23312','나물','나물','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23313','젓갈','젓갈','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23314','떡','떡','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23315','밥','밥','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23316','죽','죽','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23317','묵','묵','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23318','떡볶이','떡볶이','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23319','튀김','튀김','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23320','순대','순대','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23321','잡채','잡채','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23322','족발','족발','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23323','생선가스','생선가스','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23324','돈가스','돈가스','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23325','음료','음료','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23326','도시락','도시락','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23327','절인배추','절인배추','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23399','반찬 기타','반찬 기타','L133', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23401','돼지고기','돼지고기','L134', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23402','소고기','소고기','L134', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23403','뼈고기','뼈고기','L134', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23404','닭고기','닭고기','L134', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23499','정육 기타','정육 기타','L134', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23501','(한국)과일','(한국)과일','L135', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23502','(한국)배','(한국)배','L135', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23503','(한국)배추','(한국)배추','L135', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23504','(한국)무우','(한국)무우','L135', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23505','(한국)야채','(한국)야채','L135', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23506','(일본)야채','(일본)야채','L135', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23507','(서양)과일','(서양)과일','L135', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23508','(서양)야채','(서양)야채','L135', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23509','(중국)야채','(중국)야채','L135', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23598','야채 기타','야채 기타','L135', getDate(),'sysadmin', getDate(),'sysadmin')
,('L23599','과일 기타','과일 기타','L135', getDate(),'sysadmin', getDate(),'sysadmin')
,('L20108','기타','기타','L101', getDate(),'sysadmin', getDate(),'sysadmin');