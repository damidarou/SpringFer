package com.nigmacode.apirest.entity;

public enum estado {
    passed(1), failed(2), blocked(3), untested(4);
    private int codigo;

    estado(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
    public estado getCodigoEstado(int i){
        for (estado toret:values()) {
            if (i==toret.getCodigo()){
                return toret;
            }
        }
        return untested;
    }
}
