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














