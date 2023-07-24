import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {PageFilter} from "../interfaces/filters.interface";
import {Observable} from "rxjs";
import {PedidosResponse} from "../interfaces/pedido.interface";

const URLSERVER: string = environment.serverUrl;
@Injectable({providedIn: 'root'})
export class PedidosService {

  constructor(private http: HttpClient) { }

  getPedidos(filter: PageFilter): Observable<PedidosResponse>{
    return this.http.post<PedidosResponse>(URLSERVER + 'pedidos/getProposalsPageable', filter);
  }
}
