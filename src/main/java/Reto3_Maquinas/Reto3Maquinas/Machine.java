
package Reto3_Maquinas.Reto3Maquinas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @YeisonO
 * @version 1.1
 */
@Entity
@Table(name = "machine")
public class Machine implements Serializable{
    
    /**
     * creación de Primary Key y variables de la Tabla Machine
     */ 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String brand;
    private Integer year;
    private String description;
    
    /**
     * Relacion Muchos a Uno con la Tabla Category 
     */ 
    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties("machines")
    private Categoria category;
    
    /**
     * Relacion Uno a Muchos con la Tabla Mensajes 
     */ 
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "machine")
    @JsonIgnoreProperties({"machine","client"})
    private List<Mensaje> messages;
    
    /**
     * Relacion Uno a Muchos con la Tabla Reservations 
     */ 
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "machine")
    @JsonIgnoreProperties({"machine","client"})
    private List<Reservaciones> reservations;
    
    /**
     * Get Id 
     */ 
    public Integer getId() {
        return id;
    }
    
    /**
     * Set Id 
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Get Name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set Name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get Brand
     */
    public String getBrand() {
        return brand;
    }
    
    /**
     * Set Brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    /**
     * Get Year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Set Year
     */
    public void setYear(Integer year) {
        this.year = year;
    }
    
    /**
     * Get Description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Set Description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Get Category
     */
    public Categoria getCategory() {
        return category;
    }
    
    /**
     * Set Category
     */
    public void setCategory(Categoria category) {
        this.category = category;
    }

    /**
     * metodo para obtener dato de la tabla Mensajes por Id
     * @param Mensaje
     * @return messages
     */
    public List<Mensaje> getMessages() {
        return messages;
    }
    
    /**
     * metodo para setear dato de la tabla Mensajes por Id
     * @param messages
     * @return messages
     */
    public void setMessages(List<Mensaje> messages) {
        this.messages = messages;
    }
    
    /**
     * metodo para obtener dato de la tabla Reservaciones por Id
     * @param Reservaciones
     * @return reservations
     */
    public List<Reservaciones> getReservations() {
        return reservations;
    }
    
    /**
     * metodo para setear dato de la tabla Reservaciones por Id
     * @param reservations
     * @return reservations
     */
    public void setReservations(List<Reservaciones> reservations) {
        this.reservations = reservations;
    }
    
    
}
