package it.epicode.finalexam.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descrizione;
    private String titolo;
    private Date data;
    private String luogo;
    @Column (name = "numero_posti_disponibili")
    private int numeroPostiDisponibili;
    @ManyToMany
    @JoinTable(name = "utentilist")
    private List<Utente> utenti;

    public Evento(String descrizione, String titolo, Date data, String luogo, int numeroPostiDisponibili) {
        this.descrizione = descrizione;
        this.titolo = titolo;
        this.data = data;
        this.luogo = luogo;
        this.numeroPostiDisponibili = numeroPostiDisponibili;
        this.utenti = new ArrayList<>();
    }

    public Evento(){

    }
    public void aggiungiUtente(Utente utente){
        utenti.add(utente);
    }
}
