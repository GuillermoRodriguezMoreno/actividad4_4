import { Component, OnInit } from '@angular/core';
import { peliculaService } from '../pelicula.service';
import { Pelicula } from '../pelicula';
import { Router, RouterModule } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-index',
  standalone: true,
  imports: [RouterModule, NgFor],
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  peliculas: Pelicula[] = [];

  constructor(public peliculaService:peliculaService) { }

  ngOnInit(): void {
    this.peliculaService.getAll().subscribe((data: Pelicula[])=>{
      this.peliculas= data;
      console.log(this.peliculas);
    })
  }

  deletePelicula(id: any){
    this.peliculaService.delete(id).subscribe(res => {
      this.peliculas = this.peliculas.filter(p => p.id !== id);
      console.log('Película id =' + id + ' eliminada satisfactoriamente!');
    })
  }


}
