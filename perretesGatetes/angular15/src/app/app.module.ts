import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {MaterialModule} from "./material/material.module";
import {AdminComponent} from "./pages/admin/admin.component";
import {CustomerComponent} from "./pages/customer/customer.component";
import {FinancialDepartmentComponent} from "./pages/financial-department/financial-department.component";
import {HistorialPedidosComponent} from "./pages/historial-pedidos/historial-pedidos.component";
import {PedidoDetalladoComponent} from "./pages/pedido-detallado/pedido-detallado.component";
import {RealizarPedidoComponent} from "./pages/realizar-pedido/realizar-pedido.component";
import {StaffComponent} from "./pages/staff/staff.component";
import {VisualizarArticulosComponent} from "./pages/visualizar-articulos/visualizar-articulos.component";
import {VisualizarClientesComponent} from "./pages/visualizar-clientes/visualizar-clientes.component";
import {VisualizarEmpleadosComponent} from "./pages/visualizar-empleados/visualizar-empleados.component";
import { CrearClienteComponent } from './pages/crear-cliente/crear-cliente.component';
import { CrearEmpleadoComponent } from './pages/crear-empleado/crear-empleado.component';
import { CrearArticuloComponent } from './pages/crear-articulo/crear-articulo.component';
import {ReactiveFormsModule} from "@angular/forms";
import { MatPaginatorGotoComponent } from './shared/paginator/mat-paginator-goto/mat-paginator-goto.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    CustomerComponent,
    FinancialDepartmentComponent,
    HistorialPedidosComponent,
    PedidoDetalladoComponent,
    RealizarPedidoComponent,
    StaffComponent,
    VisualizarArticulosComponent,
    VisualizarClientesComponent,
    VisualizarEmpleadosComponent,
    CrearClienteComponent,
    CrearEmpleadoComponent,
    CrearArticuloComponent,
    MatPaginatorGotoComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MaterialModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
