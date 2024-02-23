package it.epicode.finalexam.repository;

import it.epicode.finalexam.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    public Optional<Utente> findByUsername(String username);

    public Optional<Utente> deleteByUsername(String username);
}
