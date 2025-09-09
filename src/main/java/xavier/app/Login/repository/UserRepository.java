package xavier.app.Login.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import xavier.app.Login.model.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findById(long id);

    @Query(value = "SELECT * FROM users u WHERE u.email = :email AND u.password = :password", nativeQuery = true)
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
