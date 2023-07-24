
import {Usuario} from "./usuario.interface";

export interface Direccion{
  codigoDireccion:  number;
  codigoPostal:     string;
  localidad:        string;
  comunidad:        string;
  direccion:        string;
  numero:           number;
  escalera:         string;
  piso:             string;
  letra:            string;
  tipoDireccion:    TipoDireccion;
  usuario:          Usuario;
}

export interface TipoDireccion{
  codigoTipoDireccion: number;
  nombre:         string;
  descripcion:    string;
}
