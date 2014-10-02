App = Ember.Application.create();

App.Router.map(function() {
  this.resource('flights' , {path : '/flights'});
});

App.IndexRoute = Ember.Route.extend({
    model: function() {
        //return this.store.find('Flight');
        return $.getJSON("http://localhost:8080/mvn-webapp-flights/search?origin=LAX&destination=MEX&passengers=1&date=2014-12-19", function(data) {
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

//App.Flight.reopenClass({
//    FIXTURES: [
//        {
//            id: 1,
//            departureIATA: "SFO",
//            destinationIATA: "LAX",
//            departuretime: "26/06/1991 07:45:00",
//            destinationtime: "26/06/1991 11:45:00",
//            departurecity: "San Francisco",
//            destinationcity: "Los Angeles",
//            price: "789.55"
//        },{
//            id: 2,
//            departureIATA: "MEX",
//            destinationIATA: "SFO",
//            departuretime: "28/12/1991 07:45:00",
//            destinationtime: "29/12/1991 12:45:00",
//            departurecity: "Ciudad de Mexico",
//            destinationcity: "San Francisco",
//            price: "789.55"
//        },{
//            id: 3,
//            departureIATA: "LAX",
//            destinationIATA: "MEX",
//            departuretime: "29/12/2001 07:45:00",
//            destinationtime: "29/12/2001 8:45:00",
//            departurecity: "Los Angeles",
//            destinationcity: "Ciudad de Mexico",
//            price: "789.55"
//        }
//    ]
//});