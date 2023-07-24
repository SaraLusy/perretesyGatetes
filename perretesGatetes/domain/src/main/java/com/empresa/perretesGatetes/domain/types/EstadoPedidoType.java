package com.empresa.perretesGatetes.domain.types;

public enum EstadoPedidoType {
    RECIBIDO (1,"Recibido"),
    EN_PROCESO(2,"En proceso"),
    EMPAQUETADO(3,"Empaquetado"),
    ENVIADO(4,"Enviado"),
    FINALIZADO(5,"Finalizado"),
    CANCELADO(6,"Cancelado");

    private long codigoEstadoPedido;
    private String descripcion;

    EstadoPedidoType (long codigoEstadoPedido, String descripcion){
        this.codigoEstadoPedido = codigoEstadoPedido;
        this.descripcion = descripcion;
    }

    public long getCodigoEstadoPedido() {
        return codigoEstadoPedido;
    }

    public void setCodigoEstadoPedido(long codigoEstadoPedido) {
        this.codigoEstadoPedido = codigoEstadoPedido;
    }

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
