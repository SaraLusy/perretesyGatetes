import { Component } from '@angular/core';
import {ArticuloDTO} from "../../interfaces/articulo.interface";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-crear-cliente',
  templateUrl: './crear-cliente.component.html',
  styleUrls: ['./crear-cliente.component.scss']
})
export class CrearClienteComponent {
  //public articulo: ArticuloDTO;
  public myForm = new FormGroup({
          id: new FormControl('', [Validators.required, Validators.maxLength(4)]),
          nombre: new FormControl('', [Validators.required, Validators.maxLength(100)]),
          descripcion: new FormControl('', [Validators.required, Validators.maxLength(45)]),
          precio: new FormControl('', [Validators.required, Validators.maxLength(100)]),
          peso: new FormControl('', [Validators.required, Validators.maxLength(100)])
  });

}
