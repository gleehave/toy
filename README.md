# Spring and toyproject

- 내가 지금 모르고 있는 것들이 뭘까?
- 배웠지만 까먹은 것들은 복습하고, 새로운 것들은 찾아보자

@RequestArgsConstructor
@NoArgsConstructor
@AllArgsConstuctor
@RequestBody
@ResponseStauts
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
@Service


```
#hibernate properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect

spring.jpa.hibernate.ddl-auto=update
```
- spring.jpa.hibernate.ddl-auto=update 이거 옵션값들 바꾸면? none, create 있었던거 같은데?

```
private String fieldValue;

public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
    super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
```
- String.format? 이거 뭐지?

```
import org.springframework.data.jpa.repository.JpaRepository;
import toproject.toy.entity.Post;

public class PostRepository extends JpaRepository<Post, Long> {
```
- `extends JpaRepository<Post, Long>` ? 이거 처음 봄

```
 Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
return mapToDTO(post);
```
- orElseThrow() ? 이거는 Stream()으로 제공하는 기능인거 같은데?














