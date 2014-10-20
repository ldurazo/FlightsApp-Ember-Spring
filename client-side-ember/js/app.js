App = Ember.Application.create();

App.Router.map(function() {
});

App.IndexRoute = Ember.Route.extend({
    model: function() {
        Uncomment this, this is the model that should be retrieved
        return this.store.find('Flight');
    }
});

App.IndexController = Ember.ArrayController.extend({
    needs: "application"
});

App.IndexAdapter = DS.RESTAdapter.extend({
  host: 'http://www.mocky.io/v2/542f03d5f190d8220c26e891'
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
        console.log(departureDate +" "+ departureCity +" "+ arrivalCity +" "+ returnDate +" "+ passengersNumber);
      }
  }
});

App.PostRequest=DS.Model.extend({
    departureCity: DS.attr('string'),
    arrivalCity: DS.attr('string'),
    returnDate: DS.attr('string'),
    departureDate: DS.attr('string')
    passengersNumber: DS.attr('string')
})

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
