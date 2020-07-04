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
>docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Pa33word" -p1433:1433 -v /etc/localtime:/etc/localtime:ro -e TZ=Canada/Pacific --name sql2019 -d 이미지ID
>3. SQL Server 연결  
>docker exec -it sql2019 "bash"
>
>4. 컨테이너 내부로 들어가면 sqlcmd를 사용하여 로컬로 연결  
>/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Pa33word'
>
>5. db 생성  
>create database hnsshop
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
컨틀롤러에서 모든 요청에 대한 값 검증을 진행하고 이상이 없을 시에 서비스 레이어를 호출해야 합니다. 잘못된 값이 있으면 서비스 레이어에서 정상적인 작업을 진행하기 어렵습니다. 무엇보다 컨틀롤러의 책임을 다하고 있지 않으면 그 책임은 자연스럽게 다른 레이어로 전해지게 되며 이렇게 넘겨받은 책임을 처리하는데 큰 비용과 유지보수 하기 어려워질 수밖에 없습니다.

컨트롤러의 중요한 책임 중의 하나는 **요청에 대한 값 검증**이 있습니다. 스프링은 JSR 303 기반 어노테이션으로 값 검증을 쉽고 일관성 있게 처리할 수 있도록 도와줍니다. 모든 예외는 @ControllerAdvice 선언된 객체에서 핸들링 됩니다. 컨트롤러로 본인이 직접 예외까지 처리하지 않고 예외가 발생하면 그냥 던져버리는 패턴으로 일관성 있게 개발할 수 있습니다.
컨트롤러에서의 검증은 첫번째로 **@Valid** 어노테이션을 사용합니다.
<pre>
<code>

@RestController
@RequestMapping(value="/api/cart", produces = MediaTypes.HAL_JSON_VALUE)
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartValidator cartValidator;

    ...
    @PutMapping(value = "/{itemId}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity saveCartItem(@PathVariable String itemId
            , @RequestBody @Valid CartItemDto reqCartItemDto
            , @CurrentUser Account currentUser) throws Exception {
        reqCartItemDto.setAccountId(currentUser.getAccountId());

        cartValidator.validate(CartItemDto reqCartItemDto);

        cartService.saveCartItem(reqCartItemDto);

        ResponseResutl<CartItemDto> resResult = new ResponseResutl<CartItemDto>();
        resResult.setMessage("저장 되었습니다.");
        resResult.setResult(reqCartItemDto);

        return ResponseEntity.ok(reqCartItemDto);
    }
    ...
}
</code>
</pre>
<pre>
<code>
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class CartItemDto {
    private String accountId;
    @NotEmpty
    private String itemId;
    @Min(0)
    private int itemQty;
}
</code>
</pre>
CartItemDto 중에서 유효하지 않은 값이 있을 때 **@Valid** 어노테이션으로 예외를 발생시킬 수 있습니다. 이 예외는 @ControllerAdvice에서 적절하게 핸들링 됩니다. @NotEmpty, @Min 외에도 다양한 어노테이션들이 제공됩니다.

[참고 @Valid 어노테이션으로 Parameter 검증하기][springboot-valid-link]

[springboot-valid-link]: https://bamdule.tistory.com/35 "참고 @Valid 어노테이션으로 Parameter 검증하기"

두번째로는 도매인 별로 DomainValidator 컴포넌트를 만들어 사용합니다. 그리고 해당 컴포넌트에서 BusnissException 처리를 해야합니다.
<pre>
<code>
@Component
public class EventValidator {
    public void validate(EventDto eventDto) {
        if(eventDto.getBasePrice() > eventDto.getMaxPrice()
                && eventDto.getMaxPrice() != 0) {
            throw new CartItemWrongPricesException("MaxPrice");
        }

        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        if(endEventDateTime.isBefore(eventDto.getBeginEventDateTime())
                || endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime())
                || endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
            throw new CartItemWrongEndEventException("EndEventDateTime");
        }
    }
}
</code>
</pre>

*****
### 8. Error 작성 가이드
Error Response 객체는 항상 동일한 Error Response를 가져야 합니다. 그렇지 않으면 클라이언트에서 예외 처리를 항상 동일한 로직으로 처리하기 어렵습니다.
<pre>
<code>
@ExceptionHandler(MethodArgumentNotValidException.class)
protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.error("handleMethodArgumentNotValidException", e);
    final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}
</code>
</pre>
위 예제 코드처럼 리턴 타입이 ResponseEntity<ErrorResponse> 으로 무슨 데이터가 어떻게 있는지 명확하게 추론하기 쉽도록 구성하는 게 바람직합니다.

#### Error Response JSON
<pre>
<code>
{
  "message": " Invalid Input Value",
  "status": 400,
  // "errors":[], 비어있을 경우 null 이 아닌 빈 배열을 응답한다.
  "errors": [
    {
      "field": "name.last",
      "value": "",
      "reason": "must not be empty"
    },
    {
      "field": "name.first",
      "value": "",
      "reason": "must not be empty"
    }
  ],
  "code": "C001"
}
</code>
</pre>
ErrorResponse 객체의 JSON 입니다.
| 필드     | 설명        |
| ------- | ------------------------------- |
| message | 에러에 대한 message를 작성합니다. |
| status  | http status code를 작성합니다. header 정보에도 포함된 정보이니 굳이 추가하지 않아도 됩니다. |
| errors  | 요청 값에 대한 field, value, reason 작성합니다. 일반적으로 @Valid 어노테이션으로 JSR 303: Bean Validation에 대한 검증을 진행 합니다. 만약 errors에 바인인된 결과가 없을 경우 null이 아니라 빈 배열 []을 응답해줍니다. null 객체는 절대 리턴하지 않습니다. null이 의미하는 것이 애매합니다.|
| code    | 에러에 할당되는 유니크한 코드값입니다.|

#### Error Response 객체
<pre>
<code>
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;
    ...

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;
        ...
    }
}
</code>
</pre>
ErrorResponse 객체 입니다. POJO 객체로 관리하면 errorResponse.getXXX(); 이렇게 명확하게 객체에 있는 값을 가져올 수 있습니다. 그 밖에 특정 Exception에 대해서 ErrorResponse 객체를 어떻게 만들 것인가에 대한 책임을 명확하게 갖는 구조로 설계할 수 있습니다.

#### 모든 예외를 핸들링
@ControllerAdvice 어노테이션으로 모든 예외를 한 곳에서 처리할 수 있습니다. 해당 코드의 세부적인 것은 중요하지 않으며 가장 기본적이며 필수적으로 처리하는 코드입니다. 코드에 대한 이해보다 아래의 설명을 참고하는 게 좋습니다.
<pre>
<code>
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     *  javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     *  주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * @ModelAttribut 으로 binding error 발생시 BindException 발생한다.
     * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error("handleBindException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        final ErrorResponse response = ErrorResponse.of(e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.HANDLE_ACCESS_DENIED.getStatus()));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
</code>
</pre>
- handleMethodArgumentNotValidException
  - avax.validation.Valid or @Validated 으로 binding error 발생시 발생한다. )
  - HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생 주로 @RequestBody, @RequestPart 어노테이션에서 발생
- handleBindException
  - @ModelAttribut 으로 binding error 발생시 BindException 발생한다.
- MethodArgumentTypeMismatchException
  - enum type 일치하지 않아 binding 못할 경우 발생
  - 주로 @RequestParam enum으로 binding 못했을 경우 발생
- handleHttpRequestMethodNotSupportedException :
  - 지원하지 않은 HTTP method 호출 할 경우 발생
- handleAccessDeniedException
  - Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
  - Security에서 던지는 예외
- handleException
  - 그 밖에 발생하는 모든 예외 처리, Null Point Exception, 등등
  - 개발자가 직접 핸들링해서 다른 예외로 던지지 않으면 모두 이곳으로 모인다.
- handleBusinessException
  - 비즈니스 요규사항에 따른 Exception
  - 아래에서 자세한 설명 진행

추가로 스프링 및 라이브러리 등 자체적으로 발생하는 예외는 @ExceptionHandler 으로 추가해서 적절한 Error Response를 만들고 비즈니스 요구사항에 예외일 경우 BusinessException 으로 통일성 있게 처리하는 것을 목표로 한다. 추가로 늘어날 수는 있겠지만 그 개수를 최소한으로 하는 노력이 필요합니다.

#### Error Code 정의
<pre>
<code>
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "CM001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "CM002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "CM003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "CM004", "Server Error"),
    INVALID_TYPE_VALUE(400, "CM005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "CM006", "Access is Denied"),


    // Member
    EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),

    // Cart
    CART_ITEM_ALREADY_USE(400, "CA001", "CartItem was already used!"),
    CART_ITEM_DUPLICATION(400, "CA002", "CartItem is Duplication!"),
    CART_ITEM_WRONG_PRICES(400, "CA003", "MaxPrics is wrong!"),
    CART_ITEM_END_EVENT_DATE_TIME(400, "CA004", "EndEventDateTime is wrong!"),
    //COUPON_EXPIRE(400, "CO004", "Coupon was already expired")

    // EVENT
    EVENT_WRONG_PRICES(400, "EV001", "MaxPrics is wrong!"),
    EVENT_END_EVENT_DATE_TIME(400, "EV002", "EndEventDateTime is wrong!"),

    // FILE
    FILE_STORAGE_ERROR(400, "FI001", "Could not store file!"),
    FILE_NOT_FOUND_ERROR(400, "FI002", "Could not found file!"),

    //999
    ETC_ERROR(500, "ZZ999", "Etc is Error!")
    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
</code>
</pre>
에러 코드는 enum 타입으로 한 곳에서 관리합니다.
에러 코드가 전체적으로 흩어져있을 경우 코드, 메시지의 중복을 방지하기 어렵고 전체적으로 관리하는 것이 매우 어렵습니다.
에러 메시지는 Common과 각 도메인별로 관리하는 것이 효율적인 관리 방법입니다.

#### Business Exception 처리
> 여기서 말하는 Business Exception은 요구사항에 맞지 않을 경우 발생시키는 Exception을 말합니다. 만약 쿠폰을 사용 하려고 하는데 이미 사용한 쿠폰인 > 경우에는 더 이상 정상적인 흐름을 이어갈수가 없게 됩니다. 이런 경우에는 적절한 Exception을 발생시키고 로직을 종료 시켜야합니다.
>
> 더 쉽게 정리하면 요구사항에 맞게 개발자가 직접 Exception을 발생시키는 것들이 Business Exception 이라고 할수 있습니다.
>
> 유지 보수하기 좋은 코드를 만들기 위해서는 Exception을 발생시켜야 합니다. 쿠폰을 입력해서 상품을 주문했을 경우 상품 계산 로직에서 이미 사용해 버린 > 쿠폰이면 로직을 이어나가기는 어렵습니다.
>
> 단순히 어려운 것이 아니라 해당 계산 로직의 책임이 증가하게 됩니다. 계산 로직은 특정 공식에 의해서 제품의 가격을 계산하는 것이 책임이지 쿠폰이 이미 > 사용 해 경우, 쿠폰이 만료되었을 경우, 제품이 매진 됐을 경우 등등의 책임을 갖게 되는 순간 유지 보수하기 어려운 코드가 됩니다. 객체의 적절한 책임을 > 주기 위해서라도 본인이 처리 못 하는 상황일 경우 적절한 Exception을 발생시켜야 합니다.
<pre>
<code>
public class DeviceController {
    ...
    public void sendShutDown() {
        DeviceHandle handle = getHandle(DEV1);
        // 디바이스 상태를 점검한다.
        if (handle != DeviceHandle.INVALID) {
            // 레코드 필드에 디바이스 상태를 저장한다.
            retrieveDeviceRecord(handle);
            // 디바이스가 일시정지 상태가 아니라면 종료한다.
            if (record.getStatus() != DEVICE_SUSPENDED) {
                pauseDevice(handle);
                clearDeviceWorkQueue(handle);
                closeDevice(handle);
            } else {
                logger.log("Device suspended. Unable to shut down");
            }
        } else {
            logger.log("Invalid handle for: " + DEV1.toString());
        }
    }
    ...
}
</code>
</pre>
if ... else의 반복으로 인해서 sendShutDown 핵심 비즈니스 코드의 이해하기가 어렵습니다.
<pre>
<code>
public class DeviceController {
    ...
    public void sendShutDown() {
        try {
            tryToShutDown();
        } catch (DeviceShutDownError e) {
            logger.log(e);
        }
    }

    private void tryToShutDown() throws DeviceShutDownError {
        DeviceHandle handle = getHandle(DEV1);
        DeviceRecord record = retrieveDeviceRecord(handle);
        pauseDevice(handle);
        clearDeviceWorkQueue(handle);
        closeDevice(handle);
    }

    private DeviceHandle getHandle(DeviceID id) {
        ...
        throw new DeviceShutDownError("Invalid handle for: " + id.toString());
        ...
    }
    ...
}
</code>
</pre>
객체 본인의 책임 외적인 것들은 DeviceShutDownError 예외를 발생시키고 있습니다. 코드의 가독성과 책임이 분명하게 드러나고 있습니다.

#### 비즈니스 예외를 위한 최상위 BusinessException 클래스
최상위 BusinessException을 상속 받는 InvalidValueException, EntityNotFoundExceptuon 등이 있습니다.

- InvalidValueException : 유효하지 않은 값일 경우 예외를 던지는 Excetion
  - 쿠폰 만료, 이미 사용한 쿠폰 등의 이유로 더이상 진행이 못할경우
- EntityNotFoundException : 각 엔티티들을 못찾았을 경우
  - findById, findByCode 메서드에서 조회가 안되었을 경우

최상위 BusinessException을 기준으로 예외를 발생시키면 통일감 있는 예외 처리를 가질 수 있습니다. 비니지스 로직을 수행하는 코드 흐름에서 로직의 흐름을 진행할 수 없는 상태인 경우에는 적절한 BusinessException 중에 하나를 예외를 발생 시키거나 직접 정의하게 됩니다.
<pre>
<code>
@ExceptionHandler(BusinessException.class)
protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
    final ErrorCode errorCode = e.getErrorCode();
    final List<FieldError> errors = e.getErrors();
    final ErrorResponse response = ErrorResponse.of(errorCode, errors);
    return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
}
</code>
</pre>
이렇게 발생하는 모든 예외는 handleBusinessException 에서 동일하게 핸들링 됩니다. 예외 발생시 알람을 받는 등의 추가적인 행위도 손쉽게 가능합니다. 또 BusinessException 클래스의 하위 클래스 중에서 특정 예외에 대해서 다른 알람을 받는 등의 더 디테일한 핸들링도 가능해집니다.
<pre>
<code>
public int addCartItem(CartItemDto cartItemDto) throws Exception {
    String accountId = cartItemDto.getAccountId();

    CartItem cartItem = CartItem.builder()
            .accountId(accountId)
            .itemId(cartItemDto.getItemId())
            .itemQty(cartItemDto.getItemQty())
            .regPerson(accountId)
            .lastModPerson(accountId)
            .build();
    //상품 검색
    Optional<Item> optionalItem = this.itemDao.findById(cartItem.getItemId());
    //상품이 없는경우 Excpetion 발생
    optionalItem.orElseThrow(() -> new CartItemNotFoundException(cartItem.getItemId()));

    return cartDao.add(cartItem);
}
</code>
</pre>
Cart의 addCartItem 메서드입니다. 추가하려는 Item을 존재 여부를 확인하고 예외가 발생하면 적절한 Exception을 발생시킵니다.

#### 컨트롤러 예외 처리
컨틀롤러에서 모든 요청에 대한 값 검증을 진행하고 이상이 없을 시에 서비스 레이어를 호출해야 합니다. 위에서도 언급했듯이 잘못된 값이 있으면 서비스 레이어에서 정상적인 작업을 진행하기 어렵습니다. 무엇보다 컨틀롤러의 책임을 다하고 있지 않으면 그 책임은 자연스럽게 다른 레이어로 전해지게 되며 이렇게 넘겨받은 책임을 처리하는데 큰 비용과 유지보수 하기 어려워질 수밖에 없습니다.

컨트롤러의 중요한 책임 중의 하나는 요청에 대한 값 검증이 있습니다. 스프링은 JSR 303 기반 어노테이션으로 값 검증을 쉽고 일관성 있게 처리할 수 있도록 도와줍니다. 모든 예외는 @ControllerAdvice 선언된 객체에서 핸들링 됩니다. 컨트롤러로 본인이 직접 예외까지 처리하지 않고 예외가 발생하면 그냥 던져버리는 패턴으로 일관성 있게 개발할 수 있습니다.
<pre>
<code>
@RestController
@RequestMapping(value="/api/cart", produces = MediaTypes.HAL_JSON_VALUE)
public class CartController {
    @Autowired
    private CartService cartService;

    ...
    @PutMapping(value = "/{itemId}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity saveCartItem(@PathVariable String itemId
            , @RequestBody @Valid CartItemDto reqCartItemDto
            , @CurrentUser Account currentUser) throws Exception {
        reqCartItemDto.setAccountId(currentUser.getAccountId());

        cartService.saveCartItem(reqCartItemDto);

        ResponseResutl<CartItemDto> resResult = new ResponseResutl<CartItemDto>();
        resResult.setMessage("저장 되었습니다.");
        resResult.setResult(reqCartItemDto);

        return ResponseEntity.ok(reqCartItemDto);
    }
    ...
}
</code>
</pre>
<pre>
<code>
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class CartItemDto {
    private String accountId;
    @NotEmpty
    private String itemId;
    @Min(0)
    private int itemQty;
}
</code>
</pre>
CartItemDto 중에서 유효하지 않은 값이 있을 때 @Valid 어노테이션으로 예외를 발생시킬 수 있습니다. 이 예외는 @ControllerAdvice에서 적절하게 핸들링 됩니다. @NotEmpty, @Min 외에도 다양한 어노테이션들이 제공됩니다.

#### Try Catch 전략
기본적으로 예외가 발생하면 로직의 흐름을 끊고 종료 시켜야 합니다물론 예외도 있지만, 최대한 예외를 발생시켜 종료하는 것을 지향해야 한다고 생각합니다.)
<pre>
<code>
try {
    // 비즈니스 로직 수행...
}catch (Exception e){
    e.printStackTrace();
}
</code>
</pre>
위 같은 코드는 지양하야 하는 패턴입니다. 최소한의 양심으로 e.printStackTrace(); 로그라도 출력했지만 이미 예외가 발생했음에도 불가하고 다음 로직을 실행하게 됩니다. 이런 식의 try catch를 최대한 지양해야 합니다.
하지만 Checked Exception 같은 경우에는 예외를 반드시 감싸야 하므로 이러한 경우에는 try catch를 사용해야 합니다.
<pre>
<code>
try {
    // 비즈니스 로직 수행...
}catch (Exception e){
    e.printStackTrace();
    throw new XXX비즈니스로직예외(e);
}
</code>
</pre>
try catch를 사용해야 하는 경우라면 더 구체적인 예외로 Exception을 발생시키는 것이 좋습니다. 간단하게 정리하면

1. try catch를 최대한 지양해라
2. try catch로 에러를 먹고 주는 코드는 지양해라(이런 코드가 있다면 로그라도 추가해주세요…)
3. try catch를 사용하게 된다면 더 구체적인 Exception을 발생시키는 것이 좋다.


[참고 자료 : Spring Guide - Exception 전략][exceptionGuideLink]

[exceptionGuideLink]: https://cheese10yun.github.io/spring-guide-exception/ "참고 자료 : Spring Guide - Exception 전략"

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