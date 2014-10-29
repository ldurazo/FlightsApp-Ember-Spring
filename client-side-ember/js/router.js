App.Router.map(function() {
    this.route("search", function() {
        this.resource("flights", { path: '/flights'});
    });

    this.route("booked");

    this.route("booking", { path: '/booking/:model'});

    this.route("reserve", function() {
        this.resource("reservation", { path: '/reservation'});
    });
});

App.IndexRoute = Ember.Route.extend({});

App.ApplicationRoute = Ember.Route.extend({});

App.ReservationRoute = Ember.Route.extend({
    model: function() {
        return $.getJSON("http://localhost:8080/mvn-webapp-flights/reservation/"+reservationId, function(data) {
            return data;
        });
    }
});

App.BookedRoute = Ember.Route.extend({
        model: function() {
            return $.post("http://localhost:8080/mvn-webapp-flights/reservation", bookJson, function(response) {
                console.log(response);
                return response;
            }, 'json');
        }
});

App.FlightsRoute = Ember.Route.extend({
    model: function() {
        //TODO Uncomment this when consuming the API
        return $.post("http://localhost:8080/mvn-webapp-flights/search", searchJson, function(response) {
            console.log(response);
            return response;
        }, 'json');

        //dummy json (QPX has limit of requests per day)
//        return $.getJSON("http://www.mocky.io/v2/542f03d5f190d8220c26e891", function(data){
//            return data;
//        });
    }
});