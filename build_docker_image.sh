#!/bin/sh

# Get the current timestamp
VERSION=$(date +%Y%m%d%H%M%S)

echo "Version: $VERSION"

echo "compiling jar file"
mvn clean package -DskipTests

echo "building docker image"
docker buildx build --platform linux/amd64,linux/arm64 -t mhsenpc/hiddify_bot:$VERSION -t mhsenpc/hiddify_bot:latest .

echo "pushing docker image with version $VERSION"
docker push mhsenpc/hiddify_bot:$VERSION

echo "pushing docker image with latest tag"
docker push mhsenpc/hiddify_bot:latest

echo "Done"
