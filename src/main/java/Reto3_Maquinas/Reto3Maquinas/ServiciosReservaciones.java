
package Reto3_Maquinas.Reto3Maquinas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @YeisonO
 * @version 1.1
 */
@Service
public class ServiciosReservaciones {
    /**
     * creación de variable de tipo Repositorio con la anotación
     */ 
    @Autowired
    private RepositorioReservaciones metodosCrud;
    
    /**
     * metodo para obtener todos los datos de la tabla reservaciones
     * @return List de clase Reservacion
     */
    public List<Reservaciones> getAll(){
        return metodosCrud.getAll();
    }
    
    /**
     * metodo para obtener dato de la tabla reservaciones por Id
     * @param reservationId
     * @return Optional de clase Reservacion
     */
    public Optional<Reservaciones> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }
    
    /**
     * metodo para registrar valores en la tabla reservaciones
     * @param reservation
     * @return valor de clase Reservacion
     */
    public Reservaciones save(Reservaciones reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservaciones> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }
    
    /**
     * metodo para actualizar un dato de la tabla Reservaciones
     * @param reservation
     * @return valor de clase Reservacion
     */
    public Reservaciones update(Reservaciones reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservaciones> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }
    
    /**
     * metodo para borrar un dato de la tabla Reservaciones por Id
     * @param reservationId
     * @return boolean
     */
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
   
    /**
    * Metodo para adquirir status
    * @return StatusReservas
    */
    public StatusReservas reporteStatusServicio (){
        List<Reservaciones>completed= metodosCrud.ReservacionStatusRepositorio("completed");
        List<Reservaciones>cancelled= metodosCrud.ReservacionStatusRepositorio("cancelled");
        
        return new StatusReservas(completed.size(), cancelled.size() );
    }
    
    /**
     * Metodo para el reporte de tiempo
     * @param datoA
     * @param datoB
     * @return ListaReservaciones
     */
    public List<Reservaciones> reporteTiempoServicio (String datoA, String datoB){
        SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
        
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
             datoUno = parser.parse(datoA);
             datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return metodosCrud.ReservacionTiempoRepositorio(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        
        } 
    }
    
    /**
     * metodo para reporte de clientes
     * @return listaClientes
     */
    public List<ContadorClientes> reporteClientesServicio(){
            return metodosCrud.getClientesRepositorio();
        }

}
