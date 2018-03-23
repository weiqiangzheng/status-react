# Build

```
docker build -t status-react-native-v2:latest .
```

# Run

## Bash

```
docker run -t -i --rm --name build status-react-native-v2 bash
```

## Build app

```
docker run -t -i \
  -v $(pwd):/workspace \
  -v ~/.gradle/:/home/user/.gradle/ \
  -w /workspace \
  -e LOCAL_USER_ID=`id -u $USER` \
  status-react-native-v2 \
  /bin/sh -c "cd android && ./gradlew --stacktrace assembleRelease"

```


