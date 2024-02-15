package org.iesvdm.videoclub;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Idioma;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.IdiomaRepository;
import org.iesvdm.videoclub.service.PeliculaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    private PeliculaService peliculaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private IdiomaRepository idiomaRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void insertInicial() {

        // Categorias
        Categoria cat1 = new Categoria(0, "Acción", new HashSet<>(), new Date());
        Categoria cat2 = new Categoria(0, "Drama", new HashSet<>(), new Date());
        Categoria cat3 = new Categoria(0, "Comedia", new HashSet<>(), new Date());

        categoriaRepository.save(cat1);
        categoriaRepository.save(cat2);
        categoriaRepository.save(cat3);

        // Idiomas
        Idioma idioma1 = new Idioma(0L, "Español", new Date(), new ArrayList<>(), new ArrayList<>());
        Idioma idioma2 = new Idioma(0L, "Inglés", new Date(), new ArrayList<>(), new ArrayList<>());

        idiomaRepository.save(idioma1);
        idiomaRepository.save(idioma2);

        // Peliculas
        Pelicula peli1 = new Pelicula(0, "El señor de los anillos", "Un anillo para gobernarlos a todos", new Date(), idioma1, idioma2, 0, new BigDecimal(0), 1, new BigDecimal(0), "", "", new HashSet<>(), new Date());
        Pelicula peli2 = new Pelicula(0, "Star War", "Yo soy tu padre", new Date(), idioma2, idioma1, 0, new BigDecimal(0), 1, new BigDecimal(0), "", "", new HashSet<>(), new Date());

        peli1.getCategorias().add(cat1);
        peli2.getCategorias().add(cat2);
        peli2.getCategorias().add(cat3);

        peliculaRepository.save(peli1);
        peliculaRepository.save(peli2);

        // Relaciones
        cat1.getPeliculas().add(peli1);
        cat2.getPeliculas().add(peli2);
        cat3.getPeliculas().add(peli2);

        categoriaRepository.save(cat1);
        categoriaRepository.save(cat2);
        categoriaRepository.save(cat3);

        idioma1.getPeliculasIdioma().add(peli1);
        idioma2.getPeliculasIdioma().add(peli2);
        idioma2.getPeliculasIdiomaOriginal().add(peli2);
        idioma1.getPeliculasIdiomaOriginal().add(peli2);

        idiomaRepository.save(idioma1);
        idiomaRepository.save(idioma2);
    }
}
