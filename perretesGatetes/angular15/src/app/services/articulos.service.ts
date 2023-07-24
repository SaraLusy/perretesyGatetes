import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment'
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Articulo, ArticuloRequest, ArticulosResponse} from "../interfaces/articulo.interface";
import {PageFilter} from "../interfaces/filters.interface";

const URLSERVER: string = environment.serverUrl;

@Injectable({providedIn: 'root'})
export class ArticulosService {

  constructor(private http:HttpClient) { }

  getArticulos():Observable<Articulo[]>{
    return this.http.get<Articulo[]>(URLSERVER + '/articulo/getArticulos')
  }

  getArticulosFiltro(filter: PageFilter): Observable<ArticulosResponse>{
    return this.http.post<ArticulosResponse>(URLSERVER + 'articulo/getArticulosFiltrado', filter);
  }

  saveArticulo(articulo: ArticuloRequest): Observable<ArticuloRequest>{
    return this.http.post<ArticuloRequest>(URLSERVER + 'articulo/crearArticulo', articulo);
  }

  getArticuloById(codigoArticulo: number): Observable<ArticulosResponse>{
    return this.http.get<ArticulosResponse>(URLSERVER + 'articulo/getArticuloPorId/' + codigoArticulo);
  }
}
