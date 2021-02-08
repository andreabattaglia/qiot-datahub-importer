#!/bin/env python3

import time
import json
import pandas as pd
from influxdb import InfluxDBClient

columns = [ "date",
            "country",
            "city",
            "specie",
            "count",
            "min",
            "max",
            "median",
            "variance"]

values = {
    "pm1": "pm_1",
    "pm25": "pm2_5",
    "pm10": "pm_10",
    "wind gust": "wind-speed",
    "wind-gust": "wind-speed",
    "wind speed": "wind-speed",
    "wind-speed": "wind-speed"
}

#convert sample data to line protocol (with nanosecond precision)
df = pd.read_csv("measurements/waqi-covid-2020.csv", names=columns, header=None)

with open('stations/airquality-covid19-cities.json') as json_file:
    json_data = json.load(json_file)

influx_client = InfluxDBClient(host='localhost', port=8086)
influx_client.switch_database('covid19')

with open("output.txt", "w") as output_file:
    for i,r in df.iterrows():
        timestamp = int(time.mktime(time.strptime(r["date"], '%Y-%m-%d')))
        for d in json_data["data"]:
                if d["Place"]["name"] == r["city"]:
                    latitude = d["Place"]["geo"][0]
                    longitude = d["Place"]["geo"][1]
                    pop = d["Place"]["pop"]
                    feature = d["Place"]["feature"]

        if r["specie"] in values:
            r["specie"] = values[r["specie"]]

        line_val= "%s,country=%s,city=%s,lat=%s,long=%s,population=%s,feature=%s count=%s,min=%s,max=%s,median=%s,variance=%s %d\n" % \
                (r["specie"], r["country"], r["city"], latitude, longitude, pop, feature, \
                r["count"], r["min"], r["max"], r["median"], r["variance"], timestamp)
        influx_client.write_points(line_val, database='covid19', time_precision='ms', protocol='line')