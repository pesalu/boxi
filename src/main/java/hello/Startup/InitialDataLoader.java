package hello.Startup;

import hello.Dao.AccountRepository;
import hello.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Account accountAdmin = new Account("pekka", passwordEncoder.encode("puupaa"), "ROLE_ADMIN");
        Account accountUser = new Account("kalle", passwordEncoder.encode("kustaa"), "ROLE_USER");


        //accountRepository.save(accountAdmin);
        //accountRepository.save(accountUser);
        saveAccountNotFoundFromDb(accountAdmin);
        saveAccountNotFoundFromDb(accountUser);

        alreadySetup = true;
    }

    @Transactional
    private void saveAccountNotFoundFromDb(Account account){
        Account daccount = accountRepository.findByUsername(account.getUsername());
        if (daccount == null) {
            accountRepository.save(account);
        }
    }

/*    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }*/
}