package PollardCreations.Tjabal.Services;

import PollardCreations.Tjabal.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author james
 */
@Service
public class StudentDetailsService implements UserDetailsService
{
    private final UserRepository repository;

    public StudentDetailsService(UserRepository repository) 
    {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        return repository.findByUserID(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
