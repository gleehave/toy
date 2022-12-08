# Spring and toyproject

- 내가 지금 모르고 있는 것들이 뭘까?
- 배웠지만 까먹은 것들은 복습하고, 새로운 것들은 찾아보자

@RequiredArgsConstructor
- 의존성 주입으로 constructor, setter, field가 있다.
- 생성자 주입의 단점은 긴 코드를 통해 작성하는것인데, lombok으로 생성자를 주입할 수 있다. 
- `final` 혹은 `@NotNull`을 붙인 필드의 생성자를자동으로 생성해준다.
```
@Servie
@RequiredArgsConstructor
public class exServiceImpl implements exService{
    private final exRepository repository;
    private final Utils commonUtils;
}
```
@NoArgsConstructor
- 파라미터가 없는 기본 생성자를 생성해준다.
@AllArgsConstuctor
- 모든 필드값을 파라미터로 받는 생성자를 만들어 준다.
```
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User{
    private Long id;
    
    @NotNull
    private String username;
    
    @NotNull
    private String password;
    
    private int[] scores;
}
User user1 = new User();
User user2 = new User("lee","1234");
User userr3 = new User(1L, "lee","1234", null);
```
@Data
- @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode를 한꺼번에 설정해준다.

@RequestBody, @ResponseBody
- 각각의 HTTP요청 바디를 자바객체로 변환하고, 자바객체를 다시 HTTP 응답 바디로 변환한다.
- @RequestBody를 통해서 자바객체로 conversion을 하는데, 이때 HttpMessageConverter를 사용한다. 
- @ResponseBody 가 붙은 파라미터에는 HTTP 요청의 분문 body 부분이 그대로 전달된다.

```
// HTTP 요청 바디내용을 자바 객체로 변환해서 매핑된 메소드 파라미터로 전달
@RequestMapping(value = "/ajaxTest.do")
public String ajaxTest(@RequestBody UserV0 getUserV0) throws Exception{
    System.out.println(getUserV0.getId());
    return "test/login.titles";
}

// @Responsebody를 사용하면 HTTP 요청 body를 자바 객체로 전달받을 수 있다.
@ResponseBody
@RequestMapping(...)
publid UserV0 ajaxTest() throws Exception{
    UserV0 userV0 = new UserV0();
    userV0.setId("test");
    
    return userV0;
}
```
@RestController
- 리턴값에 자동으로 @ResponseBody가 붙어있으므로, 자동으로 으답데이터에 자바 객체가 매핑되어 전달된다.

ResponseEntity
- Spring에는 HTTP Request, Response를 나타내기 위해 제공하는 HttpEntity 클래스가 있음.
- HttpEntity는 HttpHeader와 HttpBody를 포함하는 클래스임.
- HttpEntity를 상속하여 추가적으로 HttpStatus 속성을 더 가지는 클래스가 RequestEntity, ResponseEntity 클래스이다.

```
@GetMapping(...)
public ResponseEntity<String> funcGet(){
    return ResponseEntity.ok("hello");
}

@GetMapping(...)
public ResponseEntity<String> funcGet(){
    return ResponseEntity.("hello", HttpStatus.OK);
}

@ResponseStatus(value = HttpStatus.OK)
@GetMapping(...)
public String funcGet(){
    return "hello";
}

@ResposneStatus(value = HttpStatus.CREATED)
@GetMapping(...)
public String funcGet(){
    return "hello";
}

//Exception으로 활용
// 기본적으로 Exception발생시, 500에러인데, @ResponseStatus를 통해서 상태코드를 새롭게 반환함.
@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Slf4j
public class CustomException extends RuntimeException{
    public CustomException(String messgae){
        super(message);
        log.error(message);
    }
}
```

@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
- name : 매핑할 테이블의 이름을 지정한다.
- catalog: DB의 catalog를 매핑한다.
- schema: DB 스키마와 매핑한다.
- uniqueConstraint: DDL 쿼리를 작성할 때, 제약조건을 생성한다.
- `spring.jpa.hibernate.ddl-auto=update`를 지정하면, 자동으로 테이블을 생성할 수 있다.
    - create : 기존테이블을 삭제하고 새로 생성 (매우 위험.. 기존 데이터 날라간다.)
    - create-drop: 애플리케이션 종료될 때, 제거
    - update: 변경된 사항만 수정
    - none: 아무 동작 하지 않음.

@Service
- 해당 클래스를 루트 컨테이너에 빈(Bean)객체로 생성해준다.

```
private String fieldValue;

public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
    super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
```
- String.format? 이거 뭐지?
    - String 의 static 메서드인 format 메서드는 문자열의 형식을 설정하는 메서드.

```
String str = "tete";

System.out.println(String.format("%s_", str));
System.out.println(String.format("%12s_", str));
System.out.println(String.format("%-12s_", str));
System.out.println(String.format("%.2s_", str));
System.out.println(String.format("%-12.2s_", str));
System.out.println(String.format("%12.2s_", str));

<출력>
tete_
        tete_
tete        _
te_
te          _
          te_
```

```
import org.springframework.data.jpa.repository.JpaRepository;
import toproject.toy.entity.Post;

public class PostRepository extends JpaRepository<Post, Long> {
```
- `extends JpaRepository<Post, Long>` ? 이거 처음 봄
    - Spring Data JPA는 JPA의 구현체인 Hibernate를 이용하기 위한 API를 제공. 그 중 JpaRepository 인터페이스가 있다.
    - insert : save(엔티티 객체)
    - select : findById(키 타입), getOne(키 타입)
    - update : save(엔티티 객체)
    - delete : deleteById(키 타입), delete(엔티티 객체)

```
 Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
return mapToDTO(post);
```
- orElseThrow() ? 이거는 Stream()으로 제공하는 기능인거 같은데?
    - Optional 객체의 유무를 판단하고 예외 처리하기 위해서 orElseThrow를 통해 Optional에서 원하는 객체를 바로 얻거나 예외를 던질 수 있다.

```
public class ItemService{
    Item findItemById_if(Long itemId){
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()){
            ...
            return item.get();
        }
        return null;
    }
    
    Item findItemById_orElseThrow(Long itemId){
        return itemRepository.findById(itemId).orElseThrow(()->new NoSuchElementException());
    }
}
```

@SpringBootApplication
1. @ComponentScan
    - 해당 애노테이션이 선언된 하위 패키지에서 Annotation을 찾아서 Bean으로 등록
2. @EnableAutoConfigure
 <img width="506" alt="스크린샷 2022-12-04 오후 4 01 27" src="https://user-images.githubusercontent.com/91510831/205478668-25304989-ec4e-4be1-938b-159e9de27621.png">

@configuration
- @Configuration은 Bean을 등록하는 Java 설정파일
- 즉, spring은 @Configuration에 작성된 내용을 Bean으로 등록한다.
<img width="848" alt="스크린샷 2022-12-04 오후 4 04 03" src="https://user-images.githubusercontent.com/91510831/205478758-11bc8f82-5a58-4842-a1de-1335ed763f77.png">

@Bean
- 개발자가 직접 제어가 불가능한 외부 라이브러리를 Bean으로 만들려고 할 때 사용
```
@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

@Bean
@Override
public AuthenticationManger authenticationManagerBean() throws Exception{
    return super.authenticationManagerBean();
}
```

@Valid
- @RestController를 이용하여 @RequestBody 객체를 사용자로부터 가져올 때, 들어오는 값들을 검증할 수 있다.
- @Valid의 검증에서는 BadRequest를 반환하는데, 이를 custom하여 에러핸들링할 수 있다.
```
@NotBlank
@NotEmpty
@NotNull
@Null
@DecimalMax : 지정된 최대 값보다 작거나 같아야 한다.
@DecimalMin : 지정된 최소 값보다 크거나 같아야 한다.
@Max : 지정된 최대 값보다 작거나 같아야 한다.
@Min : 지정된 최소 값보다 크거나 같아야 한다.
@Positive : 양수인 값이다.
@PositiveOrZero : 0이거나 양수인 값이다.
@Negative : 음수인 값이다.
@NegativeOrZero : 0이거나 음수인 값이다.
@Future : Now 보다 미래의 날짜, 시간이어야 한다.
@FutureOrPresent : Now 거나 미래의 날짜, 시간이어야 한다.
@Past : Now 보다 과거 의의 날짜, 시간이어야 한다.
@PastOrPresent : Now 거나 과거의 날짜, 시간이어야 한다.
@Pattern : 지정한 정규식과 대응되는 문자열 이어야 한다. Java의 Pattern 패키지의 컨벤션을 따른다

// 예시
@Pattern(regexp="^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$")
private String pattern;
```

@Column(name = "content", nullable = false)
- 객체 필드와 DB 테이블 컬럼을 맵핑한다.
<img width="593" alt="스크린샷 2022-12-04 오후 4 19 14" src="https://user-images.githubusercontent.com/91510831/205479151-0b4220fe-5fd9-43f1-9ffe-98241dc79129.png">

@ManyToOne(fetch = FetchType.LAZY)
- 만약, 여러계좌가 1명의 사용자에게서 사용될 수 있다. @ManyToOne Annotation을 추가한다.
- 연관관계 주인임을 나타내고 물리 테이블에 있는 user_id 컬럼을 통해 user필드를 조작하기 위해 @JoinColumn을 붙인다.

```
// ManyToOne 단방향
@Entity //여기가 다(many)
@Table(name="TABLE_ACCOUNT")
public class Account{
    @Id
    @GeneratedValue
    @Column(name="account_id")
    private Long id;
    
    @Column(name="account_name")
    private String accountName;
    
    private int deposit;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}

@Entity //여기가 일(1)
@Table(name="TABLE_USER")
public class User{
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name")
    private String userName;
```
```
// user가 관계의 주인이고, User Entity에서 Account Entity를 조회할 수 있도록 양방향 매핑
@Entity 
@Table(name="TABLE_USER")
public class User{
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name")
    private String userName;
    
    @OneToMany(mappedBy = "user")
    private List<Account> accounts = new ArrayList<>();
    
    public void addAccount(Account account){
        this.accounts.add(account);
        account.setUser(this);
    }
```

@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
- 만약, User Entity의 accounts 필드를 주인으로 정하기 위해서는 어떡해 해야할까?
    - @OneToMany를 적용한다.
- User Entity에 있는 accounts필드의 데이터를 채울 때, Account 테이블의 user_id 컬럼을 통해서 데이터를 가져온다.
- @ManyToOne은 항상 주인이 됨을 참고!
```
@Entity
@Table(name="TABLE_ACCOUNT")
public class Account{
    @Id @GeneratedValue
    @Column(name="account_id")
    private Long id;
    
    @Column(name="account_name")
    private String accountName;
    
    private int deposit;
}

@Entity
@Table(name="TABLE_USER")
public class User{
    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;
    
    @Column(name="user_name")
    private String userName;
    
    @OneToMany
    @JoinColumn(name="user_id")
    private List<Account> accounts = new ArrayList<>();
}
```

<img width="676" alt="스크린샷 2022-12-04 오후 4 45 41" src="https://user-images.githubusercontent.com/91510831/205479951-550e65b2-649c-4c63-849b-f63304e19c96.png">

@ControllerAdvice
- 모든 @Controller에서 발생할 수 있는 예외를 잡아서 처리해주는 annotation이다.
- 별도의 속성값이 없이 사용하면 모든 패키지 전역에 있는 컨트롤러를 담당하게 된다.
```
@RestControllerAdvice
public class MyAdvice{
    @ExceptionHandler(CustomException.class)
    public String custom(){
        return "hello custom";
    }
}
```

@ExceptionHandler
- @Controller, @RestController가 적용된 Bean에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 기능을 제공한다.
    - Controller, RestController에만 적용가능하다. (@Service같은 빈에서는 안됨.)
    - @ExceptionHandler를 등록한 Controller에만 적용된다. 다른 Controller에서 NullPointerException이 발생하더라도 예외를 처리할 수 없다.
```
//MyRestController에 해당하는 Bean에서 NullPointerException이 발생한다면, 
// @ExceptionHandler(NullPointerException.class)가 적용된 메서드가 호출될 것이다.

@RestController
public class MyRestController{
    ....
    @ExceptionHandler(NullPointerException.class)
    public Object nullex(Exception e){
        System.err.println(e.getClass());
        return "myService";
    }
}
```

```
// WebReqyest?? 
public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest)
```
- WebRequest는 어디에서 발생했는지에 대한 정보가 담겨있다.

@EnableWebSecurity
- WebSecurityConfigureerAdapter를 상속받은 config 클래스에 @EnableWebSecurity를 붙이면 SpringSecurityFilterChain이 자동으로 포함된다.
- 즉, 스프링시큐리티 사용을 위한 어노테이션선언 정도로 생각하면 된다.
<img width="526" alt="스크린샷 2022-12-04 오후 5 04 20" src="https://user-images.githubusercontent.com/91510831/205480483-e14ceac7-b008-44ca-ba51-4a741e8c59f0.png">
https://devuna.tistory.com/59 (참고)

- hasRole(), hasAnyAuthority() : 특정 권한을 가지는 사용자만 접근할 수 있다.
- hasAuthority(), hasAnyAuthority() : 특정 권한을 가지는 사용자만 접근할 수 있다.
- hasIpAddress() : 특정 IP주소를 가지는 사용자만 접근할 수 있다.
- permitAll(), denyAll() : 접근을 전부 허용하거나 전부 제한한다.
- rememberMe() : 로그인한 사용자만 접근할 수 있다.
- anonymous() : 인증되지 않은 사용자가 접근할 수 있다.
- authenticated() : 인증된 사용자만 접근할 수 있다.

## Spring Security
- Authentication (인증)
- Authroization (인가)
- Principal (접근 주체) : 보호받는 Resource에 접근하는 대상
- Credential (비밀번호) : Resource에 접근하는 대상의 비밀번호

이미지 (첨부)