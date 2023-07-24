import { Component } from '@angular/core';
import {ArticulosService} from "../../services/articulos.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-visualizar-empleados',
  templateUrl: './visualizar-empleados.component.html',
  styleUrls: ['./visualizar-empleados.component.scss']
})
export class VisualizarEmpleadosComponent {
  constructor(private articuloService: ArticulosService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  public crearEmpleado():void {
    this.router.navigateByUrl('crear-empleado')
  }

}
