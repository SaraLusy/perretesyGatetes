import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Pedido, PedidosResponse} from "../../interfaces/pedido.interface";
import {Sort} from "@angular/material/sort";
import {ActivatedRoute, Router} from "@angular/router";
import {PedidosService} from "../../services/pedidos.service";
import {MatPaginator} from "@angular/material/paginator";
import {PageFilter} from "../../interfaces/filters.interface";

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.scss']
})
export class StaffComponent implements OnInit{

  public dataSource = new MatTableDataSource<Pedido>();
  public displayedColumns: string[] =
    ['codigoUsuario', 'codigoPedido', 'cantidad', 'pesoTotal', 'direccion', 'fechaPedido', 'Estado'];
  public elementLength: number = 0;
  public pageSize: number = 5;
  public pageIndex: number = 0;
  public orderBy: string = 'nombre';
  public orderDesc: boolean = false;

  constructor(public pedidosService: PedidosService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.reloadTable();
  }

  //@ViewChild(MatPaginator) paginator: MatPaginator;

  public announceSortChange(sortState: Sort) {
    this.orderBy = sortState.active;
    this.orderDesc = 'desc' == sortState.direction;
    this.reloadTable();
  }

  public reloadTable() {
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
    // this.pedidosService.getPedidos(pageFilter).toPromise()
    //   .then((response: PedidosResponse) => {
    //   this.dataSource.data = response.results;
    //   setTimeout(() => {
    //     this.paginator.pageIndex = response.pageNumber;
    //     this.paginator.length = response.count;
    //   });
    // });
  }
}
