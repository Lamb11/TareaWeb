package com.alberto.tareaweb.data;

import java.util.ArrayList;

/**
 * Created by Alberto on 04/03/15.
 */
public class NombreArray {

    public ArrayList<Nombre> getNombre(){
        return nombre;
    }

    public void setNombre(ArrayList<Nombre> mNombreArray){
        this.nombre=mNombreArray;
    }

    public ArrayList<Nombre> nombre;
}
