package com.springcommerce.midterm.config;

import com.springcommerce.midterm.model.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerDetails implements UserDetails {

    private Customer customer;
    List<SimpleGrantedAuthority> authorities=null;
    public Customer getUser() {
        return customer;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities)
    {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : customer.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authorities;
    }

    public Long getId() {
        return customer.getId();
    }
    @Override
    public String getPassword() {
        return customer.getPassword();
    }


    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
