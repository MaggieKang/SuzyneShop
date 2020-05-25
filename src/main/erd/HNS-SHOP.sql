
/* Drop Tables */

IF ObJECt_ID('[authority]') IS NOT NULL DROP TABLE [authority];
IF ObJECt_ID('[customer]') IS NOT NULL DROP TABLE [customer];
IF ObJECt_ID('[account]') IS NOT NULL DROP TABLE [account];
IF ObJECt_ID('[item_file]') IS NOT NULL DROP TABLE [item_file];
IF ObJECt_ID('[item]') IS NOT NULL DROP TABLE [item];
IF ObJECt_ID('[category]') IS NOT NULL DROP TABLE [category];
IF ObJECt_ID('[cmn_code_detail]') IS NOT NULL DROP TABLE [cmn_code_detail];
IF ObJECt_ID('[cmn_code]') IS NOT NULL DROP TABLE [cmn_code];
IF ObJECt_ID('[cmn_cls_code]') IS NOT NULL DROP TABLE [cmn_cls_code];
IF ObJECt_ID('[cmn_file]') IS NOT NULL DROP TABLE [cmn_file];
IF ObJECt_ID('[mfProd]') IS NOT NULL DROP TABLE [mfProd];
IF ObJECt_ID('[store]') IS NOT NULL DROP TABLE [store];




/* Create Tables */

CREATE TABLE [account]
(
	[seq] int NOT NULL UNIQUE IDENTITY ,
	-- 계정 ID
	[id] varchar(200) NOT NULL UNIQUE,
	-- 이름
	[name] varchar(200),
	-- 비밀번호
	[password] varchar(500) NOT NULL,
	-- email 
	[email] varchar(200),
	-- 만료 여부
	-- is Expired
	[is_expired] bit NOT NULL,
	-- 잠김여부(입력 또는 일시 잠김 설정)
	-- (locked:1, unlocked:0)
	[is_locked] bit NOT NULL,
	[is_use] bit,
	-- 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자
	[reg_person] varchar(256),
	-- last_mod_date
	[last_mod_date] datetime,
	-- 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([seq])
);


CREATE TABLE [authority]
(
	-- authority code
	[auth_cd] varchar(20) NOT NULL,
	[seq] int NOT NULL UNIQUE,
	PRIMARY KEY ([auth_cd], [seq])
);


CREATE TABLE [category]
(
	[category_cd] varchar(10) NOT NULL,
	[category_nm] varchar(45),
	[category_desc] varchar(250),
	[parent_category_cd] varchar(10),
	[is_use] bit DEFAULT '1',
	-- 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자
	[reg_person] varchar(256),
	-- last_mod_date
	[last_mod_date] datetime,
	-- 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([category_cd])
);


-- common classification code
-- 공통 분류 코드
CREATE TABLE [cmn_cls_code]
(
	[cls_cd] char(3) NOT NULL,
	[cls_cd_nm] varchar(60) NOT NULL,
	[cls_cd_dc] varchar(200),
	PRIMARY KEY ([cls_cd])
);


-- common code
CREATE TABLE [cmn_code]
(
	[cd_id] varchar(10) NOT NULL,
	[cd_id_nm] varchar(60) NOT NULL,
	[cd_id_dc] varchar(20),
	[is_use] bit DEFAULT '1' NOT NULL,
	[cls_cd] char(3) NOT NULL,
	-- 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자
	[reg_person] varchar(256),
	-- last_mod_date
	[last_mod_date] datetime,
	-- 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([cd_id])
);


-- common code detail
CREATE TABLE [cmn_code_detail]
(
	[cd_id] varchar(10) NOT NULL,
	[cd] varchar(10) NOT NULL,
	[cd_nm] varchar(60) NOT NULL,
	[cd_dc] varchar(20),
	[is_use] bit DEFAULT '1' NOT NULL,
	-- 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자
	[reg_person] varchar(256),
	-- last_mod_date
	[last_mod_date] datetime,
	-- 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([cd_id], [cd])
);


CREATE TABLE [cmn_file]
(
	[cmn_file_id] varchar(100) NOT NULL,
	[cnm_file_type_cd] varchar(10) NOT NULL,
	[cmn_file_original_nm] varchar(200) NOT NULL,
	[cnm_file_nm] varchar(500) NOT NULL,
	[cnm_file_path] varchar(100) NOT NULL,
	[cnm_file_size] int NOT NULL,
	[is_use] bit DEFAULT '1' NOT NULL,
	-- 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자
	[reg_person] varchar(256),
	-- last_mod_date
	[last_mod_date] datetime,
	-- 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([cmn_file_id])
);


CREATE TABLE [customer]
(
	[seq] int NOT NULL,
	-- 계정 ID
	[id] varchar(200) NOT NULL UNIQUE,
	-- 이름
	[name] varchar(200),
	-- 비밀번호
	[password] varchar(500) NOT NULL,
	-- email 
	[email] varchar(200),
	-- 만료 여부
	-- is Expired
	[is_expired] bit NOT NULL,
	-- 잠김여부(입력 또는 일시 잠김 설정)
	-- (locked:1, unlocked:0)
	[is_locked] bit NOT NULL,
	[is_use] bit,
	-- 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자
	[reg_person] varchar(256),
	-- last_mod_date
	[last_mod_date] datetime,
	-- 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([seq])
);


CREATE TABLE [item]
(
	[item_id] varchar(24) NOT NULL,
	[upc] varchar(20),
	[gal_code] varchar(9),
	[prod_own_code] varchar(4),
	[supp_code] varchar(9),
	[prod_id] nvarchar(15),
	[item_kr_nm] varchar(150),
	[item_en_nm] varchar(150),
	[item_size] nvarchar(30),
	-- tblCategory1 포스 테이블 참조
	[item_type] nvarchar(2),
	-- 상품 Type - "08" 베이커리 6개 이상 구매시 Non Tax
	-- 
	[item_type2] nvarchar(2),
	[item_in_price] decimal(10,2),
	[sale_price] decimal(10,2),
	[regular_price] decimal(10,2),
	[item_balance] decimal(10,2),
	-- 세금유형 - GST, BOTH(GST+PST)
	-- 
	[tax_cd] nchar(1),
	-- 판매 단위 - EA, PK, LB
	-- 
	[sale_unit] nvarchar(4),
	-- "값이 있을 경우 다음 Table 참조 Bottle Deposit / Ecofee 추가
	-- 100 보다 크면 Select * FROM [dbgal].[dbo].[mfProdEco] WHERE ReturnType ='ReturnType' 
	-- 100 보다 작으면 Select * FROM [dbgal].[dbo].[tblEncorp] WHERE ReturnType ='ReturnType'"
	[deposit_cd] int,
	[category_cd] varchar(10),
	[is_use] bit,
	-- 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자
	[reg_person] varchar(256),
	-- last_mod_date
	[last_mod_date] datetime,
	-- 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([item_id])
);


CREATE TABLE [item_file]
(
	[item_id] varchar(24) NOT NULL,
	[cmn_file_id] varchar(100) NOT NULL,
	-- 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자
	[reg_person] varchar(256),
	-- last_mod_date
	[last_mod_date] datetime,
	-- 마지막 변경 사용자
	[last_mod_person] varchar(256)
);


-- ※ 참고
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
CREATE TABLE [mfProd]
(
	[GalCode] varchar(9) NOT NULL,
	[ProdOwnCode] varchar(4) NOT NULL,
	[SuppCode] varchar(9) NOT NULL,
	[prodId] nvarchar(15),
	-- 어느 하나 입력 없는 경우 있음
	[prodName] nvarchar(60),
	-- 어느 하나 입력 없는 경우 있음
	-- 
	-- 
	[prodKname] nvarchar(60),
	-- 상품 Type
	[prodType] nvarchar(2),
	-- 공급처
	[prodSupp] nvarchar(4),
	-- 입고 가격
	[prodIUprice] float,
	[prodOUprice] float,
	-- Balance - 현재 재고 관리하지 않으므로 판매된 아이템은 "- " 재고
	-- 
	[prodBal] float,
	[prodBalw] float,
	[prodProp] float,
	[prodAisle] nvarchar(12),
	-- 상품 TAX - GST, BOTH(GST+PST)
	-- 
	[prodTax] nvarchar(1),
	-- 판매 단위 - EA, PK, LB
	-- 
	[prodUnit] nvarchar(4),
	-- "값이 있을 경우 다음 Table 참조 Bottle Deposit / Ecofee 추가
	-- 100 보다 크면 Select * FROM [dbgal].[dbo].[mfProdEco] WHERE ReturnType ='ReturnType' 
	-- 100 보다 작으면 Select * FROM [dbgal].[dbo].[tblEncorp] WHERE ReturnType ='ReturnType'"
	-- 
	-- 
	[prodDeposit] float,
	-- promotion 묶음 개수
	-- 
	[prodPromo] nvarchar(2),
	-- promotion 시작일
	-- promotion 종료일
	-- 
	[promoSdate] smalldatetime,
	-- promotion 시작일
	-- promotion 종료일
	-- 
	[promoEdate] smalldatetime,
	-- promotion 가격
	-- 
	[promoPrice] float,
	[WeeklySaleYN] bit,
	[LongTermSaleYN] bit,
	[ManagerSaleYN] bit,
	[BlowoutSaleYN] bit,
	[prodImportBal] float,
	[prodImportDate] smalldatetime,
	[item] nchar(1),
	[prodType2] nvarchar(2),
	[prodWprice] tinyint,
	[prodEdate] smalldatetime,
	[prodlead] int,
	[prodmorder] int,
	[prodweight] float,
	-- 상품 규격
	[prodsize] nvarchar(30),
	[prodBalR] float,
	-- 멤버가격
	[memberprice1] float,
	-- 멤버 가격
	-- 
	[memberprice2] float,
	-- p_멤버가격
	[memberprice3] float,
	[memberprice4] float,
	-- 멤버 가격 적용 시작일
	[memberSdate] smalldatetime,
	-- 멤버 가격 적용 종료일
	-- 
	[memberEdate] smalldatetime,
	-- 묶음 개수
	[memberQty] nchar(2),
	-- 멤버 가격 적용 기간내 구입할 수 있는 개수
	-- 
	[MemberLimitQty] varchar(2),
	[PTStime] nvarchar(16),
	[PTEtime] nvarchar(16),
	[PTQty] nchar,
	[PTPrice] float,
	-- discount 여부
	-- 
	[dcYN] varchar(1),
	-- discount rate 당일 첫번째
	-- 
	[dc1Per] int,
	-- discount time
	[dc1TM] varchar(5),
	-- discount rate 당일 두번째
	-- 
	[dc2Per] int,
	-- Discount time
	-- 
	[dc2TM] varchar(5),
	-- 포인트 아이템 개수
	[PIQty] varchar(2),
	-- 포인트 아이템 이벤트 시작일
	[PISDate] smalldatetime,
	-- 포인트 아이템 이벤트 종료일
	[PIEDate] smalldatetime,
	-- 포인트 아이템 가격
	[PIPrice] float,
	-- 차감 포인트
	[PIPoints] int,
	-- 쿠폰 프로모션 여부
	[IsCP] bit,
	-- 쿠폰 프로모션 시작일
	[CPSDate] smalldatetime,
	-- 쿠폰프로모션종료일
	[CPEDate] smalldatetime,
	-- 쿠폰개수
	[CPCpnQty] varchar(2),
	-- 쿠폰 가격
	[CPPrice] float,
	-- 쿠폰 프로모션 기간내 구입할 수 있는 개수
	[CPLimitQty] varchar(2),
	[buyTime] varchar(1),
	-- 포인트 적용 제외 여부
	[CouponException] varchar(1),
	[CouponExceptionSDate] smalldatetime,
	[CouponExceptionEDate] smalldatetime,
	-- discount 제외 여부
	[DCException] varchar(1),
	-- 가격 직접 입력
	[ManualPriceEntry] varchar(1),
	-- 체크시 영수증 한장 더 출력
	[AutoReprint] int,
	-- 배송 여부
	-- 
	[IsDelivery] bit,
	-- 나이제한 상품
	[AgeValidation] bit,
	[CRMCoupon] char,
	[SpecialCoupon] char,
	-- 레코드 수정자 정보
	[LastModPerson] nvarchar(20),
	-- 레코드 수정 날짜
	-- 
	[LastModDate] smalldatetime,
	-- 레코드 수정 시간
	[LastModTime] nvarchar(16),
	-- 상품 사용 여부
	[useYN] char(1),
	-- 웹사이트에 표시 여부
	[webView] char(1),
	PRIMARY KEY ([GalCode], [ProdOwnCode], [SuppCode])
);


CREATE TABLE [store]
(
	[store_id] varchar(10) NOT NULL,
	[store_nm] varchar(250),
	[address] varchar(100),
	[city] varchar(20),
	[province] varchar(20),
	[postal_cd] varchar(10),
	[store_open_time] varchar(50),
	[telephone] varchar(20),
	[is_use] bit DEFAULT '1' NOT NULL,
	[store_manager_id] varchar(200),
	-- 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자
	[reg_person] varchar(256),
	-- last_mod_date
	[last_mod_date] datetime,
	-- 마지막 변경 사용자
	[last_mod_person] varchar(256),
	PRIMARY KEY ([store_id])
);



/* Create Foreign Keys */

ALTER TABLE [authority]
	ADD FOREIGN KEY ([seq])
	REFERENCES [account] ([seq])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [customer]
	ADD FOREIGN KEY ([seq])
	REFERENCES [account] ([seq])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [category]
	ADD FOREIGN KEY ([parent_category_cd])
	REFERENCES [category] ([category_cd])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [item]
	ADD FOREIGN KEY ([category_cd])
	REFERENCES [category] ([category_cd])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [cmn_code]
	ADD FOREIGN KEY ([cls_cd])
	REFERENCES [cmn_cls_code] ([cls_cd])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE [cmn_code_detail]
	ADD FOREIGN KEY ([cd_id])
	REFERENCES [cmn_code] ([cd_id])
	ON UPDATE CASCADE
	ON DELETE CASCADE
;


ALTER TABLE [item_file]
	ADD FOREIGN KEY ([cmn_file_id])
	REFERENCES [cmn_file] ([cmn_file_id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [item_file]
	ADD FOREIGN KEY ([item_id])
	REFERENCES [item] ([item_id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



