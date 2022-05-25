package com.fse.product.query.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTrxnRepository extends JpaRepository<Transaction,String> {
}
