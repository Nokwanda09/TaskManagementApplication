backend-clean:
	cd TaskManagementAPI && mvn clean

backend-compile:
	cd TaskManagementAPI && mvn compile

backend-install: backend-clean
	cd TaskManagementAPI && mvn install


backend-run:
	cd TaskManagementAPI && mvn spring-boot:run
