package com.truelanz.truelanzcommerce.projections;

public interface UserDetailsProjection {

    String getUserName();
    String getPassword();
    Long getRoleId();
    String getAuthority();    
}
