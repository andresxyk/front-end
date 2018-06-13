/**
 * DetalleEstudios.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.sct.www;

public class DetalleEstudios  implements java.io.Serializable {
    private java.lang.String pase;

    private java.lang.String idestudio;

    private java.lang.String estudio;

    private java.lang.String indicaciones;

    private java.lang.String diagnostico;

    private java.lang.String recomendacion;

    public DetalleEstudios() {
    }

    public DetalleEstudios(
           java.lang.String pase,
           java.lang.String idestudio,
           java.lang.String estudio,
           java.lang.String indicaciones,
           java.lang.String diagnostico,
           java.lang.String recomendacion) {
           this.pase = pase;
           this.idestudio = idestudio;
           this.estudio = estudio;
           this.indicaciones = indicaciones;
           this.diagnostico = diagnostico;
           this.recomendacion = recomendacion;
    }


    /**
     * Gets the pase value for this DetalleEstudios.
     * 
     * @return pase
     */
    public java.lang.String getPase() {
        return pase;
    }


    /**
     * Sets the pase value for this DetalleEstudios.
     * 
     * @param pase
     */
    public void setPase(java.lang.String pase) {
        this.pase = pase;
    }


    /**
     * Gets the idestudio value for this DetalleEstudios.
     * 
     * @return idestudio
     */
    public java.lang.String getIdestudio() {
        return idestudio;
    }


    /**
     * Sets the idestudio value for this DetalleEstudios.
     * 
     * @param idestudio
     */
    public void setIdestudio(java.lang.String idestudio) {
        this.idestudio = idestudio;
    }


    /**
     * Gets the estudio value for this DetalleEstudios.
     * 
     * @return estudio
     */
    public java.lang.String getEstudio() {
        return estudio;
    }


    /**
     * Sets the estudio value for this DetalleEstudios.
     * 
     * @param estudio
     */
    public void setEstudio(java.lang.String estudio) {
        this.estudio = estudio;
    }


    /**
     * Gets the indicaciones value for this DetalleEstudios.
     * 
     * @return indicaciones
     */
    public java.lang.String getIndicaciones() {
        return indicaciones;
    }


    /**
     * Sets the indicaciones value for this DetalleEstudios.
     * 
     * @param indicaciones
     */
    public void setIndicaciones(java.lang.String indicaciones) {
        this.indicaciones = indicaciones;
    }


    /**
     * Gets the diagnostico value for this DetalleEstudios.
     * 
     * @return diagnostico
     */
    public java.lang.String getDiagnostico() {
        return diagnostico;
    }


    /**
     * Sets the diagnostico value for this DetalleEstudios.
     * 
     * @param diagnostico
     */
    public void setDiagnostico(java.lang.String diagnostico) {
        this.diagnostico = diagnostico;
    }


    /**
     * Gets the recomendacion value for this DetalleEstudios.
     * 
     * @return recomendacion
     */
    public java.lang.String getRecomendacion() {
        return recomendacion;
    }


    /**
     * Sets the recomendacion value for this DetalleEstudios.
     * 
     * @param recomendacion
     */
    public void setRecomendacion(java.lang.String recomendacion) {
        this.recomendacion = recomendacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DetalleEstudios)) return false;
        DetalleEstudios other = (DetalleEstudios) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pase==null && other.getPase()==null) || 
             (this.pase!=null &&
              this.pase.equals(other.getPase()))) &&
            ((this.idestudio==null && other.getIdestudio()==null) || 
             (this.idestudio!=null &&
              this.idestudio.equals(other.getIdestudio()))) &&
            ((this.estudio==null && other.getEstudio()==null) || 
             (this.estudio!=null &&
              this.estudio.equals(other.getEstudio()))) &&
            ((this.indicaciones==null && other.getIndicaciones()==null) || 
             (this.indicaciones!=null &&
              this.indicaciones.equals(other.getIndicaciones()))) &&
            ((this.diagnostico==null && other.getDiagnostico()==null) || 
             (this.diagnostico!=null &&
              this.diagnostico.equals(other.getDiagnostico()))) &&
            ((this.recomendacion==null && other.getRecomendacion()==null) || 
             (this.recomendacion!=null &&
              this.recomendacion.equals(other.getRecomendacion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getPase() != null) {
            _hashCode += getPase().hashCode();
        }
        if (getIdestudio() != null) {
            _hashCode += getIdestudio().hashCode();
        }
        if (getEstudio() != null) {
            _hashCode += getEstudio().hashCode();
        }
        if (getIndicaciones() != null) {
            _hashCode += getIndicaciones().hashCode();
        }
        if (getDiagnostico() != null) {
            _hashCode += getDiagnostico().hashCode();
        }
        if (getRecomendacion() != null) {
            _hashCode += getRecomendacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DetalleEstudios.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsystem/", "detalleEstudios"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pase");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idestudio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idestudio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estudio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estudio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diagnostico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "diagnostico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recomendacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recomendacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
