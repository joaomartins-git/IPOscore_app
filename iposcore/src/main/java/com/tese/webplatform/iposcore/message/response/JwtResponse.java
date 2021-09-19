package com.tese.webplatform.iposcore.message.response;

import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
 
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private String username;
  // private String email;
  private Collection<? extends GrantedAuthority> authorities;
 
  public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
    this.token = accessToken;
    this.username = username;
    this.authorities = authorities;
    // this.email = email;
  }
 
  public String getAccessToken() {
    return token;
  }
 
  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }
 
  public String getTokenType() {
    return type;
  }
 
  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }
 
  public String getUsername() {
    return username;
  }
 
  public void setUsername(String username) {
    this.username = username;
  }

  // public String getEmail(){
  //   return email;
  // }

  // public void setEmail(String email){
  //   this.email = email;
  // }
  
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }
}