package com.empresa.perretesGatetes.domain.filters;

public class DireccionFiltroDTO extends BaseFiltroDTO {

    private Long codigoUsuario;
    private Long codigoTipoDireccion;

    public Long getCodigoUsuario() {return codigoUsuario;}

    public void setCodigoUsuario(Long codigoUsuario) {this.codigoUsuario = codigoUsuario;}

    public Long getCodigoTipoDireccion() {return codigoTipoDireccion;}

    public void setCodigoTipoDireccion(Long codigoTipoDireccion) {this.codigoTipoDireccion = codigoTipoDireccion;}
}
