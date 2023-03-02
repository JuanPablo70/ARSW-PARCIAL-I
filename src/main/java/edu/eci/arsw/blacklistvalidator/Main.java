/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.util.List;

/**
 *
 * @author hcadavid
 */
public class Main {
    
    public static void main(String a[]){
        String ip = "200.24.34.55";
        //String ip = "212.24.24.55"; //no en lista negra
        HostBlacklistsDataSourceFacade skds = HostBlacklistsDataSourceFacade.getInstance();
        int size = skds.getRegisteredServersCount();
        int n = 10;
        int range = size / n;
        for (int i = 0; i < n; i++) {
            Thread t;
            if (i == n-1){
                t = new Thread(new HostBlackListsValidator(i * range, (range * (i + 1)) + (size % n), ip));
            } else {
                t = new Thread(new HostBlackListsValidator(i * range, range * (i + 1), ip));
            }
            t.start();
        }
        // HostBlackListsValidator hblv=new HostBlackListsValidator(1,1);
        // List<Integer> blackListOcurrences=hblv.checkHost("200.24.34.55");
        // System.out.println("The host was found in the following blacklists:"+blackListOcurrences);
        
    }

    
}
