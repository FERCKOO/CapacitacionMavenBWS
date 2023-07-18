package com.mycompany.capmaven.entity;

import com.mycompany.capmaven.entity.CDistribuidor;
import com.mycompany.capmaven.entity.CRegion;
import com.mycompany.capmaven.entity.HActivacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-14T14:35:14")
@StaticMetamodel(CAutomata.class)
public class CAutomata_ { 

    public static volatile SingularAttribute<CAutomata, String> clave;
    public static volatile SingularAttribute<CAutomata, Date> fechaAlta;
    public static volatile CollectionAttribute<CAutomata, HActivacion> hActivacionCollection;
    public static volatile SingularAttribute<CAutomata, CRegion> idRegion;
    public static volatile SingularAttribute<CAutomata, Long> idAutomata;
    public static volatile CollectionAttribute<CAutomata, CDistribuidor> cDistribuidorCollection;
    public static volatile SingularAttribute<CAutomata, Boolean> activo;

}