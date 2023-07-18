package com.mycompany.capmaven.entity;

import com.mycompany.capmaven.entity.CRegion;
import com.mycompany.capmaven.entity.CTipoTelefono;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-14T14:35:14")
@StaticMetamodel(CTelefonia.class)
public class CTelefonia_ { 

    public static volatile SingularAttribute<CTelefonia, String> descripcion;
    public static volatile SingularAttribute<CTelefonia, String> clave;
    public static volatile CollectionAttribute<CTelefonia, CTipoTelefono> cTipoTelefonoCollection;
    public static volatile SingularAttribute<CTelefonia, CRegion> idRegion;
    public static volatile SingularAttribute<CTelefonia, Long> idTelefonia;
    public static volatile SingularAttribute<CTelefonia, Date> fechaServidor;
    public static volatile SingularAttribute<CTelefonia, Boolean> activo;

}