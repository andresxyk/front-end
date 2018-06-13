/**
 * Derechohabiente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.sct.www;

public class Derechohabiente  implements java.io.Serializable {
    private java.lang.String expediente;

    private java.lang.String identificador;

    private java.lang.String nombre;

    private java.lang.String paterno;

    private java.lang.String materno;

    private java.lang.String parentesco;

    private java.lang.String sexo;

    private java.lang.String edad;

    private java.lang.String fechanac;

    private java.lang.String laboral;

    private java.lang.String estatus;

    public Derechohabiente() {
    }

    public Derechohabiente(
           java.lang.String expediente,
           java.lang.String identificador,
           java.lang.String nombre,
           java.lang.String paterno,
           java.lang.String materno,
           java.lang.String parentesco,
           java.lang.String sexo,
           java.lang.String edad,
           java.lang.String fechanac,
           java.lang.String laboral,
           java.lang.String estatus) {
           this.expediente = expediente;
           this.identificador = identificador;
           this.nombre = nombre;
           this.paterno = paterno;
           this.materno = materno;
           this.parentesco = parentesco;
           this.sexo = sexo;
           this.edad = edad;
           this.fechanac = fechanac;
           this.laboral = laboral;
           this.estatus = estatus;
    }


    /**
     * Gets the expediente value for this Derechohabiente.
     * 
     * @return expediente
     */
    public java.lang.String getExpediente() {
        return expediente;
    }


    /**
     * Sets the expediente value for this Derechohabiente.
     * 
     * @param expediente
     */
    public void setExpediente(java.lang.String expediente) {
        this.expediente = expediente;
    }


    /**
     * Gets the identificador value for this Derechohabiente.
     * 
     * @return identificador
     */
    public java.lang.String getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this Derechohabiente.
     * 
     * @param identificador
     */
    public void setIdentificador(java.lang.String identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the nombre value for this Derechohabiente.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this Derechohabiente.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the paterno value for this Derechohabiente.
     * 
     * @return paterno
     */
    public java.lang.String getPaterno() {
        return paterno;
    }


    /**
     * Sets the paterno value for this Derechohabiente.
     * 
     * @param paterno
     */
    public void setPaterno(java.lang.String paterno) {
        this.paterno = paterno;
    }


    /**
     * Gets the materno value for this Derechohabiente.
     * 
     * @return materno
     */
    public java.lang.String getMaterno() {
        return materno;
    }


    /**
     * Sets the materno value for this Derechohabiente.
     * 
     * @param materno
     */
    public void setMaterno(java.lang.String materno) {
        this.materno = materno;
    }


    /**
     * Gets the parentesco value for this Derechohabiente.
     * 
     * @return parentesco
     */
    public java.lang.String getParentesco() {
        return parentesco;
    }


    /**
     * Sets the parentesco value for this Derechohabiente.
     * 
     * @param parentesco
     */
    public void setParentesco(java.lang.String parentesco) {
        this.parentesco = parentesco;
    }


    /**
     * Gets the sexo value for this Derechohabiente.
     * 
     * @return sexo
     */
    public java.lang.String getSexo() {
        return sexo;
    }


    /**
     * Sets the sexo value for this Derechohabiente.
     * 
     * @param sexo
     */
    public void setSexo(java.lang.String sexo) {
        this.sexo = sexo;
    }


    /**
     * Gets the edad value for this Derechohabiente.
     * 
     * @return edad
     */
    public java.lang.String getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this Derechohabiente.
     * 
     * @param edad
     */
    public void setEdad(java.lang.String edad) {
        this.edad = edad;
    }


    /**
     * Gets the fechanac value for this Derechohabiente.
     * 
     * @return fechanac
     */
    public java.lang.String getFechanac() {
        return fechanac;
    }


    /**
     * Sets the fechanac value for this Derechohabiente.
     * 
     * @param fechanac
     */
    public void setFechanac(java.lang.String fechanac) {
        this.fechanac = fechanac;
    }


    /**
     * Gets the laboral value for this Derechohabiente.
     * 
     * @return laboral
     */
    public java.lang.String getLaboral() {
        return laboral;
    }


    /**
     * Sets the laboral value for this Derechohabiente.
     * 
     * @param laboral
     */
    public void setLaboral(java.lang.String laboral) {
        this.laboral = laboral;
    }


    /**
     * Gets the estatus value for this Derechohabiente.
     * 
     * @return estatus
     */
    public java.lang.String getEstatus() {
        return estatus;
    }


    /**
     * Sets the estatus value for this Derechohabiente.
     * 
     * @param estatus
     */
    public void setEstatus(java.lang.String estatus) {
        this.estatus = estatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Derechohabiente)) return false;
        Derechohabiente other = (Derechohabiente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.expediente==null && other.getExpediente()==null) || 
             (this.expediente!=null &&
              this.expediente.equals(other.getExpediente()))) &&
            ((this.identificador==null && other.getIdentificador()==null) || 
             (this.identificador!=null &&
              this.identificador.equals(other.getIdentificador()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.paterno==null && other.getPaterno()==null) || 
             (this.paterno!=null &&
              this.paterno.equals(other.getPaterno()))) &&
            ((this.materno==null && other.getMaterno()==null) || 
             (this.materno!=null &&
              this.materno.equals(other.getMaterno()))) &&
            ((this.parentesco==null && other.getParentesco()==null) || 
             (this.parentesco!=null &&
              this.parentesco.equals(other.getParentesco()))) &&
            ((this.sexo==null && other.getSexo()==null) || 
             (this.sexo!=null &&
              this.sexo.equals(other.getSexo()))) &&
            ((this.edad==null && other.getEdad()==null) || 
             (this.edad!=null &&
              this.edad.equals(other.getEdad()))) &&
            ((this.fechanac==null && other.getFechanac()==null) || 
             (this.fechanac!=null &&
              this.fechanac.equals(other.getFechanac()))) &&
            ((this.laboral==null && other.getLaboral()==null) || 
             (this.laboral!=null &&
              this.laboral.equals(other.getLaboral()))) &&
            ((this.estatus==null && other.getEstatus()==null) || 
             (this.estatus!=null &&
              this.estatus.equals(other.getEstatus())));
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
        if (getExpediente() != null) {
            _hashCode += getExpediente().hashCode();
        }
        if (getIdentificador() != null) {
            _hashCode += getIdentificador().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getPaterno() != null) {
            _hashCode += getPaterno().hashCode();
        }
        if (getMaterno() != null) {
            _hashCode += getMaterno().hashCode();
        }
        if (getParentesco() != null) {
            _hashCode += getParentesco().hashCode();
        }
        if (getSexo() != null) {
            _hashCode += getSexo().hashCode();
        }
        if (getEdad() != null) {
            _hashCode += getEdad().hashCode();
        }
        if (getFechanac() != null) {
            _hashCode += getFechanac().hashCode();
        }
        if (getLaboral() != null) {
            _hashCode += getLaboral().hashCode();
        }
        if (getEstatus() != null) {
            _hashCode += getEstatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Derechohabiente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsystem/", "derechohabiente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expediente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "expediente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paterno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paterno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("materno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "materno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentesco");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parentesco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sexo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sexo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "edad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechanac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechanac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("laboral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "laboral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estatus"));
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
