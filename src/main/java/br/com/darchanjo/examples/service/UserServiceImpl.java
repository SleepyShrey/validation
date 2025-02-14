package br.com.darchanjo.examples.service;

import br.com.darchanjo.examples.entity.Users;
import br.com.darchanjo.examples.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    public final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public List<Users> checkUser(String name) {

        return userRepo.findByName(name);  // Simplified to directly accept the name
    }

    @Override
    public Users checkUser(long id) {

        if(userRepo.existsById(id)){
            return  userRepo.getReferenceById(id);
        }
        return null;
    }
}
