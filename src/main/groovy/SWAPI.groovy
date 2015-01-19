import groovyx.net.http.RESTClient
import models.*

/**
 * A non thread safe Stars Wars API for http://swapi.co
 *
 * Created by toddecus on 1/11/15.
 *
 * http://linkedin.com/in/toddecus
 **/

public class SWAPI {
    private RESTClient client = new RESTClient(Settings.API_BASE_URL)
    def private final SWAPITypes = Settings.TYPES


    public SWAPI() {
        client.headers.'User-Agent' = Settings.USER_AGENT
    }

    private Object getFromClient(String requestType, int id) {
        if (SWAPITypes.values().contains(requestType)) {
            def jsonResponse = client.get(path: requestType + '/' + id + '/')?.data
            if (Settings.DEBUG_FLAG) println "getFromClient JSON response is: " + jsonResponse
            switch (requestType) {
                case SWAPITypes.FILMS:
                    return new Film(jsonResponse)
                case SWAPITypes.PEOPLE:
                    return new Person(jsonResponse)
                case SWAPITypes.STARSHIPS:
                    return new Starship(jsonResponse)
                case SWAPITypes.SPECIES:
                    return new Species(jsonResponse)
                case SWAPITypes.PLANETS:
                    return new Planet(jsonResponse)
                case SWAPITypes.VEHICLES:
                    return new Vehicle(jsonResponse)
            }
        }
        return null // not a valid requestType
    }

    public SWAPIModelList getAllbyType(String requestType) {
        if (SWAPITypes.values().contains(requestType)) {
            def jsonResponse = client.get(path: requestType + '/')?.data
            if (Settings.DEBUG_FLAG) println "getAllbyType JSON response is: " + jsonResponse
            return new SWAPIModelList(jsonResponse)
        }
        return null
    }

    public Map getAllModels() {
        def jsonResponse = client.get(path: '')?.data
        if (Settings.DEBUG_FLAG) println "getAllModels JSON response is: " + jsonResponse
        return jsonResponse
    }

    public JSONSchema getSchemaByType(String requestType) {
        if (SWAPITypes.values().contains(requestType)) {
            def jsonResponse = client.get(path: requestType + '/schema')?.data
            if (Settings.DEBUG_FLAG) println "getSchemaByType JSON response is: " + jsonResponse
            return new JSONSchema(jsonResponse)
        }
    }

    public Film getFilm(int id) {
        return this.getFromClient(SWAPITypes.FILMS, id)
    }

    public Person getPerson(int id) {
        return this.getFromClient(SWAPITypes.PEOPLE, id)
    }

    public Starship getStarship(int id) {
        return this.getFromClient(SWAPITypes.STARSHIPS, id)
    }

    public Planet getPlanet(int id) {
        return this.getFromClient(SWAPITypes.PLANETS, id)
    }

    public Vehicle getVehicle(int id) {
        return this.getFromClient(SWAPITypes.VEHICLES, id)
    }

    public Species getSpecies(int id) {
        return this.getFromClient(SWAPITypes.SPECIES, id)
    }

    public String getTypeFromURLPath(String fullPath) {
        String stripped = fullPath - Settings.API_BASE_URL // should now look like "requestType/<id>/"
        stripped = stripped - ~/\/\d\//  // remove any id
        stripped = stripped.replaceAll(~/\//, "") //remove any "/" if there was no id
        return stripped

    }

    public Integer getIdFromURLPath(String fullPath) {
        String stripped = fullPath - Settings.API_BASE_URL // should now look like "requestType/<id>/"
        stripped = stripped - ~/\w+\//  //remove the type
        stripped = stripped.replaceAll(~/\//, "") //remove any "/" if there was no id
        return stripped.toInteger()

    }

    public List getIdsFromURLList(List urlList) {
        ArrayList modelIds = new ArrayList()
        urlList.each { modelIds.add(this.getIdFromURLPath(it)) }
        return modelIds
    }
    // assume homogenous list
    public List getListofModelsFromURLList(List urlList) {
        def type = getTypeFromURLPath(urlList[0])
        def modelList = new ArrayList()
        switch (type) {
            case SWAPITypes.FILMS:
                urlList.each { modelList.add(getFilm(getIdFromURLPath(it))) }
                break
            case SWAPITypes.PEOPLE:
                urlList.each { modelList.add(getPerson(getIdFromURLPath(it))) }
                break
            case SWAPITypes.STARSHIPS:
                urlList.each { modelList.add(getStarship(getIdFromURLPath(it))) }
                break
            case SWAPITypes.SPECIES:
                urlList.each { modelList.add(getSpecies(getIdFromURLPath(it))) }
                break
            case SWAPITypes.PLANETS:
                urlList.each { modelList.add(getPlanet(getIdFromURLPath(it))) }
                break
            case SWAPITypes.VEHICLES:
                urlList.each { modelList.add(getVehicle(getIdFromURLPath(it))) }
                break
        }
        return modelList
    }

}
