import {Direccion} from "./direccion.interface";

export interface Usuario {
  codigoUsuario:  number;
  dni:            string;
  nombre:         string;
  apellidos:      string;
  fechaNacimiento:  Date;
  telefono:       string;
  contrasenia:    string;
  email:          string;
  metodoPago: MetodoPago,
  rol: Rol;
  direcciones: Direccion;
}

export interface MetodoPago{
  codigoMetodoPago: number;
  nombre:         string;
  descripcion:    string;
}

export interface Rol{
  codigoRol: number;
  nombre:         string;
  descripcion:    string;
}
