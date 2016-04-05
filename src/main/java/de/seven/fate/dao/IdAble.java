package de.seven.fate.dao;

import java.io.Serializable;

/**
 * Created by Mario on 05.04.2016.
 */
public interface IdAble<I extends Serializable> extends Serializable{

    I getId();

    void setId(I id);
}
