package org.example.mealmatesapi.payload.response;


public class TokenRefreshResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public TokenRefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    // Getter for accessToken
    public String getAccessToken() {
        return accessToken;
    }

    // Setter for accessToken
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    // Getter for refreshToken
    public String getRefreshToken() {
        return refreshToken;
    }

    // Setter for refreshToken
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // Getter for tokenType
    public String getTokenType() {
        return tokenType;
    }

    // Setter for tokenType
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
