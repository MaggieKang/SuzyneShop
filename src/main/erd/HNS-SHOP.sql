
/* Drop Tables */

IF ObJECt_ID('[account_address]') IS NOT NULL DROP TABLE [account_address];
IF ObJECt_ID('[account_authority]') IS NOT NULL DROP TABLE [account_authority];
IF ObJECt_ID('[cart]') IS NOT NULL DROP TABLE [cart];
IF ObJECt_ID('[customer]') IS NOT NULL DROP TABLE [customer];
IF ObJECt_ID('[favourite]') IS NOT NULL DROP TABLE [favourite];
IF ObJECt_ID('[orders_detail]') IS NOT NULL DROP TABLE [orders_detail];
IF ObJECt_ID('[orders]') IS NOT NULL DROP TABLE [orders];
IF ObJECt_ID('[account]') IS NOT NULL DROP TABLE [account];
IF ObJECt_ID('[authority]') IS NOT NULL DROP TABLE [authority];
IF ObJECt_ID('[item_file]') IS NOT NULL DROP TABLE [item_file];
IF ObJECt_ID('[item]') IS NOT NULL DROP TABLE [item];
IF ObJECt_ID('[category]') IS NOT NULL DROP TABLE [category];
IF ObJECt_ID('[cmn_code_detail]') IS NOT NULL DROP TABLE [cmn_code_detail];
IF ObJECt_ID('[cmn_code]') IS NOT NULL DROP TABLE [cmn_code];
IF ObJECt_ID('[cmn_cls_code]') IS NOT NULL DROP TABLE [cmn_cls_code];
IF ObJECt_ID('[cmn_file]') IS NOT NULL DROP TABLE [cmn_file];
IF ObJECt_ID('[mfProdEco]') IS NOT NULL DROP TABLE [mfProdEco];
IF ObJECt_ID('[mfProd_b]') IS NOT NULL DROP TABLE [mfProd_b];
IF ObJECt_ID('[oauth_access_token]') IS NOT NULL DROP TABLE [oauth_access_token];
IF ObJECt_ID('[oauth_approvals]') IS NOT NULL DROP TABLE [oauth_approvals];
IF ObJECt_ID('[oauth_client_details]') IS NOT NULL DROP TABLE [oauth_client_details];
IF ObJECt_ID('[oauth_client_token]') IS NOT NULL DROP TABLE [oauth_client_token];
IF ObJECt_ID('[oauth_code]') IS NOT NULL DROP TABLE [oauth_code];
IF ObJECt_ID('[oauth_refresh_token]') IS NOT NULL DROP TABLE [oauth_refresh_token];
IF ObJECt_ID('[pickup_timeslot]') IS NOT NULL DROP TABLE [pickup_timeslot];
IF ObJECt_ID('[store]') IS NOT NULL DROP TABLE [store];
IF ObJECt_ID('[tblEncorp]') IS NOT NULL DROP TABLE [tblEncorp];
IF ObJECt_ID('[tfCollection3_b]') IS NOT NULL DROP TABLE [tfCollection3_b];
IF ObJECt_ID('[tfTran3_b]') IS NOT NULL DROP TABLE [tfTran3_b];




/* Create Tables */

-- 계정
CREATE TABLE [account]
(
	-- 계정ID : 계정 ID
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
	PRIMARY KEY ([account_id])
);


-- 계정주소
CREATE TABLE [account_address]
(
	-- 계정ID : 계정 ID
	[account_id] varchar(20) NOT NULL UNIQUE,
	-- 순번
	[seq] int NOT NULL IDENTITY ,
	-- 주소
	[address] varchar(100),
	-- 시
	[city] varchar(20),
	-- 주
	[province] varchar(20),
	-- 우편번호
	[postal_cd] varchar(10) NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_id], [seq])
);


-- 계정권한
CREATE TABLE [account_authority]
(
	-- 계정ID : 계정 ID
	[account_id] varchar(20) NOT NULL,
	-- 권한코드 : authority code
	[auth_cd] varchar(20) NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_id], [auth_cd])
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


-- 장바구니
CREATE TABLE [cart]
(
	-- 계정ID : 계정 ID
	[account_id] varchar(20) NOT NULL,
	-- 상품ID
	[item_id] varchar(24) NOT NULL UNIQUE,
	-- item_qty : item quantity
	[item_qty] int DEFAULT 0 NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_id], [item_id])
);


-- 카테고리
CREATE TABLE [category]
(
	-- 분류코드
	[category_cd] varchar(10) NOT NULL,
	-- 분류명
	[category_nm] varchar(45),
	-- 분류설명
	[category_desc] varchar(250),
	-- 부모분류코드
	[parent_category_cd] varchar(10),
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
	PRIMARY KEY ([category_cd])
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
	[cnm_file_type_cd] varchar(10) NOT NULL,
	-- 공통파일원본명
	[cmn_file_original_nm] varchar(200) NOT NULL,
	-- 공통파일명
	[cnm_file_nm] varchar(500) NOT NULL,
	-- 공통파일경로
	[cnm_file_path] varchar(100) NOT NULL,
	-- 공통파일사이즈
	[cnm_file_size] int NOT NULL,
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


-- 회원
CREATE TABLE [customer]
(
	-- 계정ID : 계정 ID
	[account_id] varchar(20) NOT NULL UNIQUE,
	-- 고객이름 : 고객이름
	[customer_nm] varchar(200),
	-- 고객이메일 : 고객이메일
	[customer_email] varchar(200),
	-- 고객전화번호 : customer phone number
	[customer_phone_number] varchar(20),
	-- 고객성별
	[customer_gender] varchar(10),
	-- 고객한글이름
	[customer_kr_nm] varchar(150),
	-- 고객영문이름
	[customer_en_nm] varchar(150),
	-- 고객언어코드
	[customer_lang_cd] varchar(4),
	-- 맴버쉽번호
	[membership_no] int,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([account_id])
);


-- 즐겨찾기상품
CREATE TABLE [favourite]
(
	-- 상품ID
	[item_id] varchar(24) NOT NULL UNIQUE,
	-- 계정ID : 계정 ID
	[account_id] varchar(20) NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([item_id])
);


-- 상품
CREATE TABLE [item]
(
	-- 상품ID
	[item_id] varchar(24) NOT NULL,
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
	-- 분류코드
	[category_cd] varchar(10),
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
	-- 판매단위 : 판매 단위 - EA, PK, LB
	-- 
	[sale_unit] nvarchar(4),
	-- 프로모션묶음개수
	[promotion_bundle_qty] int,
	-- 프로모션시작일시
	[promotion_start_date] datetime,
	-- 프로모션종료일시
	[promotion_end_date] datetime,
	-- 프로모션가격
	[promotion_price] decimal(10,2),
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
	PRIMARY KEY ([item_id])
);


-- 상품파일
CREATE TABLE [item_file]
(
	-- 공통파일ID
	[cmn_file_id] varchar(100) NOT NULL,
	-- 상품ID
	[item_id] varchar(24) NOT NULL,
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


-- mfProdEco
CREATE TABLE [mfProdEco]
(
	-- ReturnType
	[ReturnType] varchar(4) NOT NULL,
	-- EcoSection
	[EcoSection] varchar(50),
	-- EcoFee
	[EcoFee] decimal(9,2),
	PRIMARY KEY ([ReturnType])
);


-- 상품for포스 : ※ 참고
-- 상품의 가격 우선 순위
-- 1. Menual Price
-- 2. Cas 저울 or Ishida 저울 아이템
-- 3. Promotion과  Membership 가격 중 적은 가격으로
-- 4. 쿠폰 가격
-- 5. 포인트 가격
-- 6. Regular 가격
-- 
-- 온라인에서 가격 적용
--  - Manual 가격적용  제외
--  - Promotion or Regular Price
-- 
-- 온라인 Table 생성시 반드시 저장해야할 field
-- GalCode, ProdOwnCode, SuppCode, prodId
CREATE TABLE [mfProd_b]
(
	-- p_GalCode
	[GalCode] varchar(9) NOT NULL,
	-- p_ProdOwnCode
	[ProdOwnCode] varchar(4) NOT NULL,
	-- p_SuppCode
	[SuppCode] varchar(9) NOT NULL,
	-- p_상품코드
	[prodId] nvarchar(15),
	-- p_상품영문명 : 어느 하나 입력 없는 경우 있음
	[prodName] nvarchar(60),
	-- p_상품한글명 : 어느 하나 입력 없는 경우 있음
	-- 
	-- 
	[prodKname] nvarchar(60),
	-- p_상품유형 : 상품 Type
	[prodType] nvarchar(2),
	-- p_공급처 : 공급처
	[prodSupp] nvarchar(4),
	-- p_입고가격 : 입고 가격
	[prodIUprice] float,
	-- p_RegularPrice
	[prodOUprice] float,
	-- p_재고수량 : Balance - 현재 재고 관리하지 않으므로 판매된 아이템은 "- " 재고
	-- 
	[prodBal] float,
	-- p_prodBalw
	[prodBalw] float,
	-- p_prodProp
	[prodProp] float,
	-- p_prodAisle
	[prodAisle] nvarchar(12),
	-- p_상품세금 : 상품 TAX - GST, BOTH(GST+PST)
	-- 
	[prodTax] nvarchar(1),
	-- p_판매단위 : 판매 단위 - EA, PK, LB
	-- 
	[prodUnit] nvarchar(4),
	-- p_prodDeposit : "값이 있을 경우 다음 Table 참조 Bottle Deposit / Ecofee 추가
	-- 100 보다 크면 Select * FROM [dbgal].[dbo].[mfProdEco] WHERE ReturnType ='ReturnType' 
	-- 100 보다 작으면 Select * FROM [dbgal].[dbo].[tblEncorp] WHERE ReturnType ='ReturnType'"
	-- 
	-- 
	[prodDeposit] float,
	-- p_prodPromo : promotion 묶음 개수
	-- 
	[prodPromo] nvarchar(2),
	-- p_Promotion시작일 : promotion 시작일
	-- promotion 종료일
	-- 
	[promoSdate] smalldatetime,
	-- p_Promotion종료일 : promotion 시작일
	-- promotion 종료일
	-- 
	[promoEdate] smalldatetime,
	-- p_Promotion가격 : promotion 가격
	-- 
	[promoPrice] float,
	-- p_WeeklySaleYN
	[WeeklySaleYN] bit,
	-- p_LongTermSaleYN
	[LongTermSaleYN] bit,
	-- p_ManagerSaleYN
	[ManagerSaleYN] bit,
	-- p_BlowoutSaleYN
	[BlowoutSaleYN] bit,
	-- p_prodImportBal
	[prodImportBal] float,
	-- p_prodImportDate
	[prodImportDate] smalldatetime,
	-- p_item
	[item] nchar(1),
	-- p_prodType2
	[prodType2] nvarchar(2),
	-- p_prodWprice
	[prodWprice] tinyint,
	-- p_prodEdate
	[prodEdate] smalldatetime,
	-- p_prodlead
	[prodlead] int,
	-- p_prodmorder
	[prodmorder] int,
	-- p_prodweight
	[prodweight] float,
	-- p_상품규격 : 상품 규격
	[prodsize] nvarchar(30),
	-- p_prodBalR
	[prodBalR] float,
	-- p_멤버가격1 : 멤버가격
	[memberprice1] float,
	-- p_멤버가격2 : 멤버 가격
	-- 
	[memberprice2] float,
	-- p_멤버가격3 : p_멤버가격
	[memberprice3] float,
	-- p_멤버가격4
	[memberprice4] float,
	-- p_멤버가격적용시작일 : 멤버 가격 적용 시작일
	[memberSdate] smalldatetime,
	-- p_멤버 가격 적용 종료일 : 멤버 가격 적용 종료일
	-- 
	[memberEdate] smalldatetime,
	-- p_묶음개수 : 묶음 개수
	[memberQty] nchar(2),
	-- p_제한개수 : 멤버 가격 적용 기간내 구입할 수 있는 개수
	-- 
	[MemberLimitQty] varchar(2),
	-- p_PTStime
	[PTStime] nvarchar(16),
	-- p_PTEtime
	[PTEtime] nvarchar(16),
	-- p_PTQty
	[PTQty] nchar,
	-- p_PTPrice
	[PTPrice] float,
	-- p_할인여부 : discount 여부
	-- 
	[dcYN] varchar(1),
	-- p_DiscountRate당일첫번째 : discount rate 당일 첫번째
	-- 
	[dc1Per] int,
	-- p_DiscountTime : discount time
	[dc1TM] varchar(5),
	-- p_DiscountRate당일두번째 : discount rate 당일 두번째
	-- 
	[dc2Per] int,
	-- p_DiscountTime2 : Discount time
	-- 
	[dc2TM] varchar(5),
	-- p_포인트아이템개수 : 포인트 아이템 개수
	[PIQty] varchar(2),
	-- p_포인트아이템이벤트시작일 : 포인트 아이템 이벤트 시작일
	[PISDate] smalldatetime,
	-- p_포인트아이템이벤트종료일 : 포인트 아이템 이벤트 종료일
	[PIEDate] smalldatetime,
	-- p_포인트아이템가격 : 포인트 아이템 가격
	[PIPrice] float,
	-- p_차감포인트 : 차감 포인트
	[PIPoints] int,
	-- p_쿠폰프로모션여부 : 쿠폰 프로모션 여부
	[IsCP] bit,
	-- p_쿠폰프로모션시작일 : 쿠폰 프로모션 시작일
	[CPSDate] smalldatetime,
	-- p_쿠폰프로모션종료일 : 쿠폰프로모션종료일
	[CPEDate] smalldatetime,
	-- p_쿠폰개수 : 쿠폰개수
	[CPCpnQty] varchar(2),
	-- p_쿠폰가격 : 쿠폰 가격
	[CPPrice] float,
	-- p_쿠폰프로모션제한개수 : 쿠폰 프로모션 기간내 구입할 수 있는 개수
	[CPLimitQty] varchar(2),
	-- p_buyTime
	[buyTime] varchar(1),
	-- p_포인트적용제외여부 : 포인트 적용 제외 여부
	[CouponException] varchar(1),
	-- p_CouponExceptionSDate
	[CouponExceptionSDate] smalldatetime,
	-- p_CouponExceptionEDate
	[CouponExceptionEDate] smalldatetime,
	-- p_Discount제외여부 : discount 제외 여부
	[DCException] varchar(1),
	-- p_가격직접입력 : 가격 직접 입력
	[ManualPriceEntry] varchar(1),
	-- p_자동영수증 : 체크시 영수증 한장 더 출력
	[AutoReprint] int,
	-- p_배송여부 : 배송 여부
	-- 
	[IsDelivery] bit,
	-- p_나이제한상품 : 나이제한 상품
	[AgeValidation] bit,
	-- p_CRMCoupon
	[CRMCoupon] char,
	-- p_SpecialCoupon
	[SpecialCoupon] char,
	-- p_마지막수정자 : 레코드 수정자 정보
	[LastModPerson] nvarchar(20),
	-- p_마지막수정날짜 : 레코드 수정 날짜
	-- 
	[LastModDate] smalldatetime,
	-- p_레코드수정시간 : 레코드 수정 시간
	[LastModTime] nvarchar(16),
	-- p_상품사용여부 : 상품 사용 여부
	[useYN] char(1),
	-- p_웹사이트에표시여부 : 웹사이트에 표시 여부
	[webView] char(1),
	PRIMARY KEY ([GalCode], [ProdOwnCode], [SuppCode])
);


-- oauth_access_token
CREATE TABLE [oauth_access_token]
(
	-- authentication_id
	[authentication_id] varchar(256) NOT NULL,
	-- token_id
	[token_id] varchar(256),
	-- token
	[token] varbinary(max),
	-- user_name
	[user_name] varchar(256),
	-- client_id
	[client_id] varchar(256),
	-- authentication
	[authentication] varbinary(max),
	-- refresh_token
	[refresh_token] varchar(256),
	PRIMARY KEY ([authentication_id])
);


-- oauth_approvals
CREATE TABLE [oauth_approvals]
(
	-- userId
	[userId] varchar(256),
	-- clientId
	[clientId] varchar(256),
	-- scope
	[scope] varchar(256),
	-- status
	[status] varchar(10),
	-- expiresAt
	[expiresAt] datetime,
	-- lastModifiedAt
	[lastModifiedAt] datetime
);


-- oauth_client_details
CREATE TABLE [oauth_client_details]
(
	-- client_id
	[client_id] varchar(256) NOT NULL,
	-- resource_ids
	[resource_ids] varchar(256),
	-- client_secret
	[client_secret] varchar(256),
	-- scope
	[scope] varchar(256),
	-- authorized_grant_types
	[authorized_grant_types] varchar(256),
	-- web_server_redirect_uri
	[web_server_redirect_uri] varchar(256),
	-- authorities
	[authorities] varchar(256),
	-- access_token_validity
	[access_token_validity] int,
	-- refresh_token_validity
	[refresh_token_validity] int,
	-- additional_information
	[additional_information] varchar(4096),
	-- autoapprove
	[autoapprove] varchar(256),
	PRIMARY KEY ([client_id])
);


-- oauth_client_token
CREATE TABLE [oauth_client_token]
(
	-- authentication_id
	[authentication_id] varchar(256) NOT NULL,
	-- token_id
	[token_id] varchar(256),
	-- token
	[token] varbinary(max),
	-- user_name
	[user_name] varchar(256),
	-- client_id
	[client_id] varchar(256),
	PRIMARY KEY ([authentication_id])
);


-- oauth_code
CREATE TABLE [oauth_code]
(
	-- code
	[code] varchar(256),
	-- authentication
	[authentication] varbinary(max)
);


-- oauth_refresh_token
CREATE TABLE [oauth_refresh_token]
(
	-- token_id
	[token_id] varchar(256),
	-- token
	[token] varbinary(max),
	-- authentication
	[authentication] varbinary(max)
);


-- 주문
CREATE TABLE [orders]
(
	-- 주문ID
	[order_id] varchar(20) NOT NULL,
	-- 주문일시
	[order_date] datetime NOT NULL,
	-- 총합계
	[total_amount] decimal(10,2),
	-- 총GST수수료
	[total_gst_fee] decimal(10,2),
	-- 총PST수수료
	[total_pst_fee] decimal(10,2),
	-- 총HST수수료
	[total_hst_fee] decimal(10,2),
	-- 총ECO수수료
	[total_eco_fee] decimal(10,2),
	-- 총Deposit수수료
	[total_deposit_fee] decimal(10,2),
	-- 계정ID : 계정 ID
	[account_id] varchar(20) NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([order_id])
);


-- 주문상세
CREATE TABLE [orders_detail]
(
	-- 주문ID
	[order_id] varchar(20) NOT NULL,
	-- 상품ID
	[item_id] varchar(24) NOT NULL,
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
	[order_qty] int,
	-- 총계
	[amount] decimal(10,2),
	-- 낱개판매가격
	[each_sale_price] decimal(10,2),
	-- 낱개GST수수료
	[each_gst_fee] decimal(10,2),
	-- 낱개PST수수료
	[each_pst_fee] decimal(10,2),
	-- 낱개HST수수료
	[each_hst_fee] decimal(10,2),
	-- 낱개ECO수수료
	[each_eco_fee] decimal(10,2),
	-- 낱개Deposit수수료
	[each_deposit_fee] decimal(10,2),
	-- 입고가격
	[receiving_price] decimal(10,2) NOT NULL,
	-- 정규가격
	[regular_price] decimal(10,2),
	-- 프로모션묶음개수
	[promotion_bundle_qty] int NOT NULL,
	-- 프로모션시작일시
	[promotion_start_date] datetime NOT NULL,
	-- 프로모션종료일시
	[promotion_end_date] datetime NOT NULL,
	-- 프로모션가격
	[promotion_price] decimal(10,2) NOT NULL,
	-- 인보이스ID
	[invoice_id] varchar(20),
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([order_id], [item_id])
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
	-- 완료수량
	[completion_qty] int DEFAULT 0 NOT NULL,
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


-- tblEncorp
CREATE TABLE [tblEncorp]
(
	-- ReturnType
	[ReturnType] varchar(4) NOT NULL,
	-- Code
	[Code] varchar(10),
	-- EncorpSectionKor
	[EncorpSectionKor] varchar(50),
	-- EncorpSection
	[EncorpSection] varchar(50),
	-- EncorpSize
	[EncorpSize] varchar(50),
	-- ConNumber
	[ConNumber] int,
	-- DepositValue
	[DepositValue] decimal(9,2),
	-- RecyclingFee
	[RecyclingFee] decimal(9,2),
	-- TotalFee
	[TotalFee] decimal(9,2),
	PRIMARY KEY ([ReturnType])
);


-- 결제for포스
CREATE TABLE [tfCollection3_b]
(
	-- p_colID : ID (자동증가)
	[colID] int,
	-- p_판매일자 : 판매 일자 yyyy-mm-dd
	[colDate] smalldatetime,
	-- p_판매시간 : 판매 시간 00:00:00
	[colTime] nvarchar(16),
	-- p_현금판매 : 현금 판매
	[colCash] decimal(10,2),
	-- p_enterCash
	[enterCash] decimal(10,2),
	-- p_colUSD : UD$
	[colUSD] float,
	-- p_enterUSD
	[enterUSD] float,
	-- p_colVisa : Visa
	[colVisa] float,
	-- p_colMaster : Master
	[colMaster] float,
	-- p_colDebit : Debit
	[colDebit] float,
	-- p_colAmex : Amex
	[colAmex] float,
	-- p_colCredit
	[colCredit] float,
	-- p_쿠폰결제금액 : 쿠폰 결제 금액
	[colCardEtc] float,
	-- p_colCheck : Check
	[colCheck] float,
	-- p_enterCheck
	[enterCheck] float,
	-- p_colChkNo
	[colChkNo] float,
	-- p_상품권 : 상품권
	[colGC] float,
	-- p_colCPIncome
	[colCPIncome] float,
	-- p_colCPExpense
	[colCPExpense] float,
	-- p_colPromo1
	[colPromo1] float,
	-- p_colPromo2
	[colPromo2] float,
	-- p_colPromo3
	[colPromo3] float,
	-- p_colPromo4
	[colPromo4] float,
	-- p_colPromo5
	[colPromo5] float,
	-- p_colHST
	[colHST] float,
	-- p_Gst합계 : Gst 합계
	[colGst] float,
	-- p_Pst합계 : Pst 합계
	[colPst] float,
	-- p_인보이스번호 : 인보이스 번호
	[colInvNo] int,
	-- p_멤버쉽번호 : 멤버쉽 번호
	[colCust] nchar(12),
	-- p_구매포인트 : 구매 포인트
	[colPoint] int,
	-- p_멤버 현재 포인트 : 멤버 현재 포인트
	[colCurPoint] int,
	-- p_colPOption
	[colPOption] nchar,
	-- p_colSPOption
	[colSPOption] nchar,
	-- p_취소포인트 : 취소포인트
	[colEstCancelledPoint] int,
	-- p_취소일자 : 취소일자
	[colEstCancelledDate] varchar(10),
	-- p_colCClass
	[colCClass] nvarchar(10),
	-- p_결제Type : 결제 Type (000000001: 현금)
	[colType] nvarchar(10),
	-- p_캐시어POS번호 : 캐시어POS번호
	[colStation] nchar(2),
	-- p_colShift : 1
	[colShift] nchar,
	-- p_캐시어ID : 캐시어 ID
	[colpassword] nvarchar(10),
	-- p_결제금액 : 결제금액
	[colPaid] float,
	-- p_잔돈 : 잔돈
	[colChange] float,
	-- p_colCouponTotal : 멤버이면 당일 구매 쿠폰 적용 금액
	[colCouponTotal] float,
	-- p_colCouponIssued
	[colCouponIssued] char,
	-- p_colCouponBalance : 멤버이면 당일 구매 쿠폰 적용 금액 누적 $75 Clear
	[colCouponBalance] float,
	-- p_colSavedAmount : Save된금액
	[colSavedAmount] float,
	-- p_col777Event
	[col777Event] nchar,
	-- p_colNation
	[colNation] nvarchar(2),
	-- p_colReferencea : 전화번호 입력, 상품권으로 결제시
	[colReference] nvarchar(15)
);


-- 주문for포스
CREATE TABLE [tfTran3_b]
(
	-- p_인보이스 번호
	[tInvNo] int NOT NULL,
	-- p_ID (자동증가)
	[tID1] int NOT NULL IDENTITY ,
	-- p_인보이스별 Seq
	[tID] int,
	-- p_상품 Code
	[GalCode] varchar(9),
	-- p_상품 Code
	[ProdOwnCode] varchar(4),
	-- p_상품 Code
	[SUppCode] varchar(9),
	-- p_상품 type
	[tType] nvarchar(2),
	-- p_PLU (Barcode)
	[tProd] nvarchar(15),
	-- p_판매일자
	[tDate] smalldatetime,
	-- p_상품 type
	[tPtype] nvarchar(2),
	-- p_판매 개수
	[tQty] decimal(10,2),
	-- p_입고 가격
	[tIUprice] decimal(10,2),
	-- p_RegularSalePrice
	[tOUprice] decimal(10,2),
	-- p_tWprice
	[tWprice] decimal(10,2),
	-- p_tNative
	[tNative] decimal(10,2),
	-- p_SalesAmount
	[tAmt] decimal(10,2),
	-- p_tHST
	[tHST] decimal(10,2),
	-- p_tGst
	[tGst] decimal(10,2),
	-- p_tPst
	[tPst] decimal(10,2),
	-- p_쿠폰 개수
	[tCpnQty] varchar(2),
	-- p_tPIQty
	[tPIQty] nchar(2),
	-- p_tPIUPoint
	[tPIUPoint] int,
	-- p_tPIPoint
	[tPIPoint] int,
	-- p_공급자Code
	[tSupp] nvarchar(4),
	-- p_캐시어 ID
	[tPassWord] nvarchar(10),
	-- p_캐시어 POS No.
	[tPassStation] nvarchar(2),
	-- p_Tax형식
	[tTax] nvarchar(1),
	-- p_멤버쉽번호
	[tCust] nchar(12),
	-- p_tPOption
	[tPOption] nchar(1),
	-- p_멤버쉽판매개수
	[tMemberQty] nchar(2),
	-- p_판매시간
	[tTime] nvarchar(16),
	-- p_tUpCode
	[tUpCode] nvarchar(1),
	-- p_tSpecial
	[tSpecial] nvarchar(1),
	-- p_tFree
	[tFree] int,
	-- p_상품 Type
	[tPtype2] nvarchar(2),
	-- p_tShift
	[tShift] nvarchar(1),
	-- p_프로모션개수
	[tPromo] nchar(2),
	-- p_프로모션Unit
	[tPunit] nvarchar(3),
	-- Status
	[tPriceStatus] varchar(1),
	-- p_Dc제외
	[tDCException] varchar(1),
	-- p_tCRMCoupon
	[tCRMCoupon] char,
	-- p_tSpecialCoupon
	[tSpecialCoupon] char,
	-- p_tDID
	[tDID] int,
	-- p_tDCD
	[tDCD] decimal(10,2),
	-- p_리턴개수
	[tReturnQty] int,
	-- p_오리지널인보이스
	[tReturnInvNo] int,
	-- p_오지지널Seq
	[tReturnID] int,
	PRIMARY KEY ([tInvNo], [tID1])
);



