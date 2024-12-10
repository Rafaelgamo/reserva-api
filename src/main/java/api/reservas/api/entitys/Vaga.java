package api.reservas.api.entitys;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "vaga")
public class Vaga  {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @ManyToOne(targetEntity = Restaurante.class, fetch = FetchType.LAZY)
      @JoinColumn(name = "id_restaurante", referencedColumnName = "id")
      private Restaurante restaurante;

      private String diaReserva;
      private String horaReserva;
      private Boolean vagaLivre;


      //Contrutores
      public Vaga(){}

      public Vaga(Long id, Restaurante restaurante, String diaReserva, String horaReserva, Boolean vagaLivre){
      this.id = id;
      this.restaurante = new Restaurante();
      this.diaReserva = diaReserva;
      this.horaReserva = horaReserva;
      this.vagaLivre = true;
      }

      //getter e setter
      public void setId(Long id){this.id = id;}
      public Long getId(){return this.id;}

      public void setRestaurante(Restaurante restaurante){this.restaurante = restaurante;}
      public Restaurante getRestaurante(){return this.restaurante;}

      public void setDiaReserva(String diaReserva){this.diaReserva = diaReserva;}
      public String getDiaReserva(){return this.diaReserva;}

      public void setHoraReserva(String horaReserva){this.horaReserva = horaReserva;}
      public String getHoraReserva(){return this.horaReserva;}

      public void setVagaLivre(Boolean vagaLivre){ this.vagaLivre = vagaLivre; }
      public Boolean getVagaLivre(){ return this.vagaLivre;}
}

