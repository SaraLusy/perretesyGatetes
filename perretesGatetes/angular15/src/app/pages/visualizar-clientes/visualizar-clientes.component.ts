import { Component } from '@angular/core';
import {ArticulosService} from "../../services/articulos.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-visualizar-clientes',
  templateUrl: './visualizar-clientes.component.html',
  styleUrls: ['./visualizar-clientes.component.scss']
})
export class VisualizarClientesComponent {
  constructor(private articuloService: ArticulosService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  public crearCliente():void {
    this.router.navigateByUrl('crear-clientes')
  }

}
