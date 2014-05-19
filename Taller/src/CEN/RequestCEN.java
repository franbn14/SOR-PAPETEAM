/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CEN;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author alberto
 */
public class RequestCEN {
    private int code;
    private Date deadline;
    private String type;
    private Double size;
    private Integer sizeUnit;
    private String color;
    private Integer amount;
    private Double maxPrice;
    private ClientCEN client;
    private boolean autoElect;
    private boolean finished;
    private boolean expired;
    
    private void setAttributes(Date deadline, String type, Double size, Integer sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoElect, boolean finished, boolean expired) {
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
        this.expired = expired;
        
        if(client == null)
           userError();
    }
    
    public RequestCEN() {
    }
        
    public RequestCEN(Date deadline, String type, Double size, Integer sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoSelec, boolean finished, boolean expired) {        
        setAttributes(deadline, type, size, sizeUnit, color, amount, maxPrice, client, autoSelec, finished, expired);      
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

    public Integer getSizeUnit() {
        return sizeUnit;
    }
   
    public void setSizeUnit(Integer sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }    
            
    @Override
    public String toString(){
        
        return ((amount!=null)?amount+" ":"") + type + ((color!=null)?" | "+color:"") + 
                ((size!=null)?" | "+size+darUnidadId(sizeUnit)+" ":"") + ((maxPrice!=null)?" | "+maxPrice+"€":"");
    }

    private static String darUnidadId(Integer id) {
        servicios.DarUnidades_Service service = new servicios.DarUnidades_Service();
        servicios.DarUnidades port = service.getDarUnidadesPort();
        return port.darUnidadId(id);
    }
}
