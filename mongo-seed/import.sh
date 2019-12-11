#! /bin/bash

mongoimport --host mongod --db search --collection chat --type json --file /mongo-seed/data.json --jsonArray
