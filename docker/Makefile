default:
	docker build --pull --rm -f "Dockerfile" -t xrazis/nodejs-project "."
	docker-compose -f "docker-compose.yml" up -d --build
	docker push xrazis/nodejs-project:latest
	docker stack deploy -c docker-compose.yml demo

first-run:
	docker pull xrazis/nodejs-project
	docker stack deploy -c docker-compose.yml demo
