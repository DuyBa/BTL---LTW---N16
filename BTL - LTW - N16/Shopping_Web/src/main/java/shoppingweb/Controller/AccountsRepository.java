package shoppingweb.Controller;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingweb.Model.Account;

public interface AccountsRepository extends JpaRepository<Account, String> {

}
