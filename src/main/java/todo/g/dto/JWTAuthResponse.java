package todo.g.dto;


//5

import lombok.Data;
import todo.g.model.User;

@Data
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private User user;
    private String message;
    private LoginDto loginDto;
    private Long idReturn;


    public JWTAuthResponse(String accessToken,User user,String message,Long idReturn) {
        this.accessToken = accessToken;
        this.user = user;
        this.message = message;
        this.idReturn = idReturn;
    }

    public JWTAuthResponse(String accessToken,User user) {
        this.accessToken = accessToken;
        this.user = user;

    }

    public JWTAuthResponse(String accessToken, String message) {
        this.message = message;
        this.accessToken = accessToken;
    }
    public JWTAuthResponse(String accessToken,LoginDto loginDto,Long idReturn) {
        this.accessToken = accessToken;
        this.loginDto = loginDto;
        this.idReturn= idReturn;
    }
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
//
//    public void setTokenType(String tokenType) {
//        this.tokenType = tokenType;
//    }
//
//    public String getAccessToken() {
//        return accessToken;
//    }
//
//    public String getTokenType() {
//        return tokenType;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}
