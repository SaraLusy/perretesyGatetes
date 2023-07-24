import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomerComponent} from "./pages/customer/customer.component";
import {AdminComponent} from "./pages/admin/admin.component";
import {FinancialDepartmentComponent} from "./pages/financial-department/financial-department.component";
import {StaffComponent} from "./pages/staff/staff.component";
import {RealizarPedidoComponent} from "./pages/realizar-pedido/realizar-pedido.component";
import {HistorialPedidosComponent} from "./pages/historial-pedidos/historial-pedidos.component";
import {PedidoDetalladoComponent} from "./pages/pedido-detallado/pedido-detallado.component";
import {VisualizarArticulosComponent} from "./pages/visualizar-articulos/visualizar-articulos.component";
import {VisualizarClientesComponent} from "./pages/visualizar-clientes/visualizar-clientes.component";
import {VisualizarEmpleadosComponent} from "./pages/visualizar-empleados/visualizar-empleados.component";
import {CrearEmpleadoComponent} from "./pages/crear-empleado/crear-empleado.component";
import {CrearArticuloComponent} from "./pages/crear-articulo/crear-articulo.component";
import {CrearClienteComponent} from "./pages/crear-cliente/crear-cliente.component";

const routes: Routes = [
  {
    path: '',
    children:[
      {
        path: 'admin',
        component: AdminComponent
      },
      {
        path: 'customer',
        component: CustomerComponent
      },
      {
        path: 'crear-articulo',
        component: CrearArticuloComponent
      },
      {
        path: 'crear-empleado',
        component: CrearEmpleadoComponent
      },
      {
        path: 'crear-cliente',
        component: CrearClienteComponent
      },
      {
        path: 'financial',
        component: FinancialDepartmentComponent
      },
      {
        path: 'staff',
        component: StaffComponent
      },
      {
        path: 'realizar-pedido',
        component: RealizarPedidoComponent
      },
      {
        path: 'historial-pedidos',
        component: HistorialPedidosComponent
      },
      {
        path: 'pedido-detallado',
        component: PedidoDetalladoComponent
      },
      {
        path: 'visualizar-articulos',
        component: VisualizarArticulosComponent
      },
      {
        path: 'visualizar-clientes',
        component: VisualizarClientesComponent
      },
      {
        path: 'visualizar-empleados',
        component: VisualizarEmpleadosComponent
      },
      {
        path: 'pedido-detallado/:id',
        component: PedidoDetalladoComponent
      },
      {
        path: '**',
        redirectTo: 'customer'
      },
    ]

  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then( m => m.AuthModule)
  }

  // {
  //   path: 'auth',
  //   loadChildren: () => import('./auth/auth.module').then( m => m.AuthModule)
  // },
  // {
  //   path: 'store',
  //   loadChildren: () => import('./store/store.module').then( m => m.StoreModule)
  // },
  // {
  //   path: '',
  //   redirectTo:'store',
  //   pathMatch: 'full'
  // },
  // {
  //   path: '**',
  //   redirectTo:'store'
  //   //TODO
  // // cambiar ruta por defecto
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
