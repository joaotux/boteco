package com.originmobi.boteco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.originmobi.boteco.model.Comanda;

public interface ComandaRepository extends JpaRepository<Comanda, Long> {

}
