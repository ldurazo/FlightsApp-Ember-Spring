App = Ember.Application.create({
    LOG_ACTIVE_GENERATION: true,
    LOG_TRANSITIONS: true,
    LOG_TRANSITIONS_INTERNAL: false,
    LOG_VIEW_LOOKUPS: true
});

App.Router.map(function() {
    this.route("reservations");
    this.resource('flights', { path: '/flights'});
});

App.FlightsRoute = Ember.Route.extend({
    model: function() {
//        Uncomment this, this is the model that should be retrieved
//        return this.store.find("flight");

        //this contains flights key on json to consume
//        return $.getJSON("http://www.mocky.io/v2/5445a3371db7968c1c5f24be", function(data) {
//                                              return data;
//                                          });

        //This is without the flights key on json to consume
      return $.getJSON("http://www.mocky.io/v2/542f03d5f190d8220c26e891", function(data) {
                                            return data;
                                        });

    }
});

App.FlightsController = Ember.ArrayController.extend({});

App.ApplicationAdapter = DS.RESTAdapter.extend({
    host:"http://www.mocky.io/v2/5445a3371db7968c1c5f24be"
});

App.ApplicationController = Ember.ObjectController.extend({
      passengerslimit: [1,2,3,4,5,6],
      departureCity:null,
      arrivalCity:null,
      departureDate:null,
      returnDate:null,
      passengersNumber:null,
      actions: {
          searchFlights: function(){
            var departureCity = this.get('departureCity');
            var departureDate = this.get('departureDate');
            var arrivalCity = this.get('arrivalCity');
            var returnDate = this.get('returnDate');
            var passengersNumber = this.get('passengersNumber');
            var searchJson = JSON.stringify({
            departureCity: departureCity,
                            arrivalCity: arrivalCity,
                            departureDate: departureDate,
                            returnDate: returnDate,
                            passengersNumber: passengersNumber
            });
            console.log(searchJson);
            this.transitionToRoute('flights');
          }
      }
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
    arrivalTime: DS.attr('date'),
    departureTime: DS.attr('date'),
    origin: DS.attr('string'),
    destination: DS.attr('string')
});
