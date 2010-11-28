/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package penny.master.blockbase;

import java.util.UUID;

import penny.master.proto1.R;

/**
 *
 * @author jens
 */
public class BaseBlock implements Cloneable{
    private TYPE type = TYPE.NONE;
    private String name = "Basic block";
    private Object status = null; //Dit zou eigenlijk ALTIJD een enum moeten zijn, anders gaat json klagen :(
    private final String klasse = this.getClass().getCanonicalName();
    private String statusKlasseNaam = "";
    private String locatie = null;
    private String description = null;
    private UUID id = UUID.randomUUID();
    
    //Android only normaal: Refactor indien 'echt' gebruikt - Wis indien niet gebruikt
    private int imageresID = R.drawable.icon;

    public String getName() {
        return name;
    }
    public Object getStatus() {
        return status;
    }
    public TYPE getType() {
        return type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStatus(Object status) {
        this.status = status;
    }
    public void setType(TYPE type) {
        this.type = type;
    }
    public boolean checkStatus(Object status){
        return (this.status.equals(status));
    }
    public String getLocation() {
        return locatie;
    }
    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }
    public String getKlasse() {
        return klasse;
    }
    //Erm...worden nu 'geset' in jsonbuilder, maar hoort daar niet thuis
    public String getStatusKlasseNaam() {
        return statusKlasseNaam;
    }
    public void setStatusKlasseNaam(String statusKlasseNaam) {
        this.statusKlasseNaam = statusKlasseNaam;
    }
    public void setDescription(String description) {
		this.description = description;
	}
    public UUID getId() {
        return id;
    }
    @Override
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch( CloneNotSupportedException e )
        {
            return null;
        }
     }

    public int getImageresID() {
		return imageresID;
	}
	public void setImageresID(int imageresID) {
		this.imageresID = imageresID;
	}
	public String getDescription() {
    	if (description == null)
    		return "Dit is een standaard " + this.getKlasse();
    	else{
    		return description;
    	}
    }
}
