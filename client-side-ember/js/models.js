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

App.Status = DS.Model.extend({
    status: DS.belongsTo
});