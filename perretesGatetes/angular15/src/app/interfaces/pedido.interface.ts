import {MetodoPago, Usuario} from "./usuario.interface";
import {Direccion} from "./direccion.interface";

export interface PedidosResponse {
  count: number;
  pageNumber:  number;
  results: Pedido[];
}
export interface Pedido{

  codigoPedido:         number;
  importeTotal:         number;
  pesoTotal:            number;
  fechaPedido:          Date;
  fechaEstimadaEntrega: Date;
  fechaModificacion:    Date;
  metodoPago:           MetodoPago;
  direccion:            Direccion;
  usuario:              Usuario;
  pedidoArticulo:       PedidosArticulo;
  pedidoEstado: PedidosEstados;

}

export interface PedidosArticulo{
  cantidad: number;
}

export interface PedidosEstados{
  fechaCambioEstado: Date;
}

export interface PedidoDTO{
  code: number;
}
