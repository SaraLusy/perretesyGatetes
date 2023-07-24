export interface ArticulosResponse {
  count: number;
  pageNumber:  number;
  results: Articulo[];
}

export interface ArticuloRequest {

}

export interface Articulo{

  codigoArticulo: number;
  nombre:         string;
  descripcion:    string;
  precio:         number;
  pesoUnitario:   number;
  especieAnimal: EspecieAnimal;
}

export interface ArticuloDTO {

}

export interface EspecieAnimal{

  codigoEspecieAnimal: number;
  nombre:         string;
  descripcion:    string;

}
