App.TripCheck = Ember.Checkbox.extend({});

App.BookingController = Ember.ObjectController.extend({
    needs:['search']
});

App.FlightsController = Ember.ArrayController.extend({});

App.ReservationController = Ember.ObjectController.extend({});

App.ReserveController = Ember.ObjectController.extend({
      reservationNumber:null,
        actions:{
              searchReservation: function(){
                this.transitionToRoute('reserve');
                reservationId = this.get('reservationNumber');
                var reservationEmail = this.get('reservationEmail');
                this.transitionToRoute('reservation');
              }
        }
});

App.SearchController = Ember.ObjectController.extend({
      passengerslimit: [1,2,3,4,5,6],
      departureCity:null,
      arrivalCity:null,
      departureDate:null,
      returnDate:null,
      passengersNumber:null,
      reservationEmail:null,
      isRoundTrip : false,
      actions: {
          tripType: function(){
            isRoundTrip = this.get('isRoundTrip');
            console.log(isRoundTrip);
          },
          searchFlights: function(){
          this.transitionToRoute('search');
          passengersNumber = this.get('passengersNumber');
            //TODO hardcoded stuff, take them out.
            var departureCity = "LAX";//this.get('departureCity');
            var departureDate = "2014-12-12";//this.get('departureDate');
            var arrivalCity = "MEX";//this.get('arrivalCity');
            var returnDate = "2014-19-12";//this.get('returnDate');
            var passengersNumber = this.get('passengersNumber');
            searchJson = JSON.stringify({
                origin: departureCity,
                destination: arrivalCity,
                date: departureDate,
                passengers: passengersNumber
            });
            console.log(searchJson);
            this.transitionToRoute('flights');
          }
      }
});