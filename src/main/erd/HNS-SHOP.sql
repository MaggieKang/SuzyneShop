
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

-- 계정
CREATE TABLE [account]
(
	-- 순번
	[seq] int NOT NULL UNIQUE IDENTITY ,
	-- 계정ID : 계정 ID
	[id] varchar(200) NOT NULL UNIQUE,
	-- 이름 : 이름
	[name] varchar(200),
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
	PRIMARY KEY ([seq])
);


-- 권한
CREATE TABLE [authority]
(
	-- 권한코드 : authority code
	[auth_cd] varchar(20) NOT NULL,
	-- 순번
	[seq] int NOT NULL UNIQUE,
	PRIMARY KEY ([auth_cd], [seq])
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
	-- 순번
	[seq] int NOT NULL,
	-- 계정ID : 계정 ID
	[id] varchar(200) NOT NULL UNIQUE,
	-- 이름 : 이름
	[name] varchar(200),
	-- 비밀번호 : 비밀번호
	[password] varchar(500) NOT NULL,
	-- 이메일 : email 
	[email] varchar(200),
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
	PRIMARY KEY ([seq])
);


-- 상품
CREATE TABLE [item]
(
	-- 상품ID
	[item_id] varchar(24) NOT NULL,
	-- 바코드ID
	[upc] varchar(20),
	-- 포스Key1
	[gal_code] varchar(9),
	-- 포스Key2
	[prod_own_code] varchar(4),
	-- 포스Key3
	[supp_code] varchar(9),
	-- 포스 상품ID index key
	[prod_id] nvarchar(15),
	-- 상품한글이름
	[item_kr_nm] varchar(150),
	-- 상품영문이름
	[item_en_nm] varchar(150),
	-- 상품규격
	[item_size] nvarchar(30),
	-- 상품유형 : tblCategory1 포스 테이블 참조
	[item_type] nvarchar(2),
	-- 상품유형2 : 상품 Type - "08" 베이커리 6개 이상 구매시 Non Tax
	-- 
	[item_type2] nvarchar(2),
	-- 입고가격
	[item_in_price] decimal(10,2),
	-- 판매가격
	[sale_price] decimal(10,2),
	-- Regular가격
	[regular_price] decimal(10,2),
	-- 상품재고
	[item_balance] decimal(10,2),
	-- 세금코드 : 세금유형 - GST, BOTH(GST+PST)
	-- 
	[tax_cd] nchar(1),
	-- 판매단위 : 판매 단위 - EA, PK, LB
	-- 
	[sale_unit] nvarchar(4),
	-- Deposit코드 : "값이 있을 경우 다음 Table 참조 Bottle Deposit / Ecofee 추가
	-- 100 보다 크면 Select * FROM [dbgal].[dbo].[mfProdEco] WHERE ReturnType ='ReturnType' 
	-- 100 보다 작으면 Select * FROM [dbgal].[dbo].[tblEncorp] WHERE ReturnType ='ReturnType'"
	[deposit_cd] int,
	-- 분류코드
	[category_cd] varchar(10),
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
	-- 상품ID
	[item_id] varchar(24) NOT NULL,
	-- 공통파일ID
	[cmn_file_id] varchar(100) NOT NULL,
	-- 최초등록일시 : 최초등록일시
	[reg_date] datetime,
	-- 최초등록사용자 : 최초등록사용자
	[reg_person] varchar(256),
	-- 마지막변경일시 : last_mod_date
	[last_mod_date] datetime,
	-- 마지막변경사용자 : 마지막 변경 사용자
	[last_mod_person] varchar(256)
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
CREATE TABLE [mfProd]
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


-- 매장
CREATE TABLE [store]
(
	-- store_id
	[store_id] varchar(10) NOT NULL,
	-- store_nm
	[store_nm] varchar(250),
	-- address
	[address] varchar(100),
	-- city
	[city] varchar(20),
	-- province
	[province] varchar(20),
	-- postal_cd
	[postal_cd] varchar(10),
	-- store_open_time
	[store_open_time] varchar(50),
	-- telephone
	[telephone] varchar(20),
	-- 사용여부
	[is_use] bit DEFAULT '1' NOT NULL,
	-- store_manager_id
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



