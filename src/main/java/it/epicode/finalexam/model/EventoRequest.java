package it.epicode.finalexam.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class EventoRequest {


    @NotBlank(message = "titolo obbligatorio")
    private String titolo;
    @NotBlank(message = "descrizione obbligatoria")
    private String descrizione;
    @NotBlank(message = "luogo obbligatorio")
    private String luogo;
    @NotBlank(message = "data obbligatoria")
    private Date data;
    @NotBlank(message = "numero massimo posti disponibili obbligatorio")
    private int numeroPostiDisponibili;
    @NotBlank(message = "Utente id obbligatorio")
    private int utente_id;

}
