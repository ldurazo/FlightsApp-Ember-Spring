

App = Ember.Application.create({
    LOG_ACTIVE_GENERATION: true,
    LOG_TRANSITIONS: true,
    LOG_TRANSITIONS_INTERNAL: false,
    LOG_VIEW_LOOKUPS: true
});

App.Router.map(function() {
    this.resource("reservation", { path: '/reservation'});
    this.resource('flights', { path: '/flights'});
});

App.ReservationRoute = Ember.Route.extend({
        model: function() {
            return $.getJSON("http://localhost:8080/mvn-webapp-flights/reservation/"+reservationId, function(data) {
                return data;
            });
        }
});

App.FlightsRoute = Ember.Route.extend({
    model: function() {
        $.ajaxSetup({
          contentType: "application/json; charset=utf-8"
        });
        return $.post("http://localhost:8080/mvn-webapp-flights/search", searchJson, function(response) {
            return response;
        }, 'json');

    }
});

App.FlightsController = Ember.ArrayController.extend({});

App.ReservationController = Ember.ObjectController.extend({

});

//Global variables.
searchJson = null;
reservationId = null;

App.ApplicationController = Ember.ObjectController.extend({
      passengerslimit: [1,2,3,4,5,6],
      departureCity:null,
      arrivalCity:null,
      departureDate:null,
      returnDate:null,
      passengersNumber:null,
      reservationNumber:null,
      reservationEmail:null,
      actions: {
          searchFlights: function(){
            this.transitionToRoute('application');
            var departureCity = this.get('departureCity');
            var departureDate = this.get('departureDate');
            var arrivalCity = this.get('arrivalCity');
            var returnDate = this.get('returnDate');
            var passengersNumber = this.get('passengersNumber');
            searchJson = JSON.stringify({
                origin: departureCity,
                destination: arrivalCity,
                date: departureDate,
                passengers: passengersNumber
            });
            this.transitionToRoute('flights');
          },
          searchReservation: function(){
            this.transitionToRoute('application');
            reservationId = this.get('reservationNumber');
            var reservationEmail = this.get('reservationEmail');
            this.transitionToRoute('reservation');
          }
      }
});

//Models for reservation
App.Reservation = DS.Model.extend({
    id: DS.attr('string'),
    name:DS.attr('string'),
    last_name:DS.attr('string'),
    passengers:DS.attr('string'),
    flights:DS.hasMany('stop')
});

App.Stop = DS.Model.extend({
    departure_date:DS.attr('date'),
    arrival_date:DS.attr('date'),
    departure_airport:DS.attr('string'),
    arrival_airport:DS.attr('string')
});

// Models for search
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
