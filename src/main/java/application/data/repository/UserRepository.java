package application.data.repository;

import application.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select count(u.id) from tbl_user u")
    long getTotalUsers();
}
