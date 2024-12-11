package api.reservas.api.repository;

import api.reservas.api.entitys.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Bean
    Optional<Usuario> findById(Long id);

    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);

}


