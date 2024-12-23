MapStruct is a Java library used to simplify object-to-object mapping, particularly useful in applications with layers like controller, service, and database (though you're not using a DB for now). Here's why and how to use MapStruct:  

### **Why MapStruct?**
1. **Simplifies Code:** Avoids boilerplate code for mapping fields between classes (e.g., `DTO -> Entity` or `Entity -> Model`).
2. **Type-Safe:** Ensures compile-time safety for mapping logic.
3. **Customizable:** Allows custom mappings if field names/types differ.
4. **Performance:** It generates mapping code at compile-time, leading to faster execution compared to reflection-based mappers.

### **Setup MapStruct in Spring Boot 3**
1. **Add Dependency:** Include MapStruct in your `pom.xml` along with the annotation processor.
   ```xml
 <properties>
		<mapstruct.version>1.5.5.Final</mapstruct.version>
	</properties>


    <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>${mapstruct.version}</version>
        </dependency>
		  <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>${mapstruct.version}</version>
        </dependency>

         <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>${mapstruct.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        ```


2. **Enable Annotation Processing:** Ensure your IDE enables annotation processing (e.g., in IntelliJ IDEA, go to `Settings > Build, Execution, Deployment > Compiler > Annotation Processors` and enable it).

---

### **Usage Example**
We'll create a simple use case for a mapping scenario:
- A `UserDTO` (Data Transfer Object) from the client-side.
- A `User` model in the service layer.

#### **Step 1: Create Models**
**`UserDTO`**
```java
public class UserDTO {
	private String username;
	private String email;

	  // Getters and Setters
}
```

**`User`**
```java
public class User {
  private Long id;
	private String username;
	private String password;
	private String name;
	private String email;

    // Getters and Setters
}
```

---

#### **Step 2: Create Mapper**
**`UserMapper`**
```java
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	// Map User -> UserDTO
	UserDTO toUserDTO(User user);
}

```

---



#### **Step 3: Controller Layer**
**`UserController`**
```java

@RestController
@RequestMapping("/api/users")
public class UserController {

	@PostMapping("/dto")
	public UserDTO getUserDTO(@RequestBody User user) {
		return UserMapper.INSTANCE.toUserDTO(user);
	}
	
//Request JSON :
//{
//    "id": 1,
//    "username": "ashfaq123",
//    "password": "mypassword",
//    "name": "Ashfaq",
//    "email": "ashfaq@example.com"
//}
//
//
//Response JSON :
//{
//	"username": "ashfaq123",
//	"email": "ashfaq@example.com"
}

```

---

### **Run the Application**
- Use Postman or similar tools to send a POST request:
  
```json
  {
    "id": 1,
    "username": "ashfaq123",
    "password": "mypassword",
    "name": "Ashfaq",
    "email": "ashfaq@example.com"
  }
```

  **Response**:


````json
 {
    "username": "ashfaq123",
    "email": "ashfaq@example.com"
     }
````

---

## Variable Scenario:
 MapStruct works great when the **variable names** in your entity and DTO are the same. But if they’re different, we need to guide it a bit. Here's how it works:

---

### **Case 1: When Variable Names Are the Same**
If the names are identical (e.g., `username` in both `User` and `UserDTO`), MapStruct automatically maps them. we don’t need to do anything extra.

---

### **Case 2: When Variable Names Are Different**
If the names **don’t match**, MapStruct needs some help. we use the `@Mapping` annotation to tell it explicitly how to map those fields.

For example:  
Imagine this mismatch:
- `User` class has `email`.
- `UserDTO` has `userEmail`.

You define the mapping like this:
```java
@Mapper
public interface UserMapper {
    @Mapping(source = "email", target = "userEmail")
    UserDTO toUserDTO(User user);
}
```

MapStruct now knows:  
- `email` (from `User`) maps to `userEmail` (in `UserDTO`).

---

### **Handling Multiple Mismatches**
If many fields have different names, you just add more `@Mapping` annotations:
```java
@Mapper
public interface UserMapper {
    @Mapping(source = "email", target = "userEmail")
    @Mapping(source = "name", target = "fullName")
    UserDTO toUserDTO(User user);
}
```

---

### **What If We Forget to Map Mismatched Fields?**
- MapStruct **skips** them.
- They’ll be `null` or default values in the target object.

---

### **Complete Example for Your Scenario**
If `UserDTO` has `userEmail` instead of `email`, the mapper will look like this:
```java
public class UserDTO {
    private String username;
    private String userEmail; // Different name than 'email'
    // Getters and Setters
}
```

Mapper:
```java
@Mapper
public interface UserMapper {
    @Mapping(source = "email", target = "userEmail") // Handle mismatch
    UserDTO toUserDTO(User user);
}
```

Generated code (by MapStruct) will look like:
```java
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setUserEmail(user.getEmail()); // Resolves mismatch
        return dto;
    }
}
```

---

### **When Should We Worry About Names?**
If most field names match, we don’t need to do anything extra. Just add `@Mapping` for mismatched fields. If names are completely different across all fields, it’s a little more work but still better than writing the entire mapping manually.



