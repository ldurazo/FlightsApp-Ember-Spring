App = Ember.Application.create({
    LOG_ACTIVE_GENERATION: true,
    LOG_TRANSITIONS: true,
    LOG_TRANSITIONS_INTERNAL: false,
    LOG_VIEW_LOOKUPS: true
});

App.TripCheck = Ember.Checkbox.extend({});

        $.ajaxSetup({
          contentType: "application/json; charset=utf-8"
        });

//Global variables, they may belong to somewhere else.
searchJson = null;
bookJson = null;
reservationId = null;
passengersNumber = null;
