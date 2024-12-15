package api.reservas.api.entitys_remove;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vaga")
public class Vaga  {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      //@ManyToOne(targetEntity = Restaurante.class, fetch = FetchType.LAZY)
      //@JoinColumn(name = "id_restaurante", referencedColumnName = "id")
      private Long restaurante;

      private String diaReserva;
      private String horaReserva;
      private Boolean vagaLivre;


      //Contrutores
      public Vaga(){}

      public Vaga(Long id, Long restaurante, String diaReserva, String horaReserva, Boolean vagaLivre){
      this.id = id;
      this.restaurante = restaurante;
      this.diaReserva = diaReserva;
      this.horaReserva = horaReserva;
      this.vagaLivre = true;
      }

      //getter e setter
      public void setId(Long id){this.id = id;}
      public Long getId(){return this.id;}

      public void setRestaurante(Long restaurante){this.restaurante = restaurante;}
      public Long getRestaurante(){return this.restaurante;}

      public void setDiaReserva(String diaReserva){this.diaReserva = diaReserva;}
      public String getDiaReserva(){return this.diaReserva;}

      public void setHoraReserva(String horaReserva){this.horaReserva = horaReserva;}
      public String getHoraReserva(){return this.horaReserva;}

      public void setVagaLivre(Boolean vagaLivre){ this.vagaLivre = vagaLivre; }
      public Boolean getVagaLivre(){ return this.vagaLivre;}
}

