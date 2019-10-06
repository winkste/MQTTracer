/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author stephan_wink
 */
public class Device 
{
    private String id;
    private String name;
    private String type;
    private String [] capListIds;
    private String locat;
    private String fwId;
    private String fwVers;
    private String fwDesc;
    

    public Device(String id, String name, String type, String location, String fwId, String fwVer, String fwDesc, String[] caps) 
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.locat = location;
        this.fwId = fwId;
        this.fwVers = fwVer;
        this.fwDesc = fwDesc;
        this.capListIds = caps.clone();     
    }
    
    public String GetId()
    {
        return(this.id);
    }
    
    public String GetName()
    {   
        return(this.name);
    }
    
    public String GetType()
    {
        return(this.type);
    }
    
    public String GetCapIdAtIdx(int idx)
    {
        if(idx < this.capListIds.length)
        {
            return(this.capListIds[idx]);
        }
        else
        {
            return(null);
        }
    }

    public String GetPayloadForTxTopic(int idx) 
    {
        return("na");
    }

            
}
