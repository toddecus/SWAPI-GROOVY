// uncoment this to run this as a groovy script at the command line.  use IVY for dependencies rather than gradle.
// do not leave uncommented if using gradle. << learned that one the hard way.
//@Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.7')
import models.*

/**
 * Created by toddecus on 1/11/2015
 * Simple example application to demo the api
 */
public class SWAPIExample {

    public static void main(String [] args) {
        def SWAPI swapi = new SWAPI()
        Film f = swapi.getFilm(1)
        Person p = swapi.getPerson(4)
        Vehicle v = swapi.getVehicle(14)
        Starship s = swapi.getStarship(22)
        Planet pp = swapi.getPlanet(1)
        Species sp = swapi.getSpecies(1)


        println f.title
        println p.name
        println v.name
        println s.name
        println pp.name
        println sp.name

        SWAPIModelList swList = swapi.getAllbyType(Settings.TYPES.FILMS)
        println "List of " + swList.count + " Films"
        swList.results.each {
            Film films = new Film(it)
            println films.title + ", by: "+ films.director

        }

    }
}
