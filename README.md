# HNS-SHOP


### 1. 환경 구성

다운로드

[Visual Studio Code 다운로드][vscodelink]

[vscodelink]: https://code.visualstudio.com/ "Visual Studio Code"

[Spring Tools for Exlipse 다운로드][stslink]

[stslink]: https://download.springsource.com/release/STS4/4.6.1.RELEASE/dist/e4.15/spring-tool-suite-4-4.6.1.RELEASE-e4.15.0-win32.win32.x86_64.self-extracting.jar "Spring sts"

[OpenJDK 다운로드][openjdklink]

[openjdklink]: https://jdk.java.net/archive/ "OpenJDK"

[Lombok 다운로드][lomboklink]

[lomboklink]: https://medium.com/@dongchimi/%EC%9D%B4%ED%81%B4%EB%A6%BD%EC%8A%A4%EC%97%90-lombok-%EC%84%A4%EC%B9%98-%EB%B0%8F-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-b3489875780b "Lombok"

[Docker toolbox 다운로드][dockertoolboxlink]

[dockertoolboxlink]: https://github.com/docker/toolbox/releases "Docker toolbox"

[Sql Server Management Studio 다운로드][ssmslink]

[ssmslink]: https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-ver15 "Sql server management studio"

[DBeaver Community Database Tool 다운로드][dbeaverlink]

[dbeaverlink]: https://dbeaver.io/download/?start&os=win&arch=x86_64 "DBeaver Community Database Tool"

Local DB 환경구성 (Docker 추천)
>docker sql server 설치
>1. 이미지 다운로드
>docker pull mcr.microsoft.com/mssql/server:2019-latest
>
>2. 이미지 조회
>docker images
>
>2. 컨테이너 생성
>docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Pa33word" -p1433:1433 --name sql2019 -d 이미지ID
>
>3. SQL Server 연결
>sql2019은 컨테이너 생성 시 지정한 이름
>실행 중인 컨테이너 내에서 대화형 bash 셸 시작
>docker exec -it sql2019 "bash"
>
>4. 컨테이너 내부로 들어가면 sqlcmd를 사용하여 로컬로 연결
>/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Pa33word'
>
>5. db 생성
>create database wholesaleDB
>
>6. db 조회
>SELECT Name from sys.Databases

프로젝트 필수 스크립트 DDL & Data 사용 (DBeaver 사용 권장)
>/HNS-SHOP/src/main/resources/sechema.sql
>/HNS-SHOP/src/main/resources/defult_data.sql

sts 환경구성
> openJdk 11 설정
> utf-8 설정
> ERD 플러그인 설정
> [lombok설치][lombokInstall-link]

[lombokInstall-link]: https://medium.com/@dongchimi/%EC%9D%B4%ED%81%B4%EB%A6%BD%EC%8A%A4%EC%97%90-lombok-%EC%84%A4%EC%B9%98-%EB%B0%8F-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-b3489875780b "lombok설치"


소스 복사
> workspace 에서 git clone 사용
> sts forder import 사용

로컬 환경 Windows 10 64bit에 IIS와 Spring boot를 설치하고 연동하기
[IIS Spring boot Connector 연결][iis2springbootlink]

[iis2springbootlink]: https://offbyone.tistory.com/322 "IIS Spring boot Connector 연결"


Junit5
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