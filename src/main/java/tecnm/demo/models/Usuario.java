package tecnm.demo.models; 

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String nombre;

    @NotBlank
    @Email
    public String email;

    public String telefono;
    
   
    public String sexo;

    @NotNull
    @Column(name = "fecha_nacimiento")
    public LocalDate fechaNacimiento;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    public String contrasena;
    
    @Column(name = "fecha_registro")
    public LocalDateTime fechaRegistro;

    public Usuario() {}
}