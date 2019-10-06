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
public class Capability 
{
    private String id;
    private String name;
    private String [] refCapIds; 
    private String [] extensions;
    private String [] subscriptions;
    private String [] publications;
    
    
    public Capability(String id, String name, String [] rxTopics, String [] txTopics, String [] refCapIds, String [] extensions)
    {
        this.id = id;
        this.name = name;
        this.subscriptions = rxTopics.clone();
        this.publications = txTopics.clone();
        this.refCapIds = refCapIds.clone();
        this.extensions = extensions.clone();
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
        if(idx < publications.length)
        {
            return(publications[idx]);
        }
        else
        {
            return(null);
        }
    }
    
    public String GetSubscriptions(int idx)
    {
        if(idx < subscriptions.length)
        {
            return(subscriptions[idx]);
        }
        else
        {
            return(null);
        }
    }
}
