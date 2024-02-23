package it.epicode.finalexam.repository;

import it.epicode.finalexam.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventoRespository extends JpaRepository<Evento, Integer>, PagingAndSortingRepository<Evento, Integer> {
}
