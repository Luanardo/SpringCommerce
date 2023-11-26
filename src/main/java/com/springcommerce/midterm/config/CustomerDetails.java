package com.springcommerce.midterm.config;

import com.springcommerce.midterm.model.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDetails implements UserDetails {

    private Customer customer;
    List<SimpleGrantedAuthority> authorities;
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

    public CustomerDetails(Customer customer) {
        this.customer = customer;
        authorities = customer.getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        authorities.add(new SimpleGrantedAuthority(customer.getRole()));

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
