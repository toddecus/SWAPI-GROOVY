/**
 * Created by toddecus on 01/11/2015
 * Schema object to be auto populated from http://swapi.co/resource/schema is called
 * I dreamed of auto createing the models from this schema but don't have time and couldn't find a JSON to Groovy POGO creator with tests of course!
 **/
class JSONSchema {
    def $schema
    def description
    Map properties
    List required
    def title
    def type
}
