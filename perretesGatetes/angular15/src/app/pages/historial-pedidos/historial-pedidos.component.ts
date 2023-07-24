import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Pedido, PedidoDTO} from "../../interfaces/pedido.interface";
import {Sort} from "@angular/material/sort";
import {PageFilter} from "../../interfaces/filters.interface";
import {ArticulosService} from "../../services/articulos.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {PedidosService} from "../../services/pedidos.service";

@Component({
  selector: 'app-historial-pedidos',
  templateUrl: './historial-pedidos.component.html',
  styleUrls: ['./historial-pedidos.component.scss']
})
export class HistorialPedidosComponent implements  OnInit{
  public dataSource = new MatTableDataSource<Pedido>();
  public displayedColumns: string[] = ['codigoPedido', 'fechaPedido', 'fechaModificacion', 'importe', 'estado', 'mostrar'];

  public elementLength: number = 0;
  public pageSize: number = 5;
  public pageIndex: number = 0;
  public orderBy: string = 'nombre';
  public orderDesc: boolean = false;

  constructor(public pedidosService: PedidosService,
              private route: ActivatedRoute,
              public dialog: MatDialog,
              private router: Router) { }
  ngOnInit() {
    this.recargarTabla();
  }
  public recargarTabla() {
    this.getRetailersTable();
  }

  public getRetailersTable() {
    const pageFilter: PageFilter = {
      pageable: true,
      pageElements: this.pageSize,
      pageNumber: this.pageIndex,
      orderBy: this.orderBy,
      orderDesc: this.orderDesc
    }

    // this.articulosService.getArticulosFiltro(pageFilter).toPromise().then((response: ArticulosResponse) => {
    //   this.dataSource.data = response.results;
    //   setTimeout(() => {
    //     this.paginator.pageIndex = response.pageNumber;
    //     this.paginator.length = response.count;
    //   });
    // });
  }
  public announceSortChange(sortState: Sort) {
    this.orderBy = sortState.active;
    this.orderDesc = 'desc' == sortState.direction;
    this.recargarTabla();
  }

  public verPedido(elem: PedidoDTO){
    this.router.navigateByUrl('/pedido-detallado/' + elem.code);
  }
}
