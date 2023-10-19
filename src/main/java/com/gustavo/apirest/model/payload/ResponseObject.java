package com.gustavo.apirest.model.payload;

import java.io.Serializable;

public class ResponseObject implements Serializable {

    private String mensaje;
    private Object object;

    public ResponseObject(String mensaje, Object object) {
        this.mensaje = mensaje;
        this.object = object;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
