package xavier.app.Login.repository;

import org.springframework.data.repository.CrudRepository;
import xavier.app.Login.model.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findById(long id);
}
