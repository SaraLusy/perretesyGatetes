import { Component } from '@angular/core';
import {ArticulosService} from "../../services/articulos.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-visualizar-articulos',
  templateUrl: './visualizar-articulos.component.html',
  styleUrls: ['./visualizar-articulos.component.scss']
})
export class VisualizarArticulosComponent {
  constructor(private articuloService: ArticulosService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  public crearArticulo():void {
    this.router.navigateByUrl('crear-articulo')
  }
}
