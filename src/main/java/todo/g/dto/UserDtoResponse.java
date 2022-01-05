package todo.g.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import todo.g.model.User;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoResponse {

//    @NotEmpty(message = "not empty")
//    @Size(min = 3
//            ,max=20,
//            message = "size must be 3")
    private String name;
    private String username;
    private String email;
    private String password;

    public static UserDtoResponse UserToDto(User user){
        return UserDtoResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }


}
