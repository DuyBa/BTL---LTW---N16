package shoppingweb.Controller;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingweb.Model.Bill;

public interface BillRepository extends JpaRepository<Bill, String> {

}
