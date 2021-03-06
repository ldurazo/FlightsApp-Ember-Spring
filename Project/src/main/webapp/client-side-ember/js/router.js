App.Router.map(function() {
    this.route("search", function() {
        this.resource("flights", { path: '/flights'});
    });

    this.resource("booked");

    this.route("booking", { path: '/booking/:model'});

    this.route("reserve", function() {
        this.resource("reservation", { path: '/reservation'});
    });
});

App.IndexRoute = Ember.Route.extend({});

App.ApplicationRoute = Ember.Route.extend({});

App.ReservationRoute = Ember.Route.extend({
    model: function() {
        return $.getJSON("http://localhost:8080/mvn-webapp-flights/reservation/"+reservationId+"/"+rEmail+"/", function(data) {
            return data;
        });
    }
});

App.BookedRoute = Ember.Route.extend({
        model: function() {
        return $.post("http://localhost:8080/mvn-webapp-flights/reservation", bookJson, function(response) {
            return response;
        }, 'json');
        }
});

App.FlightsRoute = Ember.Route.extend({
    model: function() {
        //Uncomment this when consuming the API
        return $.post("http://localhost:8080/mvn-webapp-flights/search", searchJson, function(response) {
            return response;
        }, 'json');

        //dummy json (QPX has limit of requests per day)
//        return $.getJSON("http://www.mocky.io/v2/54506432584e0fee056d0062", function(data){
//            return data;
//        });
    }
});