# Integration Test With TestContainers


Testcontainer is docker based technology maybe you want to check it out. https://www.testcontainers.org/


First of all, docker needs to be installed.
After that you can run mvn clean install and can see the result with hazelcast server in docker container.

After tests are finished all the containers will be stopped.

If you are interested you can check the docker containers while debugging test.

# Important Notes

- You can use all of the docker images but it has to be thin so be careful. If not you need to wait a lot of time before setup or it will fail.
- Also in development or integration or production environment you can use any tool so you can use them in integration tests as well.
- Database, Redis, Hazelcast etc.. can be used with testcontainers.


