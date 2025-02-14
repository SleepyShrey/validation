package br.com.darchanjo.examples.repo;

import br.com.darchanjo.examples.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

    @Query("SELECT u FROM Users u WHERE u.name = :name")
    public List<Users> findByName(@Param("name") String name);

}
