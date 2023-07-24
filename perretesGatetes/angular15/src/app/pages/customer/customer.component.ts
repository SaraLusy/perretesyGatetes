import {Component, OnInit} from '@angular/core';
import {Articulo} from "../../interfaces/articulo.interface";
import {ArticulosService} from "../../services/articulos.service";
import { ActivatedRoute, Router } from '@angular/router';
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit{
  public articulos: Articulo[] = [];
  public dataSource = new MatTableDataSource<Articulo>();
  public displayedColumns: string[] = ['codigoArticulo', 'nombre', 'descripcion', 'especieAnimal', 'cantidad', 'precio', 'accion'];
  public selection = new SelectionModel<Articulo>(true, []);
  constructor(private articuloService: ArticulosService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit(): void {
     this.articuloService.getArticulos()
       .subscribe(articulos => this.articulos = articulos )
  }

  public realizarPedido():void {
    this.router.navigateByUrl('realizar-pedido')
    }

  public historialPedidos():void {
    this.router.navigateByUrl('historial-pedidos')
  }


}




