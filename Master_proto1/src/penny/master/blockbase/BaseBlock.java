/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package penny.master.blockbase;

/**
 *
 * @author jens
 */
public class BaseBlock {
    private TYPE type = TYPE.NONE;
    private String name = "Basic block";
    private Object status = null; //Dit zou eigenlijk ALTIJD een enum moeten zijn, anders gaat json klagen :(
    private final String klasse = this.getClass().getCanonicalName();
    private String statusKlasseNaam = "";
    private String locatie = null;
    private String description = null;

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

    public String getLocatie() {
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
    
    public String getDescription() {
    	if (description == null)
    		return "Dit is een standaard " + this.getKlasse();
    	else{
    		return description;
    	}
	}

}
