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
public class Topic 
{
    private String id_str;
    private String chan_str;
    private String payloads[];
    
    public Topic(String id_str, String chan_str)
    {
        this.id_str = id_str;
        this.chan_str = chan_str;
    }
    
    public Topic(String id_str, String chan_str, String payloads[])
    {
        this(id_str, chan_str);   
        this.payloads = payloads.clone();
    }
    
    public String GetId()
    {
        return(this.id_str);
    }
    
    public String GetChan()
    {
        return(this.chan_str);
    }
    
    public String GetPayloadAt(int idx)
    {
        if(null != payloads)
        {
            if(idx < this.payloads.length)
            {
                return(this.payloads[idx]);
            }
            else
            {
                return(null);
            }
        }
        else
        {
            return(null);
        }
    }
}
