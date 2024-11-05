package com.ProyectoCoder.ProyectoCoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoCoder.ProyectoCoder.model.Bebidas;

@Repository
public interface BebidasRepository extends JpaRepository<Bebidas, Long> {

}
