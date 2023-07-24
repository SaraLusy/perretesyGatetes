import { Component } from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Pedido} from "../../interfaces/pedido.interface";
import {Sort} from "@angular/material/sort";

@Component({
  selector: 'app-pedido-detallado',
  templateUrl: './pedido-detallado.component.html',
  styleUrls: ['./pedido-detallado.component.scss']
})
export class PedidoDetalladoComponent {

  public dataSource = new MatTableDataSource<Pedido>();
  public displayedColumns: string[] = ['codigoPedido', 'cantidad', 'importe', 'direccion', 'fechaPedido',
    'fechaEstimadaEntrega', 'fechaEstimadaEntrega', 'metodoPago', 'estado' ];

}
