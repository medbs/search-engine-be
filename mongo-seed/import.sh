#! /bin/bash

mongoimport --host mongodb --db search --collection chat --type json --file /mongo-seed/data.json --jsonArray
