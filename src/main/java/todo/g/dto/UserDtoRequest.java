package todo.g.dto;


import lombok.Builder;
import lombok.Data;
import todo.g.model.User;

@Builder
@Data
public class UserDtoRequest {

//    @NotEmpty(message = "not empty")
//    @Size(min = 3
//            ,max=20,
//            message = "size must be 3")
    private String name;
    private String username;
    private String email;
    private String password;

    public static User dtoToUser(UserDtoRequest dto){
        return User.builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }


}
