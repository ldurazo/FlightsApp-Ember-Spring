var autocomplete = function(){
      $( "#w-departure-search" ).autocomplete({
        select: function (e, ui) {
            $("#w-departure-search").val(ui.item.label);
            return false;
        },
        minLength: 3,
        source: function (request, response) {
            $.ajax({
                url: 'http://localhost:8080/mvn-webapp-flights/autocomplete',
                data: request,
                success: function (data) {
                    var ParsedObject = $.parseJSON(data);
                    response($.map(ParsedObject, function (item) {
                        var results = item.iata_code + " - " + item.city_name;
                        return {
                            label: results
                        };

                    }));
                }
            });
        }
    });
    $( "#w-arrival-search" ).autocomplete({
        select: function (e, ui) {
            $("#w-arrival-search").val(ui.item.label);
            return false;
        },
        minLength: 3,
        source: function (request, response) {
            $.ajax({
                url: 'http://localhost:8080/mvn-webapp-flights/autocomplete',
                data: request,
                success: function (data) {
                    var ParsedObject = $.parseJSON(data);
                    response($.map(ParsedObject, function (item) {
                        var results = item.iata_code + " - " + item.city_name;
                        return {
                            label: results
                        };

                    }));
                }
            });
        }
    });
  }
  $(document).on("keydown", "#w-departure-search", autocomplete);
  $(document).on("keydown", "#w-arrival-search", autocomplete);