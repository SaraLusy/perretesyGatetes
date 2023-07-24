import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Usuario} from "../interfaces/usuario.interface";

const URLSERVER: string = environment.serverUrl;
@Injectable({providedIn: 'root'})
export class UsuariosService {
  private rol: string = "";

  constructor(private http: HttpClient) {}

  public getRol(): string {
    return this.rol;
  }


// Info usuario
  getInfoUsuario() : Observable<Usuario>{
    return this.http.get<Usuario>(URLSERVER + '');
  }


}
