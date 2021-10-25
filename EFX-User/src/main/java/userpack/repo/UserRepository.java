package userpack.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import userpack.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
