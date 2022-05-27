package com.yazykov.currencyservice.security.appuser;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_users")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private AppUserRole role;
    private Boolean banned;
    private Boolean enabled;
    private String baseCurrency;
    private BigDecimal amount;
    private LocalDateTime confirmedAt;

    public AppUser(String username,
                   String password,
                   String email,
                   AppUserRole role,
                   Boolean banned,
                   Boolean enabled,
                   String baseCurrency,
                   BigDecimal amount) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.banned = banned;
        this.enabled = enabled;
        this.baseCurrency = baseCurrency;
        this.amount = amount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !banned;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppUser appUser = (AppUser) o;

        if (username != null ? !username.equals(appUser.username) : appUser.username != null) return false;
        if (password != null ? !password.equals(appUser.password) : appUser.password != null) return false;
        if (email != null ? !email.equals(appUser.email) : appUser.email != null) return false;
        if (role != appUser.role) return false;
        if (banned != null ? !banned.equals(appUser.banned) : appUser.banned != null) return false;
        if (enabled != null ? !enabled.equals(appUser.enabled) : appUser.enabled != null) return false;
        if (baseCurrency != null ? !baseCurrency.equals(appUser.baseCurrency) : appUser.baseCurrency != null)
            return false;
        return amount != null ? amount.equals(appUser.amount) : appUser.amount == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (banned != null ? banned.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (baseCurrency != null ? baseCurrency.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
