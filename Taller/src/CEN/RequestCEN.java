/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CEN;


import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author alberto
 */
public class RequestCEN {
    private int code;
    private Date deadline;
    private String type;
    private Double size;
<<<<<<< HEAD
    private int sizeUnit;
=======
    private Integer sizeUnit;
>>>>>>> 77745aba8873573b304986371074292ad3268110
    private String color;
    private Integer amount;
    private Double maxPrice;
    private ClientCEN client;
    private boolean autoElect;
    private boolean finished;

<<<<<<< HEAD
    private void setAttributes(Date deadline, String type, Double size, int sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoElect, boolean finished) {
=======
    private void setAttributes(Date deadline, String type, Double size, Integer sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoElect, boolean finished) {
>>>>>>> 77745aba8873573b304986371074292ad3268110
        this.deadline = deadline;
        this.type = type;
        this.size = size;
        this.sizeUnit =  sizeUnit;
        this.color = color;
        this.amount = amount;
        this.maxPrice = maxPrice;
        this.amount = amount;
        this.client= client;
        this.autoElect = autoElect;
        this.finished = finished;
        
        if(client == null)
            userError();
    }
    
    public RequestCEN() {
    }
    
<<<<<<< HEAD
    public RequestCEN(Date deadline, String type, Double size, int sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoSelec, boolean finished) {        
=======
    public RequestCEN(Date deadline, String type, Double size, Integer sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoSelec, boolean finished) {        
>>>>>>> 77745aba8873573b304986371074292ad3268110
        setAttributes(deadline, type, size, sizeUnit, color, amount, maxPrice, client, autoSelec, finished);      
        this.code = -1;
    }
    
    public void userError() {
        System.err.println("Error: se ha realizado una solicitud sin ser usuario del sistema");
    }
    
    public int getCode() {
        return code;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UserCEN getClient() {
        return client;
    }

    public void setClient(ClientCEN client) {
        this.client = client;
    }

    public Date getdeadline() {
        return deadline;
    }

    public void setdeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

<<<<<<< HEAD
    public int getSizeUnit() {
=======
    public Integer getSizeUnit() {
>>>>>>> 77745aba8873573b304986371074292ad3268110
        return sizeUnit;
    }

    public String getSizeUnitString(){
        return UnitsCEN.getByID(sizeUnit);
    }
    
<<<<<<< HEAD
    public void setSizeUnit(int sizeUnit) {
=======
    public void setSizeUnit(Integer sizeUnit) {
>>>>>>> 77745aba8873573b304986371074292ad3268110
        this.sizeUnit = sizeUnit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

<<<<<<< HEAD
    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
=======
    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
>>>>>>> 77745aba8873573b304986371074292ad3268110
        this.maxPrice = maxPrice;
    }

    public boolean isAutoElect() {
        return autoElect;
    }

    public void setAutoElect(boolean autoSelec) {
        this.autoElect = autoSelec;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
            
   
    
    @Override
    public String toString(){
<<<<<<< HEAD
        return amount + " " + type;
    }
=======
        return ((amount!=null)?amount+" ":"") + type + " " + ((color!=null)?color+" ":"") + 
                ((size!=null)?size+" "+darUnidadId(sizeUnit)+" ":"");
    }

    private static String darUnidadId(Integer id) {
        servicios.DarUnidades_Service service = new servicios.DarUnidades_Service();
        servicios.DarUnidades port = service.getDarUnidadesPort();
        return port.darUnidadId(id);
    }
    
    
>>>>>>> 77745aba8873573b304986371074292ad3268110
}
