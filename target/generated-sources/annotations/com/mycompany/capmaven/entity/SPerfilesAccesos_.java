package com.mycompany.capmaven.entity;

import com.mycompany.capmaven.entity.SAccesos;
import com.mycompany.capmaven.entity.SPerfiles;
import com.mycompany.capmaven.entity.SPerfilesAccesosPK;
import com.mycompany.capmaven.entity.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-14T14:35:14")
@StaticMetamodel(SPerfilesAccesos.class)
public class SPerfilesAccesos_ { 

    public static volatile SingularAttribute<SPerfilesAccesos, SPerfilesAccesosPK> sPerfilesAccesosPK;
    public static volatile SingularAttribute<SPerfilesAccesos, SPerfiles> sPerfiles;
    public static volatile SingularAttribute<SPerfilesAccesos, SUsuarios> idUsuarioModifica;
    public static volatile SingularAttribute<SPerfilesAccesos, SAccesos> sAccesos;
    public static volatile SingularAttribute<SPerfilesAccesos, Date> fechaServidor;

}