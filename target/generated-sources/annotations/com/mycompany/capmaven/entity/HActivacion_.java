package com.mycompany.capmaven.entity;

import com.mycompany.capmaven.entity.CAutomata;
import com.mycompany.capmaven.entity.CCiudad;
import com.mycompany.capmaven.entity.CClientes;
import com.mycompany.capmaven.entity.CDistribuidor;
import com.mycompany.capmaven.entity.CRegion;
import com.mycompany.capmaven.entity.CTipoTelefono;
import com.mycompany.capmaven.entity.MArchivoLote;
import com.mycompany.capmaven.entity.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-14T14:35:14")
@StaticMetamodel(HActivacion.class)
public class HActivacion_ { 

    public static volatile SingularAttribute<HActivacion, SUsuarios> idUsuario;
    public static volatile SingularAttribute<HActivacion, String> claveDistribuidor;
    public static volatile SingularAttribute<HActivacion, CCiudad> idCiudad;
    public static volatile SingularAttribute<HActivacion, String> descripcionTipo;
    public static volatile SingularAttribute<HActivacion, String> iccid;
    public static volatile SingularAttribute<HActivacion, String> claveAutomata;
    public static volatile SingularAttribute<HActivacion, CClientes> idCliente;
    public static volatile SingularAttribute<HActivacion, Date> fechaPeticion;
    public static volatile SingularAttribute<HActivacion, CRegion> idRegion;
    public static volatile SingularAttribute<HActivacion, MArchivoLote> idArchivo;
    public static volatile SingularAttribute<HActivacion, Long> id;
    public static volatile SingularAttribute<HActivacion, String> telefono;
    public static volatile SingularAttribute<HActivacion, CDistribuidor> idDistribuidor;
    public static volatile SingularAttribute<HActivacion, String> nombreRegion;
    public static volatile SingularAttribute<HActivacion, Long> idActivacion;
    public static volatile SingularAttribute<HActivacion, CAutomata> idAutomata;
    public static volatile SingularAttribute<HActivacion, String> distribuidor;
    public static volatile SingularAttribute<HActivacion, Date> fechaRespuesta;
    public static volatile SingularAttribute<HActivacion, String> cliente;
    public static volatile SingularAttribute<HActivacion, Long> monto;
    public static volatile SingularAttribute<HActivacion, String> ciudad;
    public static volatile SingularAttribute<HActivacion, String> imei;
    public static volatile SingularAttribute<HActivacion, CTipoTelefono> idTipoTelefonia;
    public static volatile SingularAttribute<HActivacion, String> respuestaAplicacion;
    public static volatile SingularAttribute<HActivacion, Date> fechaServidor;

}