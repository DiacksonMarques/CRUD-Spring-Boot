package com.crmApre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crmApre.model.Client;



@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
