package cz.uhk.ppro.project.springSecurity;


import java.util.ArrayList;
import java.util.List;


import cz.uhk.ppro.project.model.Worker;
import cz.uhk.ppro.project.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    TestService testService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Worker worker = this.testService.findUserAccount(login);

        if (worker == null) {
            System.out.println("Worker not found! " + login);
            throw new UsernameNotFoundException("Worker " + login + " was not found in the database");
        }

        System.out.println("Found Worker: " + worker);

        List<String> roleNames = this.testService.getRoleNames(worker.getId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = new User(worker.getLogin(), worker.getPassword(), grantList);

        return userDetails;
    }

}