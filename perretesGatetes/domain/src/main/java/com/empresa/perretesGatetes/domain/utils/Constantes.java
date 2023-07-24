package com.empresa.perretesGatetes.domain.utils;

public class Constantes {
    /* ROL */
    public static int ROL_NOMBRE_MAX = 10;
    public static int ROL_DESCRIPCION_MAX = 100;

    /* METODO PAGO */
    public static int METODO_PAGO_NOMBRE_MAX = 10;
    public static int METODO_PAGO_DESCRIPCION_MAX = 100;

    /* USUARIO */
    public static int USUARIO_DNI_MAX = 20;
    public static int USUARIO_NOMBRE_MAX = 100;
    public static int USUARIO_APELLIDOS_MAX = 100;
    public static int USUARIO_EMAIL_MAX = 100;
    public static int USUARIO_TELEFONO_MAX = 15;

    /* PATTERN */
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    /* TIPO_DIRECCION */

    public static int TIPO_DIRECCION_NOMBRE_MAX = 100;
    public static int TIPO_DIRECCION_APELLIDOS_MAX = 100;

    /* DIRECCION */
    public static int DIRECCION_CODIGO_POSTAL_MIN = 5;
    public static int DIRECCION_LOCALIDAD_MAX = 100;
    public static int DIRECCION_COMUNIDAD_MAX = 100;
    public static int DIRECCION_DIRECCION_MAX = 200;
    public static int DIRECCION_ESCALERA_MAX = 10;
    public static int DIRECCION_PISO_MAX = 10;
    public static int DIRECCION_LETRA_MAX = 1;

    /* ESPECIE ANIMAL */

    public static int ESPECIE_ANIMAL_NOMBRE_MAX = 100;
    public static int ESPECIE_ANIMAL_DESCRIPCION_MAX = 100;

    /* ESTADO PEDIDO */
    public static int ESTADO_PEDIDO_NOMBRE_MAX = 2;
    public static int ESTADO_PEDIDO_DESCRIPCION_MAX = 100;

    /* ARTICULO */
    public static int ARTICULO_NOMBRE_MAX = 100;
    public static int ARTICULO_DESCRIPCION_MAX = 100;


}
