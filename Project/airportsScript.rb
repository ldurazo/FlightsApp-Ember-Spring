#!/usr/bin/env ruby 
 
require 'rubygems'
require 'json'
require 'rest-client' 
require 'uri'
require 'pg'

begin

client = PG::Connection.open(:host => "localhost", :user => "root", :password => "root" , :dbname => "flightsdb")

  url='https://api.flightstats.com/flex/airports/rest/v1/json/all?appId=e9bf38a8&appKey=75a5f3100db3ee5e2b7fc4bdb5fa222f'
  airportsJson = JSON.parse(RestClient.get(url))
  for airports in airportsJson["airports"]
          iata = airports["iata"]
          latitude = airports["latitude"]
          longitude = airports["longitude"]
          name = airports["name"]
          name = name.gsub("'", %q(_))
          city = airports["city"]
          city = city.gsub("'", %q(_))
          client.query("INSERT INTO AIRPORTS VALUES(DEFAULT, '#{iata}' , '#{latitude}' , '#{longitude}', '#{name}', '#{city}')")
  end
  print "loading finished"
  client.close
end


