import { Categoria } from "../categoria/categoria";

export interface Pelicula {
    id: number;
    titulo: string;
    descripcion: string;
    anio: string;

    idiomaOriginal: {
        id: number;
        nombre: string;
    };

    idioma: {
        id: number;
        nombre: string;
    };

    duracion: number;

    categorias: Categoria[];

    ultimaActualizacion: string;
    }