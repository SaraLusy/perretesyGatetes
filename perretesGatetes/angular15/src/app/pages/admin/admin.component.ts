import { Component } from '@angular/core';
import {ArticulosService} from "../../services/articulos.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent {

  constructor(private articuloService: ArticulosService,
              private route: ActivatedRoute,
              private router: Router) {}

  public visualizarArticulos():void {
    this.router.navigateByUrl('visualizar-articulos')
  }
  public visualizarClientes():void {
    this.router.navigateByUrl('visualizar-clientes')
  }
  public visualizarEmpleados():void {
    this.router.navigateByUrl('visualizar-empleados')
  }
}
