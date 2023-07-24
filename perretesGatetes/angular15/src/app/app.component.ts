import {Component, OnInit} from '@angular/core';
import {environment} from "../environments/environment";
import {UsuariosService} from "./services/usuarios.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'angular15';
 public urlImage: string = environment.imageUrl;
 public version: string = environment.version;
 public fullName: string = "";
 public unauthorizedMsg: string = "";

  constructor (public usuarioService: UsuariosService) {}
  ngOnInit(): void {}


}
