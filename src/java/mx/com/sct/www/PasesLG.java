/**
 * PasesLG.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.sct.www;

public class PasesLG  implements java.io.Serializable {
    private java.lang.String pase;

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

    private java.lang.String mediconombre;

    private java.lang.String medicopaterno;

    private java.lang.String medicomaterno;

    private java.lang.String clinicareferencia;

    private DetalleEstudios[] listaEstudios;

    public PasesLG() {
    }

    public PasesLG(
           java.lang.String pase,
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
           java.lang.String estatus,
           java.lang.String mediconombre,
           java.lang.String medicopaterno,
           java.lang.String medicomaterno,
           java.lang.String clinicareferencia,
           DetalleEstudios[] listaEstudios) {
           this.pase = pase;
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
           this.mediconombre = mediconombre;
           this.medicopaterno = medicopaterno;
           this.medicomaterno = medicomaterno;
           this.clinicareferencia = clinicareferencia;
           this.listaEstudios = listaEstudios;
    }


    /**
     * Gets the pase value for this PasesLG.
     * 
     * @return pase
     */
    public java.lang.String getPase() {
        return pase;
    }


    /**
     * Sets the pase value for this PasesLG.
     * 
     * @param pase
     */
    public void setPase(java.lang.String pase) {
        this.pase = pase;
    }


    /**
     * Gets the expediente value for this PasesLG.
     * 
     * @return expediente
     */
    public java.lang.String getExpediente() {
        return expediente;
    }


    /**
     * Sets the expediente value for this PasesLG.
     * 
     * @param expediente
     */
    public void setExpediente(java.lang.String expediente) {
        this.expediente = expediente;
    }


    /**
     * Gets the identificador value for this PasesLG.
     * 
     * @return identificador
     */
    public java.lang.String getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this PasesLG.
     * 
     * @param identificador
     */
    public void setIdentificador(java.lang.String identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the nombre value for this PasesLG.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this PasesLG.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the paterno value for this PasesLG.
     * 
     * @return paterno
     */
    public java.lang.String getPaterno() {
        return paterno;
    }


    /**
     * Sets the paterno value for this PasesLG.
     * 
     * @param paterno
     */
    public void setPaterno(java.lang.String paterno) {
        this.paterno = paterno;
    }


    /**
     * Gets the materno value for this PasesLG.
     * 
     * @return materno
     */
    public java.lang.String getMaterno() {
        return materno;
    }


    /**
     * Sets the materno value for this PasesLG.
     * 
     * @param materno
     */
    public void setMaterno(java.lang.String materno) {
        this.materno = materno;
    }


    /**
     * Gets the parentesco value for this PasesLG.
     * 
     * @return parentesco
     */
    public java.lang.String getParentesco() {
        return parentesco;
    }


    /**
     * Sets the parentesco value for this PasesLG.
     * 
     * @param parentesco
     */
    public void setParentesco(java.lang.String parentesco) {
        this.parentesco = parentesco;
    }


    /**
     * Gets the sexo value for this PasesLG.
     * 
     * @return sexo
     */
    public java.lang.String getSexo() {
        return sexo;
    }


    /**
     * Sets the sexo value for this PasesLG.
     * 
     * @param sexo
     */
    public void setSexo(java.lang.String sexo) {
        this.sexo = sexo;
    }


    /**
     * Gets the edad value for this PasesLG.
     * 
     * @return edad
     */
    public java.lang.String getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this PasesLG.
     * 
     * @param edad
     */
    public void setEdad(java.lang.String edad) {
        this.edad = edad;
    }


    /**
     * Gets the fechanac value for this PasesLG.
     * 
     * @return fechanac
     */
    public java.lang.String getFechanac() {
        return fechanac;
    }


    /**
     * Sets the fechanac value for this PasesLG.
     * 
     * @param fechanac
     */
    public void setFechanac(java.lang.String fechanac) {
        this.fechanac = fechanac;
    }


    /**
     * Gets the laboral value for this PasesLG.
     * 
     * @return laboral
     */
    public java.lang.String getLaboral() {
        return laboral;
    }


    /**
     * Sets the laboral value for this PasesLG.
     * 
     * @param laboral
     */
    public void setLaboral(java.lang.String laboral) {
        this.laboral = laboral;
    }


    /**
     * Gets the estatus value for this PasesLG.
     * 
     * @return estatus
     */
    public java.lang.String getEstatus() {
        return estatus;
    }


    /**
     * Sets the estatus value for this PasesLG.
     * 
     * @param estatus
     */
    public void setEstatus(java.lang.String estatus) {
        this.estatus = estatus;
    }


    /**
     * Gets the mediconombre value for this PasesLG.
     * 
     * @return mediconombre
     */
    public java.lang.String getMediconombre() {
        return mediconombre;
    }


    /**
     * Sets the mediconombre value for this PasesLG.
     * 
     * @param mediconombre
     */
    public void setMediconombre(java.lang.String mediconombre) {
        this.mediconombre = mediconombre;
    }


    /**
     * Gets the medicopaterno value for this PasesLG.
     * 
     * @return medicopaterno
     */
    public java.lang.String getMedicopaterno() {
        return medicopaterno;
    }


    /**
     * Sets the medicopaterno value for this PasesLG.
     * 
     * @param medicopaterno
     */
    public void setMedicopaterno(java.lang.String medicopaterno) {
        this.medicopaterno = medicopaterno;
    }


    /**
     * Gets the medicomaterno value for this PasesLG.
     * 
     * @return medicomaterno
     */
    public java.lang.String getMedicomaterno() {
        return medicomaterno;
    }


    /**
     * Sets the medicomaterno value for this PasesLG.
     * 
     * @param medicomaterno
     */
    public void setMedicomaterno(java.lang.String medicomaterno) {
        this.medicomaterno = medicomaterno;
    }


    /**
     * Gets the clinicareferencia value for this PasesLG.
     * 
     * @return clinicareferencia
     */
    public java.lang.String getClinicareferencia() {
        return clinicareferencia;
    }


    /**
     * Sets the clinicareferencia value for this PasesLG.
     * 
     * @param clinicareferencia
     */
    public void setClinicareferencia(java.lang.String clinicareferencia) {
        this.clinicareferencia = clinicareferencia;
    }


    /**
     * Gets the listaEstudios value for this PasesLG.
     * 
     * @return listaEstudios
     */
    public DetalleEstudios[] getListaEstudios() {
        return listaEstudios;
    }


    /**
     * Sets the listaEstudios value for this PasesLG.
     * 
     * @param listaEstudios
     */
    public void setListaEstudios(DetalleEstudios[] listaEstudios) {
        this.listaEstudios = listaEstudios;
    }

    public DetalleEstudios getListaEstudios(int i) {
        return this.listaEstudios[i];
    }

    public void setListaEstudios(int i, DetalleEstudios _value) {
        this.listaEstudios[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PasesLG)) return false;
        PasesLG other = (PasesLG) obj;
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
              this.estatus.equals(other.getEstatus()))) &&
            ((this.mediconombre==null && other.getMediconombre()==null) || 
             (this.mediconombre!=null &&
              this.mediconombre.equals(other.getMediconombre()))) &&
            ((this.medicopaterno==null && other.getMedicopaterno()==null) || 
             (this.medicopaterno!=null &&
              this.medicopaterno.equals(other.getMedicopaterno()))) &&
            ((this.medicomaterno==null && other.getMedicomaterno()==null) || 
             (this.medicomaterno!=null &&
              this.medicomaterno.equals(other.getMedicomaterno()))) &&
            ((this.clinicareferencia==null && other.getClinicareferencia()==null) || 
             (this.clinicareferencia!=null &&
              this.clinicareferencia.equals(other.getClinicareferencia()))) &&
            ((this.listaEstudios==null && other.getListaEstudios()==null) || 
             (this.listaEstudios!=null &&
              java.util.Arrays.equals(this.listaEstudios, other.getListaEstudios())));
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
        if (getMediconombre() != null) {
            _hashCode += getMediconombre().hashCode();
        }
        if (getMedicopaterno() != null) {
            _hashCode += getMedicopaterno().hashCode();
        }
        if (getMedicomaterno() != null) {
            _hashCode += getMedicomaterno().hashCode();
        }
        if (getClinicareferencia() != null) {
            _hashCode += getClinicareferencia().hashCode();
        }
        if (getListaEstudios() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaEstudios());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaEstudios(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PasesLG.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsystem/", "pasesLG"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pase");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediconombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mediconombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medicopaterno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "medicopaterno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medicomaterno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "medicomaterno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clinicareferencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clinicareferencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaEstudios");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaEstudios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsystem/", "detalleEstudios"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
