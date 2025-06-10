package PollardCreations.Tjabal.Repositories;

import PollardCreations.Tjabal.Model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author james
 */

@EnableJpaRepositories
//@Repository
public interface UserRepository extends JpaRepository<User, Integer> 
{
    Optional<User> findByUserID(String username);
}