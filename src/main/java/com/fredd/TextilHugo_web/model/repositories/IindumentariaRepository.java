package com.fredd.TextilHugo_web.model.repositories;

import com.fredd.TextilHugo_web.model.entities.Indumentaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IindumentariaRepository extends JpaRepository<Indumentaria, Long> {
}
