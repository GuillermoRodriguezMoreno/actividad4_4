import { Categoria } from "../categoria/categoria";

export interface Pelicula {
    id: number;
    titulo: string;
    descripcion: string;
    anio: number;
    idioma_original: {
        id: number;
        nombre: string;
    };
    idioma: {
        id: number;
        nombre: string;
    };
    duracion: number;
    categorias: Categoria[]
    ultimaActualizacion: string
    }