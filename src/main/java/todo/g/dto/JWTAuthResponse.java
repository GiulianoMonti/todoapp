package todo.g.dto;


//5
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserDtoResponse user;

    public JWTAuthResponse(String accessToken,UserDtoResponse user) {
        this.accessToken = accessToken;
        this.user = user;

    }
    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
