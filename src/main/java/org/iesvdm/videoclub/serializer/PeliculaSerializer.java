package org.iesvdm.videoclub.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.iesvdm.videoclub.domain.Pelicula;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PeliculaSerializer  extends StdSerializer<Pelicula> {

    public PeliculaSerializer() {
        this(null);
    }

    public PeliculaSerializer(Class < Pelicula > t) {
        super(t);
    }

    @Override
    public void serialize(Pelicula pelicula, JsonGenerator jgen,
                          SerializerProvider provider)
            throws IOException, JsonProcessingException {

        //PATRON FECHAS
        String pattern = "dd-MM-yyyy";
        String patternAño = "yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        SimpleDateFormat simpleDateFormatAño = new SimpleDateFormat(patternAño);

        jgen.writeStartObject(); // equivale en JSON a abrir {
        jgen.writeNumberField("id", pelicula.getIdPelicula()); // "id": 1
        jgen.writeStringField("titulo", pelicula.getTitulo()); // "titulo": "peli"
        jgen.writeStringField("descripcion", pelicula.getDescripcion()); // "descripcion": "descripcion"
        jgen.writeStringField("anio", simpleDateFormatAño.format(pelicula.getAnyoLanzamiento())); // "anio": "2021"

        jgen.writeFieldName("idiomaOriginal"); // "idioma_original":

        if (pelicula.getIdiomaOriginal() != null) {
            jgen.writeStartObject(); // equivale en JSON a abrir {
            jgen.writeNumberField("id", pelicula.getIdiomaOriginal().getId());
            jgen.writeStringField("nombre", pelicula.getIdiomaOriginal().getNombre());
            jgen.writeEndObject(); // equivale en JSON a cerrar }
        } else {
            jgen.writeRawValue("null"); //como no existe el idioma a null
        }
        jgen.writeFieldName("idioma"); // "idioma":

        if (pelicula.getIdioma() != null) {
            jgen.writeStartObject(); // equivale en JSON a abrir {
            jgen.writeNumberField("id", pelicula.getIdioma().getId());
            jgen.writeStringField("nombre", pelicula.getIdioma().getNombre());
            jgen.writeEndObject(); // equivale en JSON a cerrar }
        } else {
            jgen.writeRawValue("null"); //como no existe el idioma a null
        }
        jgen.writeNumberField("duracion", pelicula.getDuracion()); // "duracion": 120
        jgen.writeFieldName("categorias");
        if(pelicula.getCategorias() != null) {
            jgen.writeStartArray();
            pelicula.getCategorias().forEach(categoria -> {
                try {
                    jgen.writeStartObject();
                    jgen.writeNumberField("id", categoria.getId());
                    jgen.writeStringField("nombre", categoria.getNombre());
                    jgen.writeStringField("ultimaActualizacion", simpleDateFormat.format(categoria.getUltimaActualizacion()));
                    jgen.writeEndObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            jgen.writeEndArray();
        } else {
            jgen.writeRawValue("null");
        }

        jgen.writeStringField("ultimaActualizacion", simpleDateFormat.format(pelicula.getUltimaActualizacion())); // "ultima_actualizacion": "2021-05-05-12:00:00"

        jgen.writeEndObject(); //equivale en JSON a cerrar }

    }
}