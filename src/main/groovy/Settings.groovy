/**
 * Created by toddecus on 01/11/2015
 * ApplicationSettings object to centralize debugging.
 */
class Settings {
    static final String API_BASE_URL = 'http://swapi.co/api/'
    static final String USER_AGENT = 'SWAPI-GROOVY'
    static final TYPES =
            ['PEOPLE'   : 'people',
             'PLANETS'  : 'planets',
             'STARSHIPS': 'starships',
             'VEHICLES' : 'vehicles',
             'FILMS'    : 'films',
             'SPECIES'  : 'species']
    static final DEBUG_FLAG = false; // turn on to get SWAPI to output JSON response along the way
}


