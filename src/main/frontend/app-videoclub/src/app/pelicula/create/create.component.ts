import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { peliculaService } from '../pelicula.service';
import { ReactiveFormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-create',
  standalone: true,
  imports: [ ReactiveFormsModule, NgIf],
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})

export class CreateComponent implements OnInit {

  form: FormGroup =  new FormGroup({
    nombre:  new FormControl('', [ Validators.required, Validators.pattern('^[a-zA-ZÁáÀàÉéÈèÍíÌìÓóÒòÚúÙùÑñüÜ \-\']+') ]),
    descripcion:  new FormControl('', [ Validators.required]),
    anio:  new FormControl('', [ Validators.required]),
    duracion:  new FormControl('', [ Validators.required]),
    idiomaOriginal:  new FormControl('', [ Validators.required]),
    idioma:  new FormControl('', [ Validators.required]),
    categoria :  new FormControl('', [ Validators.required]),
    ultimaActualizacion:  new FormControl('', [ Validators.required]),
  });

  constructor(
    public peliculaService: peliculaService,
    private router: Router
  ) { }

  ngOnInit(): void {

  }

  get f(){
    return this.form.controls;
  }

  submit(){
    console.log(this.form.value);
    this.peliculaService.create(this.form.value).subscribe(res => {
      console.log('Pelílcula creada correctamente! + res');
      this.router.navigateByUrl('pelicula/index').then();
    })
  }

}