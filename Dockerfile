FROM java:8
COPY . /tmp/app
EXPOSE 8080
CMD ["/tmp/app/gradlew", "-p", "/tmp/app", "bootRun"]