package com.fredd.TextilHugo_web.model.repositories;

import com.fredd.TextilHugo_web.model.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompraRepository extends JpaRepository<Compra, Long> {
}
