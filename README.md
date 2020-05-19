# HNS-SHOP


### 1. 환경 구성


<a href="https://code.visualstudio.com/" target="_blank">Visual Studio Code 다운로드</a>

[Spring Tools for Exlipse 다운로드][stslink]

[stslink]: https://download.springsource.com/release/STS4/4.6.1.RELEASE/dist/e4.15/spring-tool-suite-4-4.6.1.RELEASE-e4.15.0-win32.win32.x86_64.self-extracting.jar "Spring sts"

[OpenJDK 다운로드][openjdklink]

[openjdklink]: https://jdk.java.net/archive/ "OpenJDK"

[Docker toolbox 다운로드][dockertoolboxlink]

[dockertoolboxlink]: https://github.com/docker/toolbox/releases "Docker toolbox"

[Sql Server Management Studio 다운로드][ssmslink]

[ssmslink]: https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-ver15 "Sql server management studio"

<a href="http://stackoverflow.com" target="_blank">go</a>

>docker sql server 설치  
>1. 이미지 다운로드  
>docker pull mcr.microsoft.com/mssql/server:2019-latest
>
>2. 컨테이너 생성  
>docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Pa33word" -p1433:1433 --name sql2019 -d >d60e9ac97708
>
>3. SA 암호 변경  
>docker exec -it sql2019 /opt/mssql-tools/bin/sqlcmd \
> -S localhost -U SA -P '<Pa33word>' \
> -Q 'ALTER LOGIN SA WITH PASSWORD="<Pa33word>"'
>
>4. SQL Server 연결  
>sql2019은 컨테이너 생성 시 지정한 이름
>실행 중인 컨테이너 내에서 대화형 bash 셸 시작
>docker exec -it sql2019 "bash"
>
>5. 컨테이너 내부로 들어가면 sqlcmd를 사용하여 로컬로 연결  
>/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Pa33word'
>
>
>6. MSSQL 계정 생성  
>MSSQL에서는 설치 중에 기본적으로 SA계정을 제공하며 이는 SQL Server 인스턴스의 시스템 관리자이다.  
>대부분 SA에 비밀번호를 설정하지 않거나 쉬운 비밀번호를 적용시키기 때문에 기본 포트와 SA 계정으로 >Database server에 접근하게 되면 뚫리게 되는 경우가 발생한다. 이를 방지하기 위해 추가로 계정을 >만들어 Database에 접근할 수 있도록 해야한다  
>계정 사용에 대한 두 가지 방법 : 기존 윈도우 계정을 SQL계정으로 사용 / OS와 다른 별도의 새로운 계정 >사용  
>로그인 계정 생성  
>CREATE LOGIN 'david' WITH PASSWORD='Pa33word', DEFAULT_DATABASE='wholesaleDB'  
>계정 생성 후 DEFAULT DATABASE 설정하는 경우  
>ALTER LOGIN [david] WITH DEFAULT_DATABASE = [wholesaleDB]  
>
>7. db 생성  
>create database wholesaleDB  
>
>8. db 조회  
>SELECT Name from sys.Databases  
>.백업 폴더 생성  
>docker exec -it sql2019 mkdir /var/opt/mssql/backup  
>>
>1. 호스트에서 컨테이너로 파일 전송하는 방법  
>docker cp X:/eclipse/vscode/wholesaleDB_backup_2020_04_18_010001_1258974.bak sql2019:/var/  >opt/mssql/backup/wholesaleDB_backup_2020_04_18_010001_1258974.bak  
>>
>2. 컨테이너에서 호스트로 파일 전송하는 방법   
>docker cp mycontainer:/path/foo.txt /path/foo.txt


필수 스크립트 생성
>/HNS-SHOP/src/main/resources/sechema.sql
>   >DDL     
>   >Data insert  

sts 환경구성
> openJdk 11 설정  
> utf-8 설정  
> ERD 플러그인 설정
> 프로젝트 복사  
> git clone

테스트는 테스트 참고하세요.


*****
### 2. 구성 가이드

##### 디렉토리 구조   

>domain
>   >controller   
>   >service   
>   >dao   
>   >vo   

>global
>   >controller   
>   >service   
>   >dao   
>   >vo   

##### 패키지   

| 패키지 명      | 설명     |
| ---------- | ------ |
| domain     | 기본 패키지 |
| controller | 컨트롤러   |
| service    | 서비스    |
| dao        | 데이터    |
| vo         | 객체     |

| 패키지 명      | 설명     |
| ---------- | ------ |
| global     | 기본 패키지 |
| controller | 컨트롤러   |
| service    | 서비스    |
| dao        | 데이터    |
| vo         | 객체     |

##### Layer 별 역할 및 명명 규칙   

| Layer      | 구분   | 표준  | 예시  |
| ---------- | ---- | --- | --- |
| controller | 컨트롤러 |     |     |
| service    | 서비스  |     |     |
| dao        | 데이터  |     |     |
| vo         | 객체   |     |     |
| query.xml  | 객체   |     |     |

###### This is a H6

*****
### 3. VO, DTO 작성 가이드
> Dao vo 생성시 어노테이션 사용은 아래와 같습니다.  
> Dao용 vo는 controller에서 그대로 사용하지 않습니다.
>   > @Builder  
>   > @AllArgsConstructor  
>   > @NoArgsConstructor  
>   > @Getter  
>   > @Setter  
>   > @toString  

> DTO vo 생성시 어노테이션 사용은 아래와 같습니다.  
> DTO는 Dao vo로 사용하지 않습니다.
>   > @Builder  
>   > @AllArgsConstructor  
>   > @NoArgsConstructor  
>   > @Getter  
>   > @Setter  
>   > @toString  

*****
### 4. Dao 작성 가이드
>Dao는 주로 Interface로 작성합니다.  
>작성된 Dao는 반드시 query.xml과 1대 1로 맵핑하여 줍니다.
> com.hannamsm.shop.domain.event.dao.EventDao.class  
> src/main/resources/mapper/Event.xml
><pre><code>
>public interface EventDao {
>   public int create(@Param("event")Event event) throws Exception;
>
>   public int selectEventId() throws Exception;
>   
>   public Optional<Event> findById(Integer id) throws Exception;
>
>   public int findAllTotalCount(EventSearch eventSearch) throws Exception;
>   
>   public List<Event> findAll(EventSearch eventSearch) throws Exception;
>
>   public int update(Event event) throws Exception;
>
>}
></code></pre>

*****
### 5. Service 작성 가이드
<pre>
<code>
public class BootSpringBootApplication {
  public static void main(String[] args) {
    System.out.println("Hello, Honeymon");
  }

}
</code>
</pre>

*****
### 6. Controller 작성 가이드

예제는 아래와 같습니다.
반드시 @RestController를 사용해야 합니다.
반드시 @RequestMapping을 MediaTypes.HAL_JSON_VALUE 선언하여 사용해야 합니다.

입력 값으로는 DTO를 사용해야 하며 필수 조건은 DTO를 이용하여 valid를 사용합니다.  
valid의 결과값으로 Errors 객체를 사용합니다.
DTO의 입력 조건을 확인하려면 VO 생성을 참조하세요

추가적인 입력검증은 Validator를 구현하여 사용합니다. 자세한 내용은 validator를 참조하세요.
<pre>
<code>
@RestController
@RequestMapping(value="/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {
    @Autowired
    private EventService eventService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private EventValidator eventValidator;
    
    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto reqEventDto
            , @CurrentUser Account account, Errors errors) throws Exception {
        if(errors.hasErrors()) {
            return badRequest(errors);
        }
        
        this.eventValidator.validate(reqEventDto, errors);
        if(errors.hasErrors()) {
            return badRequest(errors);
        }
        
        Event event = modelMapper.map(reqEventDto, Event.class);
        event.update();
        event.setRegPerson(account.getId());
        event.setLastModPerson(account.getId());
        
        Event savedEvent = this.eventService.create(event);
        
        ResEventDto resEventDto = modelMapper.map(savedEvent, ResEventDto.class);
        
        ResponseResutl<ResEventDto> resResult = new ResponseResutl<ResEventDto>();
        resResult.setMessage("생성되었습니다.");
        resResult.setResult(resEventDto);

        return ResponseEntity
                .created(linkTo(this.getClass()).slash(event.getId()).toUri())
                .body(resEventDto);
    }
</code>
</pre>

*****
### 7. Validator 작성 가이드
*single asterisks*
_single underscores_
**double asterisks**
__double underscores__
~~cancelline~~

*****
### 8. Error 작성 가이드

#### 에러코드 정의 

##### ex) B12345678910  
| 예제     | 자리      | 설명        | 종류                              |
| ------ | ------- | --------- | ------------------------------- |
| B      | 0자리     | 메시지 유형구분  | B:비즈시니,S:시스템,:D:DB IO,I:정보,U:조치 |
| 123    | 1~3자리   | 어플리케이션 코드 | ORD:주문,DPT:입금,CMM:공통...         |
| 45     | 4~5자리   | 시스템 코드    | 00:시스템공통,01:HNS-BBY,02:HNS-SRY  |
| 678910 | 6~10 자리 | 일련번호      | 순차 일련번호                         |



*****
### 9. Test 작성 가이드
<img src="http://tst.t-brothers.com/images/initTB.jpg" width="40%" height="30%" title="px(픽셀) 크기 설정" alt="RubberDuck"></img>

*****
### 10. Doc 문서화 작성 가이드
* 줄 바꿈을 하기 위해서는 문장 마지막에서 3칸이상을 띄어쓰기해야 한다.___\\ 띄어쓰기
이렇게


*****
### 11. 빌드 사용 가이드

*****