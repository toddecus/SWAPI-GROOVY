import models.*
import spock.lang.Specification

/**
 * Created by toddecus on 1/11/2015
 * Spock specification "tests" of the SWAPI-Groovy API
 */
class SWAPISpecification extends Specification {
    def "GetAllbyType"() {
        setup:
        SWAPI swapi = new SWAPI()
        SWAPIModelList sml = swapi.getAllbyType(Settings.TYPES.FILMS)
        expect:
        sml.count==6
        sml.results.each{println "Films"+it}
    }
    def "GetAllModels"(){
        setup:
        SWAPI swapi = new SWAPI()
        def modelsMap = swapi.getAllModels()
        expect:
        modelsMap.size()==6
        modelsMap.(Settings.TYPES.FILMS)=="http://swapi.co/api/films/"
        modelsMap.(Settings.TYPES.PEOPLE)=="http://swapi.co/api/people/"
        modelsMap.(Settings.TYPES.PLANETS)=="http://swapi.co/api/planets/"
        modelsMap.(Settings.TYPES.SPECIES)=="http://swapi.co/api/species/"
        modelsMap.(Settings.TYPES.VEHICLES)=="http://swapi.co/api/vehicles/"
        modelsMap.(Settings.TYPES.STARSHIPS)=="http://swapi.co/api/starships/"
    }
    def "GetSchemaByType"(){
        setup:
        SWAPI swapi = new SWAPI()
        JSONSchema schema = swapi.getSchemaByType(Settings.TYPES.FILMS)
        expect:
        schema.title=="Film"

    }

    def "GetFilm"() {
        setup:
        SWAPI swapi = new SWAPI()
        Film f = swapi.getFilm(1)
        expect:
        f.title == 'A New Hope'

    }

    def "GetPerson"() {
        setup:
        SWAPI swapi = new SWAPI()
        Person p = swapi.getPerson(4)
        expect:
        p.name == 'Darth Vader'
    }

    def "GetStarship"() {
        setup:
        SWAPI swapi = new SWAPI()
        Starship s = swapi.getStarship(22)
        expect:
        s.name == 'Imperial shuttle'
    }

    def "GetPlanet"() {
        setup:
        SWAPI swapi = new SWAPI()
        Planet pp = swapi.getPlanet(1)
        expect:
        pp.name == 'Tatooine'
    }

    def "GetVehicle"() {
        setup:
        SWAPI swapi = new SWAPI()
        Vehicle v = swapi.getVehicle(14)
        expect:
        v.name == 'Snowspeeder'
    }

    def "GetSpecies"() {
        setup:
        SWAPI swapi = new SWAPI()
        Species sp = swapi.getSpecies(1)
        expect:
        sp.name == 'Human'
    }

    def "GetTypeFromURLPath"() {
        setup:
        SWAPI swapi = new SWAPI()
        def exampleURL = 'http://swapi.co/api/people/1/'
        def exampleURL2 = 'http://swapi.co/api/people/'
        expect:
        swapi.getTypeFromURLPath(exampleURL) == Settings.TYPES.PEOPLE
        swapi.getTypeFromURLPath(exampleURL2) == Settings.TYPES.PEOPLE

    }

    def "GetIdFromURLPath"() {
        setup:
        def SWAPI swapi = new SWAPI()
        def exampleURL = 'http://swapi.co/api/people/1/'

        expect:
        swapi.getIdFromURLPath(exampleURL) == 1
    }

    def "GetIdsFromURLList"() {
        setup:
        def SWAPI swapi = new SWAPI()
        Person p = swapi.getPerson(1)
        expect:
        swapi.getIdsFromURLList(p.films) == [1, 2, 3, 6]
    }

    def "GetListofModelsFromURLList"() {
        setup:
        def SWAPI swapi = new SWAPI()
        Person p = swapi.getPerson(1)
        expect:
        swapi.getListofModelsFromURLList(p.films)[0].title =='A New Hope'

    }
}
