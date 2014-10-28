

App.BookingController = Ember.ObjectController.extend({
    needs:['search'],
    name:null,
    last_name:null,
    email:null,
    listOfFlights:null,
    actions: {
        saveReservation: function(){
            this.transitionToRoute('booking')
            name= this.get('userName');
            last_name= this.get('userLastName');
            email=this.get('userMail');
            price = this.get('saleTotal');
            passengersNumber = this.get('controllers.search.passengersNumber');
            listOfFlights = this.get('slice')[0].segment[0].leg;
            bookJson = JSON.stringify({
                name: name,
                last_name: last_name,
                passengers: passengersNumber,
                flights: listOfFlights,
                cost: price,
                email: email
            });
            console.log(bookJson);
            this.transitionToRoute('booked');
        }
    }
});

App.BookedControler=Ember.ObjectController.extend({});

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