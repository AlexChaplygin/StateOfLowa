# StateOfLowa
Given: a single CSV file as input.

 

Your solution may use whatever technologies you wish to build on:

 

·        Web server software

·        Database software

·        Database interaction layer

 

The challenge will utilize the State of Iowa - Monthly Voter Registration Totals by County data set:

 

https://data.iowa.gov/Communities-People/State-of-Iowa-Monthly-Voter-Registration-Totals-by/cp55-uurs

 

Download the JSON, CSV or other and use that as your input. It contains 19 columns and over 20,000 rows. Now expose the data via a web API.

 

Your solution must implement the following API behaviors:

 

·        A "get_voters" endpoint that takes the following optional arguments: county, month, party affiliation, active_status, and limit (the max number of results to return). The endpoint must return a JSON-formatted output, but the schema is up to you.

·        All APIs must be RESTful

 

Example of URL: http://127.0.0.1:8080/get_voters?county=Adair&month=3&limit=2&party=Republican

Example of output:

 

{

  "data": [

    {

      "County": "Adair",

      "Date": "Sun, 01 Mar 2015 00:00:00 GMT",

      "Grand Total": 5271,

      "Republican - Active": 1906,

      "Republican - Inactive": 61

    },

    {

      "County": "Adair",

      "Date": "Sat, 01 Mar 2014 00:00:00 GMT",

      "Grand Total": 5552,

      "Republican - Active": 1901,

      "Republican - Inactive": 131

    }

  ],

  "next_start": 2

}