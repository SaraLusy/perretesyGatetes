import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-crear-empleado',
  templateUrl: './crear-empleado.component.html',
  styleUrls: ['./crear-empleado.component.scss']
})
export class CrearEmpleadoComponent {

  public myForm = new FormGroup({
    id: new FormControl('', [Validators.required, Validators.maxLength(4)]),
    nombre: new FormControl('', [Validators.required, Validators.maxLength(100)]),
    descripcion: new FormControl('', [Validators.required, Validators.maxLength(45)]),
    precio: new FormControl('', [Validators.required, Validators.maxLength(100)]),
    peso: new FormControl('', [Validators.required, Validators.maxLength(100)])
  });

  public myError = (controlName: string, errorName: string) => {
   // return this.myForm.controls[controlName].hasError(errorName);
  }

}
