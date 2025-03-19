package com.example.blogpost.berkayerol.model;

import com.example.blogpost.berkayerol.enums.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder //Tasarım ve kolay nesne için
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User implements UserDetails {
    private Role role;

    @Id
    private String ID;

    @Valid
    @NotBlank(message = "First name not be null")
    @Size(min = 2, max = 40)
    private String firstName;

    @NotBlank(message = "Last name not be null")
    @Size(min = 2,max = 40)
    private String lastName;

    //@ValidateUserEmail
    @NotBlank(message = "e-mail not be null")
    @Size(min = 2,max = 50)
    private String email;

    @NotBlank(message = "password not be null")
    @Size(min = 5,max = 20)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name())); //normal yetki verir
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { //hesabın süresi dolmuş mu ?
        return true; //dolmamış
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
