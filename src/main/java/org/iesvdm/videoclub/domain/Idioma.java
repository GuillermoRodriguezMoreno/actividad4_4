package org.iesvdm.videoclub.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="idioma")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_idioma")
    @EqualsAndHashCode.Include
    private Long id;
    private String nombre;

    @Column(name = "ultima_actualizacion")
    //@JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss",  shape = JsonFormat.Shape.STRING)
    @JsonFormat(pattern = "yyyy-MM-dd",  shape = JsonFormat.Shape.STRING)
    private Date ultimaActualizacion;

    @OneToMany(mappedBy = "idioma",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Pelicula> peliculasIdioma;

    @OneToMany(mappedBy = "idiomaOriginal",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Pelicula> peliculasIdiomaOriginal;
}
