# search-engine-be
Lucene basic search engine

#### Modules:

* [Indexer](https://github.com/medbs/search-engine-be/blob/master/indexer/README.md)
* [Searcher](https://github.com/medbs/search-engine-be/blob/master/searcher/README.md)

#### Running the project:

Start the containers
`docker-compose up`


Create the index of Mongodb
`curl -X POST http://localhost:8081/api/v1/index-chat`
`

Search for words
`curl -X POST -G http://localhost:8082/api/v1/search/beyond+even`
`