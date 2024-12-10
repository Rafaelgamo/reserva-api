package api.reservas.api.repository;


import api.reservas.api.entitys.Avaliacao;
import api.reservas.api.entitys.Restaurante;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>  {



}
