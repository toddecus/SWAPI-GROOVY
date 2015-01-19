//@Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.7')
// failed attempt at heady notion of generating model from schema
import groovyx.net.http.*
import groovyx.net.http.ContentType.*
import groovyx.net.http.Method.*

/**
 * Created by tellermann on 1/11/2015
 * Started down the path of attempting to autogenerate the models.  need a lib to JSON to POGO to make this happen.
 */

def swapi = new RESTClient('http://swapi.co/api/')
swapi.headers.'User-Agent' = 'SWAPI-GROOVY'
def ship = swapi.get(path: 'starships/schema/')?.data

println('toString()')
println(ship.toString())

