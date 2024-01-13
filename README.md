### Docker

For every merge and push to the main branch, the pipeline will build a docker container and push it to dockerhub.
The latest image is available at [darkress/assets:latest](https://hub.docker.com/r/darkress/assets)


### Testing

For every merge and push to the main branch, pipeline will run a linter against the Dockerfile to check for any issues or warnings.


### Database queries

Database queries are secured via the hibernate orm.
Specific prepared statements are therefore not required.


### Configuration and Settings

These can be given as environment variables to the docker container

| Property                   | Description                                    | Default                                 |
|----------------------------|------------------------------------------------|-----------------------------------------|
| spring_datasource_url      | Full url under which the database is reachable | jdbc:postgresql://localhost:5432/assets |
| spring_datasource_username | Database username                              | postgres                                |
| spring_datasource_password | Database password                              | -                                       |

### Licence

MIT license. The licence is provided in the file LICENCE. 