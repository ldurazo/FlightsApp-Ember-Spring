App = Ember.Application.create({
    LOG_ACTIVE_GENERATION: true,
    LOG_TRANSITIONS: true,
    LOG_TRANSITIONS_INTERNAL: false,
    LOG_VIEW_LOOKUPS: true
});

$.ajaxSetup({cache: false});

//Global variables.
searchJson = null;
reservationId = null;
passengersNumber = null;
