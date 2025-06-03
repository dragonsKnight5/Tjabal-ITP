package PollardCreations.Tjabal.Services;

import PollardCreations.Tjabal.Model.User;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author james
 */
public class SecurityUser implements UserDetails
{
    private final User student;

    public SecurityUser(User appUser) 
    {
        this.student = appUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        String role = new String();
        if (student.getAdministrator())
        {
            role = "Administrator";
        }
        else if (student.getStudent())
        {
            role = "Student";
        }
        else if (student.getTutor())
        {
            role = "tutor";
        }
        
        String prefixedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;
        return List.of(new SimpleGrantedAuthority(prefixedRole));
    }

    @Override
    public String getPassword() 
    {
        return student.getPassword();
    }

    @Override
    public String getUsername() 
    {
        return student.getUserID();
    }

    @Override public boolean isAccountNonExpired() 
    { 
        return true; 
    }
    
    @Override public boolean isAccountNonLocked() 
    { 
        return true; 
    }
    
    @Override public boolean isCredentialsNonExpired() 
    { 
        return true; 
    }
    
    @Override public boolean isEnabled() 
    { 
        return true; 
    }
}
