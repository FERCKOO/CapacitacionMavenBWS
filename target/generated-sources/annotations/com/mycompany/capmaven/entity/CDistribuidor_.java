package com.mycompany.capmaven.entity;

import com.mycompany.capmaven.entity.CAutomata;
import com.mycompany.capmaven.entity.CRegion;
import com.mycompany.capmaven.entity.HActivacion;
import com.mycompany.capmaven.entity.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-20T13:10:33")
@StaticMetamodel(CDistribuidor.class)
public class CDistribuidor_ { 

    public static volatile SingularAttribute<CDistribuidor, Date> fechaBaja;
    public static volatile SingularAttribute<CDistribuidor, Date> fechaAlta;
    public static volatile CollectionAttribute<CDistribuidor, HActivacion> hActivacionCollection;
    public static volatile SingularAttribute<CDistribuidor, CRegion> idRegion;
    public static volatile SingularAttribute<CDistribuidor, CAutomata> idAutomata;
    public static volatile SingularAttribute<CDistribuidor, SUsuarios> idUsuarioModifica;
    public static volatile SingularAttribute<CDistribuidor, Long> idDistribuidor;
    public static volatile SingularAttribute<CDistribuidor, String> claveDistribuidor;
    public static volatile SingularAttribute<CDistribuidor, String> nombre;
    public static volatile SingularAttribute<CDistribuidor, Date> fechaServidor;
    public static volatile SingularAttribute<CDistribuidor, Boolean> activo;

}