package penny.master.networking;

import java.lang.reflect.Constructor;

import com.google.gson.Gson;

import android.util.Log;

import penny.master.blockbase.BaseBlock;

public class JSONObjectManager {
	
    /**
    *
    * @param A baseblock
    * @return An JSON - encoded string, ready for transmission
    */
   public static String getJsonObject(BaseBlock block){
       //TODO: testen of blok een regelblok is: moeten anders geparset
       boolean regelBlok = false;
       String json = null;

       if (regelBlok){
    	   //TODO: andere functie, srsly.
       }else{
           //TODO: Serieus hoort hier niet, maar anders nullpointerexept...
           block.setStatusKlasseNaam(block.getStatus().getClass().getCanonicalName());
           json = new Gson().toJson(block);
       }
       return json;
   }

   /**
    *
    * @param a JSON encoded string
    * @return an object with the requested baseblock - inherited class
    */
   @SuppressWarnings({ "unchecked", "rawtypes" })
public static Object decodeJSONObject(String input){
       BaseBlock block = new Gson().fromJson(input, BaseBlock.class);
       String klasse = block.getKlasse();
       String statusKlasse = block.getStatusKlasseNaam();
       String locatie = block.getLocation();
       String description = block.getDescription();
       Object result = null;

        //TODO: locatie + beschrijving gaan NIET gewoon mee
       try{
           Class tocast = Class.forName(klasse);
           Class statuscast = Class.forName(statusKlasse);
           Class parameterType[] = new Class[2];
           parameterType[0] = String.class; //2e paramtype in switch
           Object argList[] = new Object[2];
           argList[0] = block.getName(); //2e param in switch
           parameterType[1] = statuscast;
           argList[1] = Enum.valueOf(statuscast, block.getStatus().toString());
//           if (tocast.equals(IMOutputBlock.class) || tocast.equals(PersoonSensorBlock.class)) //Beiden gebruiken imnotif --> is hier geen betere manier voor :/
//           {
//               parameterType[1] = IMSTATUS.class;
//               argList[1] = IMSTATUS.valueOf(block.getStatus().toString());
//           }
           Constructor ct = tocast.getConstructor(parameterType);
           result = ct.newInstance(argList);
           
           //Extra onbekende informatie
           BaseBlock b = (BaseBlock)result;
           if(locatie != null)
        	   b.setLocatie(locatie);
           if (description != null)
        	   b.setDescription(description);
           
       }catch (ClassNotFoundException ex)
       {
           Log.e("Json Manager", "Could not find class with name " + ex.getMessage());
           ex.printStackTrace();
       }catch (ClassCastException ex){
           Log.e("Json Manager","Could not cast the class");
           ex.printStackTrace();
       }catch (NoSuchMethodException ex){
    	   Log.e("Json Manager","Could not find associated methods");
           ex.printStackTrace();
       }catch (InstantiationException ex){
    	   Log.e("Json Manager","Could not instantiate new object");
           ex.printStackTrace();
       }catch (Throwable ex){
    	   Log.e("Json Manager","An other error has occured");
           ex.printStackTrace();
       }
       return result;
   }

}
