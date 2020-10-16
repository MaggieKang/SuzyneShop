
/* Drop Tables */

IF ObJECt_ID('[account_address]') IS NOT NULL DROP TABLE [account_address];
IF ObJECt_ID('[cart]') IS NOT NULL DROP TABLE [cart];
IF ObJECt_ID('[customer]') IS NOT NULL DROP TABLE [customer];
IF ObJECt_ID('[favourite]') IS NOT NULL DROP TABLE [favourite];
IF ObJECt_ID('[orders_detail]') IS NOT NULL DROP TABLE [orders_detail];
IF ObJECt_ID('[orders]') IS NOT NULL DROP TABLE [orders];
IF ObJECt_ID('[payment]') IS NOT NULL DROP TABLE [payment];
IF ObJECt_ID('[account]') IS NOT NULL DROP TABLE [account];
IF ObJECt_ID('[account_authority]') IS NOT NULL DROP TABLE [account_authority];
IF ObJECt_ID('[account_service_api]') IS NOT NULL DROP TABLE [account_service_api];
IF ObJECt_ID('[admin_notice]') IS NOT NULL DROP TABLE [admin_notice];
IF ObJECt_ID('[admin_account]') IS NOT NULL DROP TABLE [admin_account];
IF ObJECt_ID('[authority]') IS NOT NULL DROP TABLE [authority];
IF ObJECt_ID('[card_payment_result]') IS NOT NULL DROP TABLE [card_payment_result];
IF ObJECt_ID('[item_file]') IS NOT NULL DROP TABLE [item_file];
IF ObJECt_ID('[item]') IS NOT NULL DROP TABLE [item];
IF ObJECt_ID('[category]') IS NOT NULL DROP TABLE [category];
IF ObJECt_ID('[city]') IS NOT NULL DROP TABLE [city];
IF ObJECt_ID('[cmn_code_detail]') IS NOT NULL DROP TABLE [cmn_code_detail];
IF ObJECt_ID('[cmn_code]') IS NOT NULL DROP TABLE [cmn_code];
IF ObJECt_ID('[cmn_cls_code]') IS NOT NULL DROP TABLE [cmn_cls_code];
IF ObJECt_ID('[cmn_file]') IS NOT NULL DROP TABLE [cmn_file];
IF ObJECt_ID('[country]') IS NOT NULL DROP TABLE [country];
IF ObJECt_ID('[invoice_detail]') IS NOT NULL DROP TABLE [invoice_detail];
IF ObJECt_ID('[invoice]') IS NOT NULL DROP TABLE [invoice];
IF ObJECt_ID('[notice]') IS NOT NULL DROP TABLE [notice];
IF ObJECt_ID('[pickup_timeslot]') IS NOT NULL DROP TABLE [pickup_timeslot];
IF ObJECt_ID('[pickup_timeslot_default]') IS NOT NULL DROP TABLE [pickup_timeslot_default];
IF ObJECt_ID('[province]') IS NOT NULL DROP TABLE [province];
IF ObJECt_ID('[service_api]') IS NOT NULL DROP TABLE [service_api];
IF ObJECt_ID('[store]') IS NOT NULL DROP TABLE [store];




/* Create Tables */

-- 계정
CREATE TABLE [account]
(
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL IDENTITY ,
	-- 계정이메일
	[account_email] varchar(200) NOT NULL UNIQUE,
	-- 비밀번호 : 비밀번호
	[password] varchar(500) NOT NULL,
	-- 만료여부 : 만료 여부
	-- is Expired
	[is_expired] bit NOT NULL,
	-- 잠김여부 : 잠김여부(입력 또는 일시 잠김 설정)
	-- (locked:1, unlocked:0)
	[is_locked] bit NOT NULL,
	-- 사용여부
	[is_use] bit NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_no])
);


-- 계정주소
CREATE TABLE [account_address]
(
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL,
	-- 순번
	[seq] int NOT NULL IDENTITY ,
	-- 주소
	[address] varchar(100),
	-- 시
	[city] varchar(20),
	-- 주
	[province] varchar(20),
	-- 국가
	[country] varchar(20),
	-- 우편번호
	[postal_cd] varchar(10) NOT NULL,
	-- 기본주소여부
	[is_default_address] bit DEFAULT '0',
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_no], [seq])
);


-- 계정권한
CREATE TABLE [account_authority]
(
	-- 권한코드 : authority code
	[auth_cd] varchar(20) NOT NULL,
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([auth_cd], [account_no])
);


-- 계정서비스API
CREATE TABLE [account_service_api]
(
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL,
	-- API번호
	[api_no] int NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_no], [api_no])
);


-- 관리자계정
CREATE TABLE [admin_account]
(
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL IDENTITY ,
	-- 계정ID
	[account_id] varchar(20) NOT NULL UNIQUE,
	-- 비밀번호 : 비밀번호
	[password] varchar(500) NOT NULL,
	-- 만료여부 : 만료 여부
	-- is Expired
	[is_expired] bit NOT NULL,
	-- 잠김여부 : 잠김여부(입력 또는 일시 잠김 설정)
	-- (locked:1, unlocked:0)
	[is_locked] bit NOT NULL,
	-- 사용여부
	[is_use] bit,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_no])
);


-- 관리자알림
CREATE TABLE [admin_notice]
(
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL,
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 공지번호
	[notice_no] int NOT NULL,
	-- 읽음여부 : true:1/false:0
	[is_read] bit DEFAULT '0' NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_no], [store_id], [notice_no])
);


-- 권한
CREATE TABLE [authority]
(
	-- 권한코드 : authority code
	[auth_cd] varchar(20) NOT NULL,
	-- 권한설명
	[auth_desc] varchar(500),
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([auth_cd])
);


-- 카드결제결과
CREATE TABLE [card_payment_result]
(
	-- 요청UUID : 요청UUID
	[request_uuid] varchar(50) NOT NULL,
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL,
	-- 주문ID
	[order_id] varchar(30) NOT NULL,
	-- 인보이스ID
	[invoice_id] varchar(25) NOT NULL,
	-- ssl_transaction_type
	[ssl_transaction_type] varchar(20) NOT NULL,
	-- ssl_txn_id
	[ssl_txn_id] varchar(60),
	-- ssl_txn_time
	[ssl_txn_time] varchar(30),
	-- ssl_card_number
	[ssl_card_number] varchar(25),
	-- ssl_card_type
	[ssl_card_type] varchar(25),
	-- ssl_amount
	[ssl_amount] varchar(20),
	-- ssl_result_message
	[ssl_result_message] varchar(200),
	-- ssl_approval_code
	[ssl_approval_code] varchar(50),
	-- ssl_result
	[ssl_result] varchar(50),
	-- error_code
	[error_code] varchar(50),
	-- error_name
	[error_name] varchar(200),
	-- error_message
	[error_message] varchar(500),
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([request_uuid])
);


-- 장바구니
CREATE TABLE [cart]
(
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL,
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 상품ID
	[item_id] varchar(24) NOT NULL,
	-- 상품판매종류코드 : REGULAR1:일반가격, PROMO01:홍보가격(단), PROMO02:홍보가격(묶), MEMBER01:회원제가격(단), MEMBER02:회원제가격(묶)
	[item_sales_type_cd] varchar(10) NOT NULL,
	-- 상품수량 : item quantity
	[item_qty] int DEFAULT 0 NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_no], [store_id], [item_id], [item_sales_type_cd])
);


-- 카테고리
CREATE TABLE [category]
(
	-- 분류코드
	[category_cd] varchar(10) NOT NULL,
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 분류명
	[category_nm] varchar(45),
	-- 분류설명
	[category_desc] varchar(250),
	-- 분류순서
	[category_sqnc] int DEFAULT 0 NOT NULL,
	-- 부모분류코드
	[parent_category_cd] varchar(10),
	-- 부모매장ID
	[parent_store_id] varchar(10),
	-- 사용여부
	[is_use] bit DEFAULT '1',
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([category_cd], [store_id])
);


-- city
CREATE TABLE [city]
(
	-- 도시코드
	[city_cd] varchar(10) NOT NULL,
	-- 도시이름
	[city_nm] varchar(100) NOT NULL,
	-- province_cd
	[province_cd] varchar(10) NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([city_cd])
);


-- 공통분류코드 : common classification code
-- 공통 분류 코드
CREATE TABLE [cmn_cls_code]
(
	-- 분류코드
	[cls_cd] char(3) NOT NULL,
	-- 분류코드명
	[cls_cd_nm] varchar(60) NOT NULL,
	-- 분류코드설명
	[cls_cd_dc] varchar(200),
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([cls_cd])
);


-- 공통코드 : common code
CREATE TABLE [cmn_code]
(
	-- 코드ID
	[cd_id] varchar(10) NOT NULL,
	-- 코드ID명
	[cd_id_nm] varchar(60) NOT NULL,
	-- 코드ID설명
	[cd_id_dc] varchar(20),
	-- 사용여부
	[is_use] bit DEFAULT '1' NOT NULL,
	-- 분류코드
	[cls_cd] char(3) NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([cd_id])
);


-- 공통코드상세 : common code detail
CREATE TABLE [cmn_code_detail]
(
	-- 코드ID
	[cd_id] varchar(10) NOT NULL,
	-- 코드
	[cd] varchar(10) NOT NULL,
	-- 코드이름
	[cd_nm] varchar(60) NOT NULL,
	-- 코드설명
	[cd_dc] varchar(20),
	-- 사용여부
	[is_use] bit DEFAULT '1' NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([cd_id], [cd])
);


-- 공통 파일
CREATE TABLE [cmn_file]
(
	-- 공통파일ID
	[cmn_file_id] varchar(100) NOT NULL,
	-- 공통파일유형코드
	[cmn_file_type_cd] varchar(10) NOT NULL,
	-- 공통파일원본명
	[cmn_file_original_nm] varchar(200) NOT NULL,
	-- 공통파일명
	[cmn_file_nm] varchar(500) NOT NULL,
	-- 공통파일경로
	[cmn_file_path] varchar(100) NOT NULL,
	-- 공통파일URL
	[cmn_file_url] varchar(200) NOT NULL,
	-- 공통파일사이즈
	[cmn_file_size] int NOT NULL,
	-- 사용여부
	[is_use] bit DEFAULT '1' NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([cmn_file_id])
);


-- country
CREATE TABLE [country]
(
	-- 국가코드
	[country_cd] varchar(10) NOT NULL,
	-- 국가이름
	[country_nm] varchar(100) NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([country_cd])
);


-- 회원
CREATE TABLE [customer]
(
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL UNIQUE,
	-- 이름
	[first_name] varchar(100),
	-- 성
	[last_name] varchar(100),
	-- 고객이메일 : 고객이메일
	[customer_email] varchar(200),
	-- 고객전화번호 : customer phone number
	[customer_phone_number] varchar(20),
	-- 내선번호
	[extension_nember] varchar(10),
	-- 고객성별
	[customer_gender] varchar(10),
	-- 고객한글이름
	[customer_kr_nm] varchar(150),
	-- 고객영문이름
	[customer_en_nm] varchar(150),
	-- 고객언어코드
	[customer_lang_cd] varchar(4),
	-- 맴버쉽ID
	[membership_id] varchar(12),
	-- 맴버쉽여부 : 맴버쉽여부
	[is_membership] bit DEFAULT '0' NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_no])
);


-- 즐겨찾기상품
CREATE TABLE [favourite]
(
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL,
	-- 상품ID
	[item_id] varchar(24) NOT NULL UNIQUE,
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_no], [item_id], [store_id])
);


-- 인보이스
CREATE TABLE [invoice]
(
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 인보이스ID
	[invoice_id] varchar(25) NOT NULL,
	-- 인보이스인덱스번호
	[invoice_index_no] int NOT NULL,
	-- 인보이스일시
	[invoice_date] datetime NOT NULL,
	-- 인보이스상태코드
	[invoice_status_cd] varchar(10) NOT NULL,
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL,
	-- 총지불금액 : Total Amount + Total GST + Total PST + Total ECO + Total Deposit
	[total_pay_amount] decimal(10,2) NOT NULL,
	-- 총금액 : (item_qty * sales_bundle_price) or (pickup_qty * sales_bundle_price)
	[total_amount] decimal(10,2) NOT NULL,
	-- 총GST수수료
	[total_gst_fee] decimal(10,2) NOT NULL,
	-- 총PST수수료
	[total_pst_fee] decimal(10,2) NOT NULL,
	-- 총ECO수수료
	[total_eco_fee] decimal(10,2) NOT NULL,
	-- 총Deposit수수료
	[total_deposit_fee] decimal(10,2) NOT NULL,
	-- 배달주소
	[delivery_address] varchar(200) NOT NULL,
	-- 청구서주소
	[billing_address] varchar(200) NOT NULL,
	-- 판매회사
	[sold_by] varchar(100) NOT NULL,
	-- 주문ID
	[order_id] varchar(30),
	-- 주문일시
	[order_date] datetime,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([store_id], [invoice_id])
);


-- 인보이스상세
CREATE TABLE [invoice_detail]
(
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 인보이스ID
	[invoice_id] varchar(25) NOT NULL,
	-- 상품ID
	[item_id] varchar(24) NOT NULL,
	-- 순번
	[seq] int NOT NULL,
	-- 상품판매종류코드 : REGULAR1:일반가격, PROMO01:홍보가격(단), PROMO02:홍보가격(묶), MEMBER01:회원제가격(단), MEMBER02:회원제가격(묶)
	[item_sales_type_cd] varchar(10) NOT NULL,
	-- 바코드ID
	[upc] varchar(20) NOT NULL,
	-- 상품영문이름
	[item_en_nm] varchar(150) NOT NULL,
	-- 상품한글이름
	[item_kr_nm] varchar(150) NOT NULL,
	-- 상품세금코드 : 세금유형 - GST, BOTH(GST+PST)
	-- 
	[item_tax_cd] nchar(1),
	-- 상품Deposit코드 : "값이 있을 경우 다음 Table 참조 Bottle Deposit / Ecofee 추가
	-- 100 보다 크면 Select * FROM [dbgal].[dbo].[mfProdEco] WHERE ReturnType ='ReturnType' 
	-- 100 보다 작으면 Select * FROM [dbgal].[dbo].[tblEncorp] WHERE ReturnType ='ReturnType'"
	[item_deposit_cd] varchar(4),
	-- 상품ECO코드
	[item_eco_cd] varchar(4),
	-- 상품규격
	[item_size] nvarchar(30) NOT NULL,
	-- 판매단위 : 판매 단위 - EA, PK, LB
	-- 
	[sales_unit] nvarchar(4) NOT NULL,
	-- 판매수량
	[sales_qty] int NOT NULL,
	-- 판매묶음총계
	[sales_bundle_amount] decimal(10,2) NOT NULL,
	-- 판매묶음GST수수료
	[sales_bundle_gst_fee] decimal(10,2) NOT NULL,
	-- 판매묶음PST수수료
	[sales_bundle_pst_fee] decimal(10,2) NOT NULL,
	-- 판매묶음Deposit수수료
	[sales_bundle_deposit_fee] decimal(10,2) NOT NULL,
	-- 판매묶음ECO수수료
	[sales_bundle_eco_fee] decimal(10,2) NOT NULL,
	-- 판매묶음입고가격
	[sales_bundle_receiving_price] decimal(10,2) NOT NULL,
	-- 판매묶음정규가격
	[sales_bundle_regular_price] decimal(10,2) NOT NULL,
	-- 판매묶음개수
	[sales_bundle_qty] int NOT NULL,
	-- 판매묶음가격
	[sales_bundle_price] decimal(10,2) NOT NULL,
	-- 판매묶음할인율
	[sales_bundle_discount_rate] int NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([store_id], [invoice_id], [item_id], [seq])
);


-- 상품
CREATE TABLE [item]
(
	-- 상품ID
	[item_id] varchar(24) NOT NULL,
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 바코드ID
	[upc] varchar(20),
	-- 상품한글이름
	[item_kr_nm] varchar(150),
	-- 상품영문이름
	[item_en_nm] varchar(150),
	-- 입고가격
	[receiving_price] decimal(10,2),
	-- 정규가격
	[regular_price] decimal(10,2) NOT NULL,
	-- 상품세금코드 : 세금유형 - GST, BOTH(GST+PST)
	-- 
	[item_tax_cd] nchar(1),
	-- 상품Deposit코드 : "값이 있을 경우 다음 Table 참조 Bottle Deposit / Ecofee 추가
	-- 100 보다 크면 Select * FROM [dbgal].[dbo].[mfProdEco] WHERE ReturnType ='ReturnType' 
	-- 100 보다 작으면 Select * FROM [dbgal].[dbo].[tblEncorp] WHERE ReturnType ='ReturnType'"
	[item_deposit_cd] varchar(4),
	-- 상품ECO코드
	[item_eco_cd] varchar(4),
	-- 상품규격
	[item_size] nvarchar(30),
	-- 상품무게
	[item_weight] decimal(10,4),
	-- 상품무게단위
	[item_weight_unit] varchar(4),
	-- 상품브렌드
	[item_brand] varchar(20),
	-- 분류코드
	[category_cd] varchar(10),
	-- 분류매장ID
	[category_store_id] varchar(10),
	-- 판매단위 : 판매 단위 - EA, PK, LB
	-- 
	[sales_unit] nvarchar(4),
	-- 프로모션묶음개수
	[promotion_bundle_qty] int DEFAULT 0 NOT NULL,
	-- 프로모션시작일시
	[promotion_start_date] datetime,
	-- 프로모션종료일시
	[promotion_end_date] datetime,
	-- 프로모션가격
	[promotion_price] decimal(10,2),
	-- 회원제한수량
	[member_limit_qty] int DEFAULT 0 NOT NULL,
	-- 회원묶음수량
	[member_bundle_qty] int DEFAULT 0 NOT NULL,
	-- 회원시작일시
	[member_start_date] datetime,
	-- 회원종료일시
	[member_end_date] datetime,
	-- 회원가격
	[member_price] decimal(10,2),
	-- 포스Key1
	[gal_code] varchar(9),
	-- 포스Key2
	[prod_own_code] varchar(4),
	-- 포스Key3
	[supp_code] varchar(9),
	-- 포스prodId
	[prod_id] nvarchar(15),
	-- 상품유형 : tblCategory1 포스 테이블 참조
	[item_type] nvarchar(2),
	-- 상품유형2 : 상품 Type - "08" 베이커리 6개 이상 구매시 Non Tax
	-- 
	[item_type2] nvarchar(2),
	-- 이벤트태그
	[event_tag] varchar(10),
	-- 상품설명
	[item_description] varchar(max),
	-- 설명출력여부
	[is_description_display] bit DEFAULT '1' NOT NULL,
	-- 사용여부
	[is_use] bit DEFAULT '1' NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([item_id], [store_id])
);


-- 상품파일
CREATE TABLE [item_file]
(
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 상품ID
	[item_id] varchar(24) NOT NULL,
	-- 순번
	[seq] int NOT NULL,
	-- 공통파일ID
	[cmn_file_id] varchar(100) NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([store_id], [item_id], [seq])
);


-- 공지사항
CREATE TABLE [notice]
(
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 공지번호
	[notice_no] int NOT NULL IDENTITY ,
	-- 공지제목
	[notice_title] varchar(100) NOT NULL,
	-- 공지내용
	[notice_content] nvarchar(max),
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([store_id], [notice_no])
);


-- 주문
CREATE TABLE [orders]
(
	-- 주문ID
	[order_id] varchar(30) NOT NULL,
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 주문일시
	[order_date] datetime NOT NULL,
	-- 고객연락번호
	[customer_contact_number] varchar(20) NOT NULL,
	-- 총금액 : (item_qty * sales_bundle_price) or (pickup_qty * sales_bundle_price)
	[total_amount] decimal(10,2) DEFAULT 0,
	-- 총GST수수료
	[total_gst_fee] decimal(10,2) DEFAULT 0,
	-- 총PST수수료
	[total_pst_fee] decimal(10,2) DEFAULT 0,
	-- 총ECO수수료
	[total_eco_fee] decimal(10,2) DEFAULT 0,
	-- 총Deposit수수료
	[total_deposit_fee] decimal(10,2) DEFAULT 0,
	-- 주문상태코드
	[order_status_cd] varchar(10) NOT NULL,
	-- 슬롯날짜
	[slot_dt] date,
	-- 슬롯시간
	[slot_time] time,
	-- 인보이스ID
	[invoice_id] varchar(25),
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([order_id], [store_id])
);


-- 주문상세
CREATE TABLE [orders_detail]
(
	-- 주문ID
	[order_id] varchar(30) NOT NULL,
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 상품ID
	[item_id] varchar(24) NOT NULL,
	-- 상품판매종류코드 : REGULAR1:일반가격, PROMO01:홍보가격(단), PROMO02:홍보가격(묶), MEMBER01:회원제가격(단), MEMBER02:회원제가격(묶)
	[item_sales_type_cd] varchar(10) NOT NULL,
	-- 상품판매상태코드
	[item_sales_status_cd] varchar(10) NOT NULL,
	-- 상품세금코드 : 세금유형 - GST, BOTH(GST+PST)
	-- 
	[item_tax_cd] nchar(1) NOT NULL,
	-- 상품Deposit코드 : "값이 있을 경우 다음 Table 참조 Bottle Deposit / Ecofee 추가
	-- 100 보다 크면 Select * FROM [dbgal].[dbo].[mfProdEco] WHERE ReturnType ='ReturnType' 
	-- 100 보다 작으면 Select * FROM [dbgal].[dbo].[tblEncorp] WHERE ReturnType ='ReturnType'"
	[item_deposit_cd] varchar(4),
	-- 상품ECO코드
	[item_eco_cd] varchar(4),
	-- 주문수량
	[order_qty] int DEFAULT 0 NOT NULL,
	-- 집품수량
	[pickup_qty] int DEFAULT 0 NOT NULL,
	-- 판매묶음총계
	[sales_bundle_amount] decimal(10,2) NOT NULL,
	-- 판매묶음GST수수료
	[sales_bundle_gst_fee] decimal(10,2) DEFAULT 0 NOT NULL,
	-- 판매묶음PST수수료
	[sales_bundle_pst_fee] decimal(10,2) DEFAULT 0 NOT NULL,
	-- 판매묶음ECO수수료
	[sales_bundle_eco_fee] decimal(10,2) DEFAULT 0 NOT NULL,
	-- 판매묶음Deposit수수료
	[sales_bundle_deposit_fee] decimal(10,2) DEFAULT 0 NOT NULL,
	-- 판매묶음입고가격
	[sales_bundle_receiving_price] decimal(10,2) DEFAULT 0 NOT NULL,
	-- 판매묶음정규가격
	[sales_bundle_regular_price] decimal(10,2) DEFAULT 0 NOT NULL,
	-- 판매묶음개수
	[sales_bundle_qty] int NOT NULL,
	-- 판매묶음가격
	[sales_bundle_price] decimal(10,2) DEFAULT 0 NOT NULL,
	-- 판매묶음할인율
	[sales_bundle_discount_rate] int DEFAULT 0 NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([order_id], [store_id], [item_id], [item_sales_type_cd])
);


-- 지불
CREATE TABLE [payment]
(
	-- 계정번호 : 계정번호
	[account_no] int NOT NULL,
	-- Payment ID
	[payment_id] int NOT NULL IDENTITY ,
	-- 카드번호
	[card_no] varchar(16) NOT NULL,
	-- card_name
	[card_name] varchar(200) NOT NULL,
	-- 유효기간월
	[expiration_month] char(2) NOT NULL,
	-- 유효기간년
	[expiration_year] char(2) NOT NULL,
	-- CVC번호
	[card_verification_code] varchar(10),
	-- 기본여부
	[is_default] bit DEFAULT '0' NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_no], [payment_id])
);


-- 픽업_시간표
CREATE TABLE [pickup_timeslot]
(
	-- 슬롯날짜
	[slot_dt] date NOT NULL,
	-- 슬롯시간
	[slot_time] time NOT NULL,
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 할당수량
	[allocation_qty] int DEFAULT 0 NOT NULL,
	-- 예약수량
	[reserved_qty] int DEFAULT 0 NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([slot_dt], [slot_time], [store_id])
);


-- 픽업_시간표_기본
CREATE TABLE [pickup_timeslot_default]
(
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 기본요일 : SUN, MON, TUE, WED, THU, FRI, SAT
	[default_day_Week] varchar(3) NOT NULL,
	-- 기본슬롯시간
	[default_slot_time] time NOT NULL,
	-- 할당수량
	[allocation_qty] int DEFAULT 0 NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([store_id], [default_day_Week], [default_slot_time])
);


-- province
CREATE TABLE [province]
(
	-- 주코드
	[province_cd] varchar(10) NOT NULL,
	-- 주이름
	[province_nm] varchar(100) NOT NULL,
	-- 나라코드
	[country_cd] varchar(10) NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([province_cd])
);


-- 서비스API
CREATE TABLE [service_api]
(
	-- API번호
	[api_no] int NOT NULL IDENTITY ,
	-- API이름
	[api_nm] varchar(20) NOT NULL,
	-- API경로
	[api_path] varchar(200) NOT NULL,
	-- API메소드
	[api_method] varchar(6) NOT NULL,
	-- API타입
	[api_type] varchar(6) NOT NULL,
	-- API설명
	[api_desc] varchar(500),
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([api_no])
);


-- 매장
CREATE TABLE [store]
(
	-- 매장ID
	[store_id] varchar(10) NOT NULL,
	-- 매장이름
	[store_nm] varchar(250),
	-- 주소
	[address] varchar(100),
	-- 시
	[city] varchar(20),
	-- 주
	[province] varchar(20),
	-- 우편번호
	[postal_cd] varchar(10),
	-- 매장운영시간
	[store_open_time] varchar(50),
	-- 전화번호
	[telephone] varchar(20),
	-- 사용여부
	[is_use] bit DEFAULT '1' NOT NULL,
	-- 매장관리자ID
	[store_manager_id] varchar(200),
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([store_id])
);



