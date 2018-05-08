package app.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFilter;
import cronapi.rest.security.CronappSecurity;


/**
 * Classe que representa a tabela LOGMSG
 * @generated
 */
@Entity
@Table(name = "\"LOGMSG\"")
@XmlRootElement
@CronappSecurity
@JsonFilter("app.entity.Logmsg")
public class Logmsg implements Serializable {

  /**
   * UID da classe, necessário na serialização
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * @generated
   */
  @Id
  @Column(name = "id", nullable = false, insertable=true, updatable=true)
  private java.lang.String id = UUID.randomUUID().toString().toUpperCase();

  /**
  * @generated
  */
  @Column(name = "text", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.lang.String text;

  /**
  * @generated
  */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "stamp", nullable = true, unique = false, insertable=true, updatable=true)
  
  private java.util.Date stamp;

  /**
  * @generated
  */
  @ManyToOne
  @JoinColumn(name="fk_user", nullable = true, referencedColumnName = "id", insertable=true, updatable=true)
  
  private User user;

  /**
   * Construtor
   * @generated
   */
  public Logmsg(){
  }


  /**
   * Obtém id
   * return id
   * @generated
   */
  
  public java.lang.String getId(){
    return this.id;
  }

  /**
   * Define id
   * @param id id
   * @generated
   */
  public Logmsg setId(java.lang.String id){
    this.id = id;
    return this;
  }

  /**
   * Obtém text
   * return text
   * @generated
   */
  
  public java.lang.String getText(){
    return this.text;
  }

  /**
   * Define text
   * @param text text
   * @generated
   */
  public Logmsg setText(java.lang.String text){
    this.text = text;
    return this;
  }

  /**
   * Obtém stamp
   * return stamp
   * @generated
   */
  
  public java.util.Date getStamp(){
    return this.stamp;
  }

  /**
   * Define stamp
   * @param stamp stamp
   * @generated
   */
  public Logmsg setStamp(java.util.Date stamp){
    this.stamp = stamp;
    return this;
  }

  /**
   * Obtém user
   * return user
   * @generated
   */
  
  public User getUser(){
    return this.user;
  }

  /**
   * Define user
   * @param user user
   * @generated
   */
  public Logmsg setUser(User user){
    this.user = user;
    return this;
  }

  /**
   * @generated
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Logmsg object = (Logmsg)obj;
    if (id != null ? !id.equals(object.id) : object.id != null) return false;
    return true;
  }

  /**
   * @generated
   */
  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

}
