package org.example.mealmatesapi.payload.request;


import jakarta.validation.constraints.NotBlank;

public class TokenRefreshRequest {
    @NotBlank
    private String refresh;

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refreshToken) {
        this.refresh = refreshToken;
    }
}