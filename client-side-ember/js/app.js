App = Ember.Application.create();

App.Router.map(function() {
  this.resource('flights' , {path : '/flights'});
});

App.IndexRoute = Ember.Route.extend({
    model: function() {
        //Uncomment this, this is the model that should be retrieved
        //return this.store.find('Flight');

        //Uncomment this to make calls to the API directly
//        return $.getJSON("http://localhost:8080/mvn-webapp-flights/search?origin=LAX&destination=MEX&passengers=1&date=2014-12-19", function(data) {
//            return data;
//        });

        //this code retrieves a dummy json instead of callin the API (remember that the response from this link requires a Access-Control-Allow-Origin: * header)
        return $.getJSON("http://www.mocky.io/v2/542f03d5f190d8220c26e891", function(data) {
                    return data;
                });
    }
});

App.IndexController = Ember.ArrayController.extend({});

App.FlightsRoute = Ember.Route.extend({});

App.ApplicationController = Ember.ObjectController.extend({
  passengerslimit: [1,2,3,4,5,6]
});

App.ApplicationAdapter = DS.FixtureAdapter.extend({
});

App.Flight = DS.Model.extend({
    saleTotal: DS.attr('string'),
    slices: DS.hasMany('slice')
});

App.Slice = DS.Model.extend({
    segments: DS.hasMany('segment')
});

App.Segment = DS.Model.extend({
    legs: DS.hasMany('leg')
});

App.Leg = DS.Model.extend({
    arrivalTime: DS.attr('string'),
    departureTime: DS.attr('string'),
    origin: DS.attr('string'),
    destination: DS.attr('string')
});
