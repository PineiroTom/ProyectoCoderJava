package com.ProyectoCoder.ProyectoCoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ProyectoCoder.ProyectoCoder.model.Cliente;

import jakarta.transaction.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cliente_bebidas (cliente_id, bebidas_id) VALUES (?1, ?2)", nativeQuery = true)
    void insertUserDomicilio(Long clientId, Long bebidasId);
}
