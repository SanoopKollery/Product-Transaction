package com.fse.product.query.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ProductTrxnRepository extends JpaRepository<Transaction,String> {
  public Transaction findByProductIdAndEmail(int productId,String email);
}
