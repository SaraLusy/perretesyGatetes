import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Articulo} from "../../interfaces/articulo.interface";
import {Sort} from "@angular/material/sort";
import {PageFilter} from "../../interfaces/filters.interface";
import {ArticulosService} from "../../services/articulos.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-realizar-pedido',
  templateUrl: './realizar-pedido.component.html',
  styleUrls: ['./realizar-pedido.component.scss']
})
export class RealizarPedidoComponent {

  public dataSource = new MatTableDataSource<Articulo>();
  public displayedColumns: string[] = ['codigoArticulo', 'nombre', 'especieAnimal', 'cantidad', 'precio', 'editar'];
  public elementLength: number = 0;
  public pageSize: number = 5;
  public pageIndex: number = 0;
  public orderBy: string = 'nombre';
  public orderDesc: boolean = false;

  constructor(public articulosService: ArticulosService,
              private route: ActivatedRoute,
              public dialog: MatDialog,
              private router: Router) { }

  ngOnInit() {
    this.recargarTabla();
  }
  public recargarTabla() {
    this.getRetailersTable();
  }

 // @ViewChild(MatPaginator) paginator: MatPaginator;
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

}
