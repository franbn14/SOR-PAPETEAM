/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taller;

import CEN.*;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author alberto
 */
public class Autoselection {
    ArrayList<OfferCEN> best;
    ArrayList<OfferCEN> offers;
    int finalAmount;
    double finalPrice;
    
    public Autoselection(ArrayList<OfferCEN> offerList, RequestCEN request) {
        best= new ArrayList<OfferCEN>();
        offers=new ArrayList<OfferCEN>(offerList);
        
        finalAmount=request.getAmount();
        finalPrice=request.getMaxPrice();
        
        Collections.sort(offers);
        /*System.out.print("Despues: ");
        for(OfferCEN o: offers)
            System.out.print(o.getPrice()+" ");
        System.out.println("");*/
    }
    
    public double totalPrice(ArrayList<OfferCEN> list) {
        double total=0;

        if(list.size()==0)
            total=finalPrice+1.0;
        else
            for(OfferCEN offer: list) 
                total+=offer.getPrice();
        
        return total;
    }
    
    public int totalAmount(ArrayList<OfferCEN> list) {
        int total=0;
        
        if(list.size()==0)
            total=0;
        else
            for(OfferCEN offer: list) 
                total+=offer.getAmount();
        
        return total;
    }
         
    public void sortOffersByPrice() {
        
    }
            
    private void solution(ArrayList<OfferCEN> current, int index) {        
        boolean inserted=false;
        
        for(int i=index+1; i<offers.size(); i++) {            
            if(index==-1 || totalAmount(current)+offers.get(i).getAmount()<=finalAmount || totalPrice(current)+offers.get(i).getPrice()<=finalPrice) {
                inserted=true;               
                ArrayList<OfferCEN> aux=new ArrayList<OfferCEN>(current);
                aux.add(offers.get(i));
                solution(aux, i);
            }
                
        }        
        if(!inserted) {
            
            int amount=totalAmount(current);
            double price=totalPrice(current);
            
            if((amount>=totalAmount(best) && amount<=finalAmount) &&  price<totalPrice(best)) {
                best=current;
            }
        }
            
    }
    
    public String getBest() {      
        String accepted="";
        ArrayList<OfferCEN> first=new ArrayList<OfferCEN>();        
        solution(first,-1);
        
        //System.out.println("Mejor: "+best.size()+" ofertas de "+totalAmount(best)+" cantidades y "+totalPrice(best)+" precio");
        for(OfferCEN offer: best)
            accepted+=offer.getCode()+" ";
        
        return accepted;
    }
    
}
