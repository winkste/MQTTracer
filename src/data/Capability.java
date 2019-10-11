/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;

/**
 *
 * @author stephan_wink
 */
public class Capability 
{
    private String id;
    private String name;
    private String [] refCapIds; 
    private String [] extensions;
    //private String [] subscriptions;
    //private String [] publications;
    private List <Topic> subs;
    private List <Topic> pubs;
    
   

    Capability(String id, String name, List<Topic> subs, List<Topic> pubs, String[] capRef, String[] extensions) 
    {
        this.id = id;
        this.name = name;
        this.refCapIds = capRef.clone();
        this.extensions = extensions.clone();
        this.subs = subs;
        this.pubs = pubs;
    }
    
    public String GetId()
    {
        return(this.id);
    }
    
    public String GetName()
    {
        return(this.name);
    }
    
    public String GetPublications(int idx)
    {
        if(null != pubs)
        {
            if(idx < pubs.size())
            {
                return(pubs.get(idx).GetId());
            }
        }
        return(null);
    }
    
    public Topic GetPublicationByIndex(int idx)
    {
        if(null != pubs)
        {
            if(idx < pubs.size())
            {
                return(pubs.get(idx));
            }
        }
        return(null);
    }
    
    public Topic GetPublicationById(String id)
    {
        if(null != this.pubs)
        {
            for(int idx = 0; idx < this.pubs.size(); idx++)
            {
                if(this.pubs.get(idx).equalsId(id))
                {
                    return(this.pubs.get(idx));
                }
            }
        }
        
        return(null);
    }
    
    public Topic GetSubscriptionById(String id)
    {
        if(null != this.subs)
        {
            for(int idx = 0; idx < this.subs.size(); idx++)
            {
                if(this.subs.get(idx).equalsId(id))
                {
                    return(this.subs.get(idx));
                }
            }
        }
        
        return(null);
    }
    
    public String GetSubscriptions(int idx)
    {
        if(null != subs)
        {
            if(idx < subs.size())
            {
                return(subs.get(idx).GetId());
            }
        }
        
        return(null);
    }
}
