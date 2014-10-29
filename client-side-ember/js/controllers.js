

App.BookingController = Ember.ObjectController.extend({
    needs:['search'],
    name:null,
    last_name:null,
    email:null,
    listOfFlights:null,
    actions: {
        saveReservation: function(){
            var i,
            arr = [], leg=[];
            this.transitionToRoute('booking')
            name= this.get('userName');
            last_name= this.get('userLastName');
            email=this.get('userMail');
            price = this.get('saleTotal');
            passengersNumber = this.get('controllers.search.passengersNumber');
            listOfSlice = this.get('slice');
            for (i=0; i< listOfSlice.length; i++){
                for(j=0; j< listOfSlice[i].segment.length; j++){
                    for(k=0; k< listOfSlice[i].segment[j].leg.length; k++){
                        arr[j] = listOfSlice[i].segment[j].leg[k];
                    }
                }
            }
            console.log(arr);
            bookJson = JSON.stringify({
                name: name,
                last_name: last_name,
                passengers: passengersNumber,
                flights: arr,
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
      reservationEmail:null,
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
            var departureCity = this.get('departureCity').substring(0,3);//"LAX";
            var departureDate = this.get('departureDate');//"2014-12-12";
            var arrivalCity = this.get('arrivalCity').substring(0,3);//"MEX";
            var returnDate = this.get('returnDate');//"2014-19-12";
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