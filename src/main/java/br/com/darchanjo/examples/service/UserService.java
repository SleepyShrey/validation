package br.com.darchanjo.examples.service;

import br.com.darchanjo.examples.entity.Users;
import java.util.List;

public interface UserService {
        public List<Users> checkUser(String name);
        public Users checkUser(long id);
}