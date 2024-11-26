package api.reservas.api.repository;


import api.reservas.api.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByNome(String nome);

    boolean existsByTelefone(String telefone);

}